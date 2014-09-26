package com.github.bobbylough.springReferenceData.dao;

import java.util.List;

import com.github.bobbylough.springReferenceData.model.Person;

public interface  PersonDao {
	public List<Person> getAllPeople();
	public void savePerson(Person person);
}
