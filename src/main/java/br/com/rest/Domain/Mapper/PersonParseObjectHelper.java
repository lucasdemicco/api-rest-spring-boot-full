package br.com.rest.Domain.Mapper;

import br.com.rest.Domain.Dtos.V1.PersonDto;
import br.com.rest.Domain.Dtos.V2.PersonV2Dto;
import br.com.rest.Domain.Entities.Person;

public class PersonParseObjectHelper {
    public static Person mapToEntity(PersonDto dto){
        Person newPerson = new Person();
        newPerson.setFirstName(dto.getFirstName());
        newPerson.setId(dto.getId());
        newPerson.setLastName(dto.getLastName());
        newPerson.setAddress(dto.getAddress());
        newPerson.setGender(dto.getGender());

        return newPerson;
    }

    public static Person mapToEntityV2(PersonV2Dto dto){
        Person newPerson = new Person();
        newPerson.setFirstName(dto.getFirstName());
        newPerson.setId(dto.getId());
        newPerson.setLastName(dto.getLastName());
        newPerson.setAddress(dto.getAddress());
        newPerson.setGender(dto.getGender());
        newPerson.setBithDay(dto.getBirthDay());

        return newPerson;
    }

    public static PersonDto mapToPersonDto(Person person){
        PersonDto dto = new PersonDto();
        dto.setFirstName(person.getFirstName());
        dto.setId(person.getId());
        dto.setLastName(person.getLastName());
        dto.setAddress(person.getAddress());
        dto.setGender(person.getGender());
        return dto;
    }

    public static PersonV2Dto mapToPersonDtoV2(Person person){
        PersonV2Dto dto = new PersonV2Dto();
        dto.setFirstName(person.getFirstName());
        dto.setId(person.getId());
        dto.setLastName(person.getLastName());
        dto.setAddress(person.getAddress());
        dto.setGender(person.getGender());
        dto.setBirthDay(person.getBithDay());
        return dto;
    }
}
