package com.rameshallu.samples.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "person")
public class Person {

	@Id
	private String id;

	@Column
	private String name;

	@Column
	private String gender;

	@Column
	private String country;

	public Person() {
		super();
	}

	public Person(String id, String name, String gender, String counry) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.country = counry;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", gender=" + gender + ", country=" + country + "]";
	}
}
