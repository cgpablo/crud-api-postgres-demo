package com.github.cgpablo.crudapidemo.api;

import java.util.List;

import com.github.cgpablo.crudapidemo.model.Person;
import com.github.cgpablo.crudapidemo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {

	@Autowired
	private PersonService personService;

	
	@PostMapping
	public void addPerson(@RequestBody Person person) {
		personService.addPerson(person);
	}
	
	@GetMapping(path = "/{id}")
	public Person getPersonById(@PathVariable("id") Long id) {
		return personService.getPersonById(id)
				.orElse(null);
	}
	
	@GetMapping
	public List<Person> getAllPeople() {
		return personService.getAllPeople();
	}
	
	@DeleteMapping(path = "{id}")
	public void deletePersonById(@PathVariable("id") Long id) {
		personService.deletePersonById(id);
	}
	
	@PutMapping(path = "{id}")
	public void updatePersonById(@PathVariable("id") Long id, @RequestParam(required = false) String name, @RequestParam(required = false) String email) {
		personService.updatePersonById(id, name, email);
	}
	
}
