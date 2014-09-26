package com.github.bobbylough.springReferenceData.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.bobbylough.springReferenceData.model.Person;

@Transactional
@Repository
public class PersonDaoImpl implements PersonDao {

	@Autowired
	private SessionFactory sessionFactory;


	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<Person> getAllPeople() {
		try {
			return sessionFactory.getCurrentSession().createCriteria(Person.class).list();
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void savePerson(Person person) {
		try {
			Session mySession = sessionFactory.getCurrentSession();
			mySession.save(person);
			mySession.flush();
		} catch (Exception e) {
			System.out.println(e);
		}
	}




}
