package Mocks;

import br.com.rest.Domain.Dtos.V1.PersonDto;

import java.util.ArrayList;
import java.util.List;

public class PersonMock {

    public PersonDto mockPerson(){
        PersonDto dto = new PersonDto();
        dto.setAddress("Mock address");
        dto.setFirstName("Mock Name");
        dto.setLastName("Mock Last Name");
        dto.setGender("Mock gender");
        return dto;
    }

    public List<PersonDto> mockListPerson(){
        List<PersonDto> newList = new ArrayList<PersonDto>();
        for(int i = 0; i < 10; i++){
            newList.add(mockPerson());
        }
        return newList;
    }
}
