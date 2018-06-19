package br.com.spring.jms.service;

import br.com.spring.jms.model.Message;
import br.com.spring.jms.repository.MessageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ActiveMqServiceTest {

    @InjectMocks
    private ActiveMqService activeMqService;

    @Mock
    private MessageRepository messageRepository;

    @Test
    public void shouldFindAllAuthors() {
        when(messageRepository.findAll()).thenReturn(Optional.of(new ArrayList<>()));

        activeMqService.findAll();

        verify(messageRepository, times(1)).findAll();
    }

    @Test
    public void shouldFindAuthorById() {
        final long authorId = 1L;

        when(messageRepository.findById(authorId)).thenReturn(Optional.of(new Message()));

        activeMqService.findById(authorId);

        verify(messageRepository, times(1)).findById(authorId);
    }

}
