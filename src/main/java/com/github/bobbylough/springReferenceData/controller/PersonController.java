package com.github.bobbylough.springReferenceData.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.bobbylough.springReferenceData.model.Person;
import com.github.bobbylough.springReferenceData.service.PersonService;

@RestController
@RequestMapping("/")
public class PersonController {
	@Autowired
	private PersonService personService;
	@RequestMapping("/helloPeople")
	public List<Person> getPeople(@RequestParam(value = "name",required = false, defaultValue = "") String name) {
		Person randomPerson = new Person();
		randomPerson.setName("random" + (int)(Math.random() * 100));
		personService.savePerson(randomPerson);

		if (!StringUtils.isEmpty(name)) {
			Person person = new Person();
			person.setName(name);
			personService.savePerson(person);
		}
		return personService.getPeople();
	}
}