package com.fradantim.hateoas.persistence.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.fradantim.hateoas.persistence.entity.Person;

public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

}
