package br.com.rest.Services.Services;

import br.com.rest.Domain.Dtos.V1.PersonDto;
import br.com.rest.Domain.Dtos.V2.PersonV2Dto;
import br.com.rest.Domain.Entities.Person;
import br.com.rest.Domain.Mapper.PersonParseObjectHelper;
import br.com.rest.Handler.Exceptions.ResourceNotFoundException;
import br.com.rest.Repositories.PersonRepository;
import br.com.rest.Services.Interfaces.PersonInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService implements PersonInterface {

    @Autowired
    PersonRepository personRepository;

    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    @Override
    public PersonDto findById(String id){
        logger.info("Finding one person");
        return PersonParseObjectHelper.mapToPersonDto(identifyNullPerson(Long.parseLong(id)));
    }

    @Override
    public List<PersonDto> findAll(){
        logger.info("Finding list persons");
        List<PersonDto> returnList = new ArrayList<>();
        personRepository.findAll()
                .stream()
                .toList()
                .forEach(person -> returnList.add(PersonParseObjectHelper.mapToPersonDto(person)));

        return returnList;
    }

    @Override
    public void deletePerson(String id){
        logger.info("Deleting on person");
        personRepository.delete(identifyNullPerson(Long.parseLong(id)));
    }

    @Override
    public PersonDto create(PersonDto personDto){
        logger.info("Starting create person");

        Person newPerson = PersonParseObjectHelper.mapToEntity(personDto);
        personRepository.save(newPerson);

        logger.info("Success create person");

        return personDto;
    }

    @Override
    public PersonV2Dto newCreatePerson(PersonV2Dto personDto) {
        logger.info("Starting create person");

        Person newPerson = PersonParseObjectHelper.mapToEntityV2(personDto);
        personRepository.save(newPerson);

        logger.info("Success create person");

        return personDto;
    }

    @Override
    public PersonDto updatePerson(Long id, PersonDto personDto){
        logger.info("Updating person");

        Person newPerson = identifyNullPerson(id);

        newPerson.setFirstName(personDto.getFirstName());
        newPerson.setLastName(personDto.getLastName());
        newPerson.setAddress(personDto.getAddress());
        newPerson.setGender(personDto.getGender());

        personRepository.save(newPerson);

        return PersonParseObjectHelper.mapToPersonDto(newPerson);
    }

    private Person identifyNullPerson(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found person by id"));
    }
}
