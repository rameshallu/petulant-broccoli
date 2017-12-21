package com.rameshallu.samples.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rameshallu.samples.jpa.entity.Person;
import com.rameshallu.samples.jpa.repository.PersonRepository;

@RestController
@RequestMapping("/api/person")
public class PersonController {
	
	@Autowired
	private PersonRepository personRepository;

	@CrossOrigin(origins = "http://127.0.0.1:3000")
	@GetMapping("/all")
	public Iterable<Person> all() {
		return personRepository.findAll();
	}

	@CrossOrigin(origins = "http://127.0.0.1:3000")
	@GetMapping("/{id}")
	public Person get(@PathVariable String id) {
		return personRepository.findOne(id);
	}
	
	@CrossOrigin(origins = "http://127.0.0.1:3000")
	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
		personRepository.delete(id);
	}
	
	@CrossOrigin(origins = "http://127.0.0.1:3000")
	@PostMapping("/save")
	public Person save(@ModelAttribute Person person) {
		return personRepository.save(person);
	}
}
