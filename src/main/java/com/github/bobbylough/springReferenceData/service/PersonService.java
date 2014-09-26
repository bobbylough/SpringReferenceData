package com.github.bobbylough.springReferenceData.service;

import java.util.List;

import com.github.bobbylough.springReferenceData.model.Person;

public interface PersonService {

	public List<Person> getPeople();

	public void savePerson(Person person);
}
