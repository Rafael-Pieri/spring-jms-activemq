package br.com.activemq.controller;

import br.com.activemq.dto.ActiveMqMessageDTO;
import br.com.activemq.service.ActiveMqService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/queues")
public class ActiveMqController {

    private final ActiveMqService activeMqService;

    @Autowired
    public ActiveMqController(ActiveMqService activeMqService) {
        this.activeMqService = activeMqService;
    }

    @PostMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void sendMessage(@Valid @RequestBody ActiveMqMessageDTO activeMqMessageDTO) {
        activeMqService.sendMessage(activeMqMessageDTO);
    }

}
