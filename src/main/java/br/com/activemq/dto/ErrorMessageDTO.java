package br.com.activemq.dto;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Arrays;
import java.util.List;

public class ErrorMessageDTO {

    private String message;

    @JsonInclude(Include.NON_EMPTY)
    private List<String> errors;

    public ErrorMessageDTO(String message, List<String> errors) {
        this.message = message;
        this.errors = errors;
    }

    public ErrorMessageDTO(String message, String error) {
        this.message = message;
        this.errors = Arrays.asList(error);
    }

    public ErrorMessageDTO(String message) {
        this.message = message;
    }

    public ErrorMessageDTO() {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}


