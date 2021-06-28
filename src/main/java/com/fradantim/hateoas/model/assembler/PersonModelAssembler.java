package com.fradantim.hateoas.model.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.stereotype.Component;

import com.fradantim.hateoas.model.PersonModel;
import com.fradantim.hateoas.persistence.entity.Person;
import com.fradantim.hateoas.web.PersonResource;

@Component
public class PersonModelAssembler extends AssemblerUtil<Person, PersonModel, Long> {

	public PersonModelAssembler() {
		super(PersonResource.class, PersonModel.class);
	}

	@Override
	public PersonModel toModel(Person entity) {
		PersonModel personModel = toMiniModel(entity);

		personModel.setId(entity.getId());
		personModel.setName(entity.getName());
		personModel.setBirthdate(entity.getBirthdate());
		
		personModel.add(linkTo(methodOn(PersonResource.class).getPersonCars(entity.getId())).withRel(PersonModel.CARS_REL));
			
		
		return personModel;
	}
}
