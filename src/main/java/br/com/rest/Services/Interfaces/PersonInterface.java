package br.com.rest.Services.Interfaces;

import br.com.rest.Domain.Dtos.V1.PersonDto;
import br.com.rest.Domain.Dtos.V2.PersonV2Dto;

import java.util.List;

public interface PersonInterface {
    PersonDto findById(String id);
    List<PersonDto> findAll();
    PersonDto create(PersonDto personDto);
    PersonDto newCreatePerson(PersonV2Dto personDto);
    PersonDto updatePerson(Long id, PersonDto personDto);
    void deletePerson(String id);
    String getPersonName(Long id);
}
