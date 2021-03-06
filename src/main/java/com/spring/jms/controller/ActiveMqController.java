package com.spring.jms.controller;

import com.spring.jms.dto.MessagePostDTO;
import com.spring.jms.model.Message;
import com.spring.jms.service.ActiveMqService;
import java.util.Collection;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/message")
public class ActiveMqController {

    private final ActiveMqService activeMqService;

    @Autowired
    public ActiveMqController(ActiveMqService activeMqService) {
        this.activeMqService = activeMqService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void send(@Valid @RequestBody MessagePostDTO messagePostDTO) {
        activeMqService.send(messagePostDTO);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Message findById(@PathVariable("id") Long id) {
        return activeMqService.findById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Collection<Message> findAll() {
        return activeMqService.findAll();
    }
}