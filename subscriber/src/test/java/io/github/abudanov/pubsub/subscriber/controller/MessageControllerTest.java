package io.github.abudanov.pubsub.subscriber.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.abudanov.pubsub.commondata.dto.MessageDto;
import io.github.abudanov.pubsub.commondata.value.Action;
import io.github.abudanov.pubsub.subscriber.service.MessageProcessingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MessageController.class)
class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageProcessingService service;

    @Test
    void create() throws Exception {
        MessageDto messageDto = new MessageDto(1, 79999999999L, Action.PURCHASE, System.currentTimeMillis() / 1000);

        doNothing().when(service).process(messageDto);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(messageDto);

        mockMvc.perform(post("/messages").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}