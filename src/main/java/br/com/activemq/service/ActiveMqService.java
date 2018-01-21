package br.com.activemq.service;

import br.com.activemq.dto.ActiveMqMessageDTO;
import br.com.activemq.exception.ActiveMqException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class ActiveMqService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActiveMqService.class);
    private final JmsTemplate jmsTemplate;
    @Value("${activemq.queue.helloworld}")
    private String queue;

    @Autowired
    public ActiveMqService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage(ActiveMqMessageDTO activeMqMessageDTO) {
        try {
            jmsTemplate.convertAndSend(queue, activeMqMessageDTO);
        } catch (Exception exception) {
            throw new ActiveMqException(exception.getMessage());
        }
    }

    @JmsListener(destination = "${activemq.queue.helloworld}")
    public void receiveMessage(ActiveMqMessageDTO activeMqMessageDTO) {
        LOGGER.info("received message = {}", activeMqMessageDTO);
    }

}
