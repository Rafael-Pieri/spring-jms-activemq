package br.com.activemq.dto;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

public class MessageDTO implements Serializable {

    private static final long serialVersionUID = -295422703255886286L;

    private String message;

    public MessageDTO() {

    }

    public MessageDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}