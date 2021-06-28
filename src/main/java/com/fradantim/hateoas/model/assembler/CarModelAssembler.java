package com.fradantim.hateoas.model.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.stereotype.Component;

import com.fradantim.hateoas.model.CarModel;
import com.fradantim.hateoas.persistence.entity.Car;
import com.fradantim.hateoas.web.CarResource;
import com.fradantim.hateoas.web.PersonResource;

@Component
public class CarModelAssembler extends AssemblerUtil<Car, CarModel, Long> {
	
	public CarModelAssembler() {
		super(CarResource.class, CarModel.class);
	}
	
	@Override
	public CarModel toModel(Car entity) {
		CarModel carModel = toMiniModel(entity);
		carModel.setId(entity.getId());
		carModel.setBrand(entity.getBrand());

		if(entity.getOwnerId() != null)
			carModel.add(linkTo(methodOn(PersonResource.class).getById(entity.getOwnerId())).withRel(CarModel.OWNER_REL));
		
		return carModel;
	}
}
