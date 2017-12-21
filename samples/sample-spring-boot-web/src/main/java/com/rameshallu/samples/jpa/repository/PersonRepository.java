package com.rameshallu.samples.jpa.repository;

import org.springframework.data.repository.CrudRepository;

import com.rameshallu.samples.jpa.entity.Person;

public interface PersonRepository extends CrudRepository<Person, String> {

}
