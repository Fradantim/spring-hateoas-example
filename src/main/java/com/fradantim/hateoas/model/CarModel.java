package com.fradantim.hateoas.model;

import org.springframework.hateoas.RepresentationModel;

public class CarModel extends RepresentationModel<CarModel>{
	
	public static final String OWNER_REL = "owner";

	private Long id;
	
	private String brand;
	
	public CarModel() {
		super();
	}
	
	public CarModel(String brand) {
		this();
		this.brand = brand;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
}
