package com.fradantim.hateoas.model;

import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;

public class PersonModel extends RepresentationModel<PersonModel> {
	
	public final static String CARS_REL = "cars";

	private Long id;

	private String name;

	private LocalDate birthdate;

	public PersonModel() {
		super();
	}

	public PersonModel(String name, LocalDate birthdate) {
		this();
		this.name = name;
		this.birthdate = birthdate;
	}

	public PersonModel(Long id, String name, LocalDate birthdate) {
		this(name, birthdate);
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
}
