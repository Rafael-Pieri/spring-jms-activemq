package br.com.spring.jms.service;

import br.com.spring.jms.dto.MessageDTO;
import br.com.spring.jms.dto.MessagePostDTO;
import br.com.spring.jms.exception.ActiveMqException;
import br.com.spring.jms.exception.MessageNotFoundException;
import br.com.spring.jms.model.Message;
import br.com.spring.jms.repository.MessageRepository;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class ActiveMqService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActiveMqService.class.getName());

    private final JmsTemplate jmsTemplate;

    private final MessageRepository messageRepository;

    @Value("${activemq.queue.name}")
    private String queue;

    @Autowired
    public ActiveMqService(JmsTemplate jmsTemplate, MessageRepository messageRepository) {
        this.jmsTemplate = jmsTemplate;
        this.messageRepository = messageRepository;
    }

    public void send(MessagePostDTO messagePostDTO) {
        try {
            Message message = new Message(messagePostDTO.getMessage());
            Optional<Message> messageSaved = messageRepository.save(message);
            MessageDTO messageDTO = new MessageDTO(messageSaved.orElseThrow(RuntimeException::new).getId());
            jmsTemplate.convertAndSend(queue, messageDTO);
        } catch (Exception exception) {
            throw new ActiveMqException(exception.getMessage());
        }
    }

    @JmsListener(destination = "${activemq.queue.name}")
    public void receive(MessageDTO messageDTO) {
        LOGGER.info("received message: {}", messageDTO);

        Optional<Message> optionalMessage = messageRepository.findById(messageDTO.getId());

        if (!optionalMessage.isPresent()) {
            throw new MessageNotFoundException("Message not found");
        }

        Message message = optionalMessage.get();
        message.setReceivedAt(ZonedDateTime.now(ZoneOffset.UTC));
        messageRepository.save(message);
    }

    public Message findById(Long id) {
        Optional<Message> message = messageRepository.findById(id);

        if (message.isPresent()) {
            return message.get();
        }

        throw new MessageNotFoundException("Message not found");
    }

    public Collection<Message> findAll() {
        return messageRepository.findAll().orElseGet(ArrayList::new);
    }
}