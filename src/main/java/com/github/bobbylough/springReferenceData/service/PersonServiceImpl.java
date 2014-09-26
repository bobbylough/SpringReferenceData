package com.github.bobbylough.springReferenceData.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.bobbylough.springReferenceData.dao.PersonDao;
import com.github.bobbylough.springReferenceData.model.Person;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonDao personDao;

	@Override
	public List<Person> getPeople() {
		return personDao.getAllPeople();
	}

	@Override
	public void savePerson(Person person) {
		personDao.savePerson(person);
	}

}
