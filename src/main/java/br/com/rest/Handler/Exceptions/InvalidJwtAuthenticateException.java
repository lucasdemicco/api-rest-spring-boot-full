package br.com.rest.Handler.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.naming.AuthenticationException;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidJwtAuthenticateException extends AuthenticationException {

    private static final Long serialVersionUID = 1L;

    public InvalidJwtAuthenticateException(String ex){
        super(ex);
    }
}
