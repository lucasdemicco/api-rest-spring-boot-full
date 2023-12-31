package br.com.rest.Domain.Dtos.V1;

import br.com.rest.Domain.Dtos.V2.PersonV2Dto;
import br.com.rest.Domain.Entities.Person;

import java.io.Serial;
import java.io.Serializable;

public class PersonDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;

    private String firstName;

    private String lastName;

    private String address;
    private String gender;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
