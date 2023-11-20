package br.com.rest.Controller;

import br.com.rest.Domain.Dtos.PersonDto;
import br.com.rest.Domain.Entities.Person;
import br.com.rest.Services.Services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDto retrievePersonById(@PathVariable("id") String id){
        return personService.findById(id);
    }

    @GetMapping(value = "/findAllPersons", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonDto> retrieveAllPersons(){
        return personService.findAll();
    }

    @PostMapping(
            value = "/createPerson",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonDto createPerson(@RequestBody(required = true) PersonDto person){
        return personService.create(person);
    }

    @PutMapping(value = "/updatePerson/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonDto updatePerson(
            @PathVariable(value = "id") Long id,
            @RequestBody(required = true) PersonDto person){
        return personService.updatePerson(id, person);
    }

    @DeleteMapping("/deletePerson/{id}")
    public void deletePerson(@PathVariable(value = "id") String id){
        personService.deletePerson(id);
    }

}
