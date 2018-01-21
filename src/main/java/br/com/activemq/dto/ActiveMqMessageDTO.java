package br.com.activemq.dto;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

public class ActiveMqMessageDTO implements Serializable {

    private static final long serialVersionUID = -295422703255886286L;

    @NotNull
    private String message;

    public ActiveMqMessageDTO() {

    }

    public ActiveMqMessageDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ActiveMqMessageDTO{" +
            "message='" + message + '\'' +
            '}';
    }

}


