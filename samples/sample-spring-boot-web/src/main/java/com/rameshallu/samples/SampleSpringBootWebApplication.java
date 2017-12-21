package com.rameshallu.samples;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.rameshallu.samples.jpa.entity.Person;
import com.rameshallu.samples.jpa.repository.PersonRepository;

@SpringBootApplication
public class SampleSpringBootWebApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(SampleSpringBootWebApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SampleSpringBootWebApplication.class, args);
	}
	
	@Bean
	@Transactional
	public CommandLineRunner run(PersonRepository personRepository) {
		return (args) -> {
			logger.info("Saving sample data to repository..");
			personRepository.save(new Person("ID1", "John Doe", "M", "US"));
			personRepository.save(new Person("ID2", "Julia Roberts", "F", "US"));
			personRepository.save(new Person("ID3", "Amitabh Bachan", "M", "IN"));
			personRepository.save(new Person("ID4", "Jackie Chan", "M", "HK"));
		};
	}
}
