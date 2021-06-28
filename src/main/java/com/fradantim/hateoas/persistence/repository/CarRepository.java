package com.fradantim.hateoas.persistence.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.fradantim.hateoas.persistence.entity.Car;
import com.fradantim.hateoas.persistence.entity.Person;

public interface CarRepository extends PagingAndSortingRepository<Car, Long> {

	public List<Car> findByOwner(Person owner);
}
