package br.com.rest.Domain.Dtos.V2;

import br.com.rest.Domain.Dtos.V1.PersonDto;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class PersonV2Dto extends PersonDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Date birthDay;

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
}
