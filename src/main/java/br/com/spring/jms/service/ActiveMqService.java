package br.com.spring.jms.service;

import br.com.spring.jms.dto.MessageDTO;
import br.com.spring.jms.exception.ActiveMqException;
import br.com.spring.jms.exception.MessageNotFoundException;
import br.com.spring.jms.model.Message;
import br.com.spring.jms.repository.MessageRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class ActiveMqService {

    private final JmsTemplate jmsTemplate;

    private final MessageRepository messageRepository;

    @Value("${activemq.queue.name}")
    private String queue;

    @Autowired
    public ActiveMqService(JmsTemplate jmsTemplate, MessageRepository messageRepository) {
        this.jmsTemplate = jmsTemplate;
        this.messageRepository = messageRepository;
    }

    public void sendMessage(MessageDTO messageDTO) {
        try {
            jmsTemplate.convertAndSend(queue, messageDTO);
        } catch (Exception exception) {
            throw new ActiveMqException(exception.getMessage());
        }
    }

    @JmsListener(destination = "${activemq.queue.name}")
    public void receiveMessage(MessageDTO messageDTO) {
        messageRepository.save(new Message(messageDTO.getMessage()));
    }

    public Message findById(Long id) {
        Optional<Message> message = messageRepository.findById(id);

        if (message.isPresent()) {
            return message.get();
        }

        throw new MessageNotFoundException("Message not found");
    }

    public Collection<Message> findAll() {
        Optional<List<Message>> all = messageRepository.findAll();

        List<Message> messages = all.get();

        messages.size();

        return messageRepository.findAll().orElseGet(ArrayList::new);
    }
}