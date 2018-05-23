package br.com.spring.jms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ActiveMqException extends RuntimeException {

    public ActiveMqException(String message) {
        super(message);
    }
}