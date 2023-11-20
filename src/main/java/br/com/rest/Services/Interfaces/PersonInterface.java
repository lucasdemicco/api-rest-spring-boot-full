package br.com.rest.Services.Interfaces;

import br.com.rest.Domain.Dtos.PersonDto;
import br.com.rest.Domain.Entities.Person;

import java.util.List;

public interface PersonInterface {
    PersonDto findById(String id);
    List<PersonDto> findAll();
    PersonDto create(PersonDto personDto);
    PersonDto updatePerson(Long id, PersonDto personDto);
    void deletePerson(String id);
}
