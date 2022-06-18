package com.management.system.WebLayerTest.IntegrationTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.management.system.DAO.EventRepository;
import com.management.system.Entity.DTO.EventDTO;
import com.management.system.Entity.Event;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.time.LocalDate;
import java.util.Objects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@EnableWebMvc
@ExtendWith(MockitoExtension.class)
public class EventIntegration {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private MockMvc mockMvc;

    private JacksonTester<Event> eventJacksonTester;

    @BeforeEach
    public void setup(){
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void getAllEvents() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:9191/events").
                accept(MediaType.APPLICATION_JSON)).
                andDo(print()).
                andExpect(status().isOk());
    }

    @Test
    public void getAllCategories() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:9191/events/categories").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getEventDetails() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:9191/events/eventDetails").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getTop20Events() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:9191/events/top20").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void saveEvent() throws Exception {
        Faker faker = new Faker();
        LocalDate date = LocalDate.now();
        EventDTO event = new EventDTO(faker.name().name(),faker.name().name(), faker.name().name(), faker.number().randomDigit(), faker.number().randomDouble(1,5,20), faker.name().name(), faker.bool().bool(), date);
        mockMvc.perform(post("http://localhost:9191/events/saveEvent")
                .contentType(MediaType.APPLICATION_JSON).content(Objects.requireNonNull(transformToJson(event)))).andExpect(status().isCreated());
    }

    @Test
    public void deleteEvent() throws Exception {
        mockMvc.perform(delete("http://localhost:9191/events/deleteEvent/{id}",1)).andExpect(status().isOk());
    }

    public static String transformToJson(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
