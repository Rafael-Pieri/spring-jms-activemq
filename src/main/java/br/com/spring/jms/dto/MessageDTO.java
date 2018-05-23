package br.com.spring.jms.dto;

import java.io.Serializable;

public class MessageDTO implements Serializable {

    private static final long serialVersionUID = -295422703255886286L;

    private Long id;

    public MessageDTO() {}

    public MessageDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MessageDTO{id = " + id + "}";
    }
}