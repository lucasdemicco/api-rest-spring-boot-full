package br.com.rest.Controller;

import br.com.rest.Domain.Dtos.V1.PersonDto;
import br.com.rest.Domain.Dtos.V2.PersonV2Dto;
import br.com.rest.Helper.StringHelper;
import br.com.rest.Services.Services.PersonService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/person")
@CrossOrigin(origins = "/**")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/personName/{id}")
    @ApiResponse(description = StringHelper.Ok, responseCode = StringHelper.Ok_STATUS_CODE_VALUE, content = @Content)
    @ApiResponse(description = StringHelper.NOT_FOUND, responseCode = StringHelper.NOT_FOUND_STATUS_CODE_VALUE, content = @Content)
    @ApiResponse(description = StringHelper.INTERNAL_SERVER_ERROR, responseCode = StringHelper.INTERNAL_SERVER_ERROR_STATUS_CODE_VALUE, content = @Content)
    public String getPersonName(@PathVariable("id") Long id){
        return personService.getPersonName(id);
    }

    @GetMapping(value = "/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(description = StringHelper.Ok, responseCode = StringHelper.Ok_STATUS_CODE_VALUE, content = @Content)
    @ApiResponse(description = StringHelper.NOT_FOUND, responseCode = StringHelper.NOT_FOUND_STATUS_CODE_VALUE, content = @Content)
    @ApiResponse(description = StringHelper.INTERNAL_SERVER_ERROR, responseCode = StringHelper.INTERNAL_SERVER_ERROR_STATUS_CODE_VALUE, content = @Content)
    public PersonDto retrievePersonById(@PathVariable("id") String id){
        return personService.findById(id);
    }

    @GetMapping(value = "/findAllPersons", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(description = StringHelper.Ok, responseCode = StringHelper.Ok_STATUS_CODE_VALUE, content = @Content)
    @ApiResponse(description = StringHelper.NOT_FOUND, responseCode = StringHelper.NOT_FOUND_STATUS_CODE_VALUE, content = @Content)
    @ApiResponse(description = StringHelper.INTERNAL_SERVER_ERROR, responseCode = StringHelper.INTERNAL_SERVER_ERROR_STATUS_CODE_VALUE, content = @Content)
    public List<PersonDto> retrieveAllPersons(){
        return personService.findAll();
    }

    @PostMapping(
            value = "/createPerson",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(description = StringHelper.CREATED, responseCode = StringHelper.CREATED_STATUS_CODE_VALUE, content = @Content)
    @ApiResponse(description = StringHelper.INTERNAL_SERVER_ERROR, responseCode = StringHelper.INTERNAL_SERVER_ERROR_STATUS_CODE_VALUE, content = @Content)
    public PersonDto createPerson(@RequestBody(required = true) PersonDto person){
        return personService.create(person);
    }

    @PostMapping(value = "/createPerson/v2", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(description = StringHelper.CREATED, responseCode = StringHelper.CREATED_STATUS_CODE_VALUE, content = @Content)
    @ApiResponse(description = StringHelper.INTERNAL_SERVER_ERROR, responseCode = StringHelper.INTERNAL_SERVER_ERROR_STATUS_CODE_VALUE, content = @Content)
    public PersonV2Dto createPerson(@RequestBody(required = true) PersonV2Dto person){
        return personService.newCreatePerson(person);
    }

    @PutMapping(value = "/updatePerson/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(description = StringHelper.Ok, responseCode = StringHelper.Ok_STATUS_CODE_VALUE, content = @Content)
    @ApiResponse(description = StringHelper.BAD_REQUEST, responseCode = StringHelper.BAD_REQUEST_STATUS_CODE_VALUE, content = @Content)
    @ApiResponse(description = StringHelper.INTERNAL_SERVER_ERROR, responseCode = StringHelper.INTERNAL_SERVER_ERROR_STATUS_CODE_VALUE, content = @Content)
    public PersonDto updatePerson(
            @PathVariable(value = "id") Long id,
            @RequestBody(required = true) PersonDto person){
        return personService.updatePerson(id, person);
    }

    @DeleteMapping("/deletePerson/{id}")
    @ApiResponse(description = StringHelper.Ok, responseCode = StringHelper.Ok_STATUS_CODE_VALUE, content = @Content)
    @ApiResponse(description = StringHelper.BAD_REQUEST, responseCode = StringHelper.BAD_REQUEST_STATUS_CODE_VALUE, content = @Content)
    @ApiResponse(description = StringHelper.INTERNAL_SERVER_ERROR, responseCode = StringHelper.INTERNAL_SERVER_ERROR_STATUS_CODE_VALUE, content = @Content)
    public void deletePerson(@PathVariable(value = "id") String id){
        personService.deletePerson(id);
    }

}
