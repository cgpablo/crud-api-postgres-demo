package com.github.cgpablo.crudapidemo.dao;

import java.util.Optional;

import com.github.cgpablo.crudapidemo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	@Query("SELECT p FROM Person p WHERE p.email = ?1")
	Optional<Person> findPersonByEmail(String email);
}
