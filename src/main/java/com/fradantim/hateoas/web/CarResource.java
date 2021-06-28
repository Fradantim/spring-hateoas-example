package com.fradantim.hateoas.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fradantim.hateoas.model.CarModel;
import com.fradantim.hateoas.model.assembler.CarModelAssembler;
import com.fradantim.hateoas.persistence.entity.Car;
import com.fradantim.hateoas.persistence.repository.CarRepository;

@RestController
@RequestMapping("/car")
public class CarResource implements ResourceGetsModelById<CarModel, Long> {

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private CarModelAssembler carModelAssembler;

	@Autowired
	private PagedResourcesAssembler<Car> pagedResourcesAssembler;

	@GetMapping()
	public ResponseEntity<PagedModel<CarModel>> getAll(Pageable pageable) {
		Page<Car> carEntities = carRepository.findAll(pageable);
		PagedModel<CarModel> modelCollection = pagedResourcesAssembler.toModel(carEntities, carModelAssembler);
		return new ResponseEntity<>(modelCollection, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CarModel> getById(@PathVariable("id") Long id) {
		return carRepository.findById(id).map(carModelAssembler::toModel).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
}
