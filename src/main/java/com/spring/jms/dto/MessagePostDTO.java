package com.spring.jms.dto;

public class MessagePostDTO {

    private String message;

    public MessagePostDTO() {}

    public MessagePostDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}