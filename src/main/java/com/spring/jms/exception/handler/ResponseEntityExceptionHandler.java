package com.spring.jms.exception.handler;

import com.spring.jms.dto.ErrorMessageDTO;
import com.spring.jms.exception.ActiveMqException;
import com.spring.jms.exception.MessageNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ResponseEntityExceptionHandler {

    private static final String INVALID_REQUEST_OBJECT = "Invalid Request Object";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessageDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<String> errors = exception.getBindingResult()
            .getFieldErrors()
            .stream().map(s -> s.getField()
                .concat(" ")
                .concat(s.getDefaultMessage()))
            .collect(Collectors.toList());
        return new ErrorMessageDTO(INVALID_REQUEST_OBJECT, errors);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ResponseBody
    public ErrorMessageDTO handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException exception) {
        return new ErrorMessageDTO(INVALID_REQUEST_OBJECT, "Unsupported content type: " + exception.getContentType());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessageDTO handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        return new ErrorMessageDTO(INVALID_REQUEST_OBJECT, exception.getMostSpecificCause().getMessage());
    }

    @ExceptionHandler(ActiveMqException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorMessageDTO handleActiveMqException(ActiveMqException exception) {
        return new ErrorMessageDTO(INVALID_REQUEST_OBJECT, exception.getMessage());
    }

    @ExceptionHandler(MessageNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorMessageDTO handleMessageNotFoundException(MessageNotFoundException exception) {
        return new ErrorMessageDTO(INVALID_REQUEST_OBJECT, exception.getMessage());
    }
}