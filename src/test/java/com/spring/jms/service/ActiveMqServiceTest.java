package com.spring.jms.service;

import com.spring.jms.dto.MessagePostDTO;
import com.spring.jms.model.Message;
import com.spring.jms.repository.MessageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jms.core.JmsTemplate;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ActiveMqServiceTest {

    @InjectMocks
    private ActiveMqService activeMqService;

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private JmsTemplate jmsTemplate;

    @Captor
    private ArgumentCaptor<Object> objectArgumentCaptor;

    @Captor
    private ArgumentCaptor<String> stringArgumentCaptor;

    @Test
    public void shouldSendMessage() {
        when(this.messageRepository.save(anyObject())).thenReturn(Optional.of(new Message()));

        doNothing().when(this.jmsTemplate).convertAndSend(stringArgumentCaptor.capture(), objectArgumentCaptor.capture());

        this.activeMqService.send(new MessagePostDTO());

        verify(this.messageRepository, times(1)).save(anyObject());
        verify(jmsTemplate, times(1)).convertAndSend(stringArgumentCaptor.capture(), objectArgumentCaptor.capture());
    }

    @Test
    public void shouldFindAllMessages() {
        when(this.messageRepository.findAll()).thenReturn(Optional.of(new ArrayList<>()));

        this.activeMqService.findAll();

        verify(this.messageRepository, times(1)).findAll();
    }

    @Test
    public void shouldFindMessageById() {
        final Long authorId = 1L;

        when(this.messageRepository.findById(authorId)).thenReturn(Optional.of(new Message()));

        this.activeMqService.findById(authorId);

        verify(this.messageRepository, times(1)).findById(authorId);
    }
}