package br.com.rest.Services.Services;

import br.com.rest.Domain.Entities.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();
    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    public Person findById(String id){
        logger.info("Finding one person");

        Person person = new Person();
        person.setId(counter.get());
        person.setFirstName("Mock");
        person.setLastName("Mockzinho");
        person.setAddress("Rua dos Mocks , 123");
        person.setGender("Male");

        return person;
    }

    public List<Person> findAll(){
        logger.info("Finding list persons");
        List<Person> persons = new ArrayList<Person>();

        for (int i = 0; i < 8; i++){
            persons.add(mockPerson(i));
        }

        return persons;
    }

    private Person mockPerson(int i) {
        Person person = new Person();

        person.setId(counter.incrementAndGet());
        person.setFirstName("Name " + i);
        person.setLastName("Last Name " + i);
        person.setAddress("Rua dos Mocks " + i);
        person.setGender("Male " + i);

        return person;
    }

}
