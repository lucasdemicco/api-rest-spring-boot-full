package br.com.rest.Controller;

import br.com.rest.Domain.Entities.Person;
import br.com.rest.Services.Services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Person retrievePersonById(@PathVariable("id") String id){
        return personService.findById(id);
    }

    @GetMapping(value = "/findAllPersons", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> retrieveAllPersons(){
        return personService.findAll();
    }

}
