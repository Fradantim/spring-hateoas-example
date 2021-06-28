package com.fradantim.hateoas.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Car implements HasId<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String brand;

	@Column(name="owner_id", updatable=false, insertable=false)
	private Long ownerId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Person owner;

	public Car() {
	}

	public Car(Person owner, String brand) {
		super();
		this.owner = owner;
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

	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}
	
	public Long getOwnerId() {
		return ownerId;
	}
}
