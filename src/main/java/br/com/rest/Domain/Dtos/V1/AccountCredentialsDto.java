package br.com.rest.Domain.Dtos.V1;

import java.io.Serializable;

public class AccountCredentialsDto implements Serializable {
    private static final Long serialVersionUID = 1L;

    private String userName;
    private String password;

    public AccountCredentialsDto() {
    }

    public AccountCredentialsDto(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
