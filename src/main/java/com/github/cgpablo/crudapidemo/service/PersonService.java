package com.github.cgpablo.crudapidemo.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.cgpablo.crudapidemo.dao.PersonRepository;
import com.github.cgpablo.crudapidemo.model.Person;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	public void addPerson(Person person) {
		Optional<Person> personByEmail = personRepository.findPersonByEmail(person.getEmail());
		if (personByEmail.isPresent()) {
			throw new IllegalStateException("email taken");
		}
		personRepository.save(person);
	}

	public List<Person> getAllPeople() {
		return personRepository.findAll();
	}

	public Optional<Person> getPersonById(Long id) {
		return personRepository.findById(id);
	}

	public void deletePersonById(Long id) {
		if (!personRepository.existsById(id)) {
			throw new IllegalStateException("person with id " + id + " does not exist");
		}
		personRepository.deleteById(id);
	}

	@Transactional
	public void updatePersonById(Long id, String name, String email) {
		Person person = personRepository.findById(id)
				.orElseThrow(() -> new IllegalStateException("person with id " + id + " does not exist"));
		
		if (null != name && name.length() > 0 && !Objects.equals(person.getName(), name)) {
			person.setName(name);
		}

		if (null != email && email.length() > 0 && !Objects.equals(person.getEmail(), email)) {
			Optional<Person> personByEmail = personRepository.findPersonByEmail(person.getEmail());
			if (personByEmail.isPresent()) {
				throw new IllegalStateException("email taken");
			}
			person.setEmail(email);
		}
	}

}
