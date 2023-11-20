package br.com.rest.Services.Interfaces;

import br.com.rest.Domain.Entities.Person;

import java.util.List;

public interface PersonInterface {
    Person findById(String id);
    List<Person> findAll();
    Person create(Person person);
    Person updatePerson(Long id, Person person);
    void deletePerson(String id);
}
