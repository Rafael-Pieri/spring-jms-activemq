package br.com.spring.jms.controller;

import br.com.spring.jms.dto.MessagePostDTO;
import br.com.spring.jms.model.Message;
import br.com.spring.jms.service.ActiveMqService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActiveMqControllerIT {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private ActiveMqService activeMqService;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.context)
                .build();
    }

    @Test
    public void findAllAuthorsShouldReturnOk() throws Exception {
        MessagePostDTO messagePostDTO = new MessagePostDTO("hello world!");

        doNothing().when(this.activeMqService).send(messagePostDTO);

        this.mockMvc
                .perform(post("/api/message").accept(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(messagePostDTO)))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void findAllAuthorsShouldReturnOk1() throws Exception {
        Long aLong = new Long(1);
        Message message = new Message();
        message.setId(aLong);
        message.setDescription("hello world!");
        when(this.activeMqService.findById(aLong)).thenReturn(message);


        this.mockMvc
                .perform(get("/api/message/"+ aLong).accept(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(aLong))
                .andExpect(jsonPath("$.description").value("hello world"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void findAllAuthorsShouldReturnOk2() throws Exception {
        Long aLong = new Long(1);
        Message message = new Message();
        message.setId(aLong);
        message.setDescription("hello world");
        when(this.activeMqService.findAll()).thenReturn(Collections.singletonList(message));


        this.mockMvc
                .perform(get("/api/message").accept(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(aLong))
                .andExpect(jsonPath("$.[0].description").value("hello world"))
                .andExpect(status().isOk())
                .andReturn();
    }
}