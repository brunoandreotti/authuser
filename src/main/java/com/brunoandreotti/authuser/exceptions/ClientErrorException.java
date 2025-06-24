package com.brunoandreotti.authuser.exceptions;

import lombok.Getter;
import org.springframework.web.client.RestClientException;

@Getter
public class ClientErrorException extends RuntimeException {

    private final Integer status;

    public ClientErrorException(String message, Integer status) {
        super(message);
        this.status = status;
    }
    public ClientErrorException(String message, RestClientException ex) { super(message, ex);
        this.status = null;
    }

}
