package br.com.rest.Domain.Dtos.V2;

import br.com.rest.Domain.Dtos.V1.PersonDto;

import java.io.Serial;
import java.io.Serializable;

public class PersonV2Dto extends PersonDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String birthDay;

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }
}
