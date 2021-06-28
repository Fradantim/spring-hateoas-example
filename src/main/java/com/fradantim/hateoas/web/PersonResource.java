package com.fradantim.hateoas.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fradantim.hateoas.model.CarModel;
import com.fradantim.hateoas.model.PersonModel;
import com.fradantim.hateoas.model.assembler.CarModelAssembler;
import com.fradantim.hateoas.model.assembler.PersonModelAssembler;
import com.fradantim.hateoas.persistence.entity.Car;
import com.fradantim.hateoas.persistence.entity.Person;
import com.fradantim.hateoas.persistence.repository.CarRepository;
import com.fradantim.hateoas.persistence.repository.PersonRepository;

@RestController
@RequestMapping("/person")
public class PersonResource implements ResourceGetsModelById<PersonModel, Long> {

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private CarRepository carRepository;

	@Autowired
	private PersonModelAssembler personModelAssembler;
	
	@Autowired
	private CarModelAssembler carModelAssembler;

	@Autowired
	private PagedResourcesAssembler<Person> pagedResourcesAssembler;

	@GetMapping()
	public ResponseEntity<PagedModel<PersonModel>> getAll(Pageable pageable) {
		Page<Person> personEntities = personRepository.findAll(pageable);

		PagedModel<PersonModel> modelCollection = pagedResourcesAssembler.toModel(personEntities, personModelAssembler);

		return new ResponseEntity<>(modelCollection, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PersonModel> getById(@PathVariable("id") Long id) {
		return personRepository.findById(id).map(personModelAssembler::toModel).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/{id}/cars")
	public ResponseEntity<CollectionModel<CarModel>> getPersonCars(@PathVariable("id") Long ownwerId) {
		List<Car> carEntities = carRepository.findByOwner(new Person(ownwerId));
		CollectionModel<CarModel> carModels = carModelAssembler.toCollectionModel(carEntities);
		
		return new ResponseEntity<>(carModels, HttpStatus.OK);
	}
}
