package br.com.rest.Services.Services;

import br.com.rest.Domain.Entities.Person;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();
    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    public Person findById(String id){
        logger.info("Finding one person");

        Person person = new Person();
        person.setId(1L);
        person.setFirstName("Mock");
        person.setLastName("Mockzinho");
        person.setAddress("Rua dos Mocks , 123");
        person.setGender("Male");

        return person;
    }


}
