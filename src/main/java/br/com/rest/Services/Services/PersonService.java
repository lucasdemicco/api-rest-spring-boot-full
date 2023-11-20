package br.com.rest.Services.Services;

import br.com.rest.Domain.Entities.Person;
import br.com.rest.Handler.Exceptions.ResourceNotFoundException;
import br.com.rest.Repositories.PersonRepository;
import br.com.rest.Services.Interfaces.PersonInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class PersonService implements PersonInterface {

    @Autowired
    PersonRepository personRepository;
    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    @Override
    public Person findById(String id){
        logger.info("Finding one person");
        return identifyNullPerson(Long.parseLong(id));
    }

    @Override
    public List<Person> findAll(){
        logger.info("Finding list persons");
        return personRepository.findAll();
    }

    @Override
    public void deletePerson(String id){
        logger.info("Deleting on person");
        Person person = identifyNullPerson(Long.parseLong(id));
        personRepository.delete(person);
    }

    @Override
    public Person create(Person person){
        logger.info("Starting create person");

        Person newPerson = new Person();
        newPerson.setFirstName(person.getFirstName());
        newPerson.setLastName(person.getLastName());
        newPerson.setAddress(person.getAddress());
        newPerson.setGender(person.getGender());

        personRepository.save(newPerson);

        logger.info("Success create person");

        return newPerson;
    }

    @Override
    public Person updatePerson(Long id, Person person){
        logger.info("Updating person");

        Person newPerson = identifyNullPerson(id);

        newPerson.setFirstName(person.getFirstName());
        newPerson.setLastName(person.getLastName());
        newPerson.setAddress(person.getAddress());
        newPerson.setGender(person.getGender());

        personRepository.save(newPerson);

        return person;
    }

    private Person identifyNullPerson(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found person by id"));
    }
}
