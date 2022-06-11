package com.management.system.ServiceLayerTest.IntegrationTest;

import com.management.system.DAO.CityRepository;
import com.management.system.DAO.EventCategoryRepository;
import com.management.system.DAO.EventRepository;
import com.management.system.Entity.City;
import com.management.system.Entity.Event;
import com.management.system.Entity.EventCategory;
import com.management.system.Service.EventService;
import com.management.system.SystemApplication;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SystemApplication.class)
@AutoConfigureMockMvc
public class EventServiceIntegrationTest {

    @TestConfiguration
    static class EventServiceTestContextConfig {
        @Bean
        public EventService eventService(){
            return new EventService();
        }
    }

    @Autowired
    private EventService eventService;

    @MockBean
    private EventRepository eventRepository;

    @Test
    public void saveUserTest(){

    }
}
