package com.management.system.Utility;

import com.management.system.Entity.City;
import com.management.system.Entity.DTO.*;
import com.management.system.Entity.Event;
import com.management.system.Entity.Ticket;
import com.management.system.Entity.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DTOConverter {

    @Autowired
    private ModelMapper modelMapper;


    public Event eventDtoToEntityConverter(EventDTO eventDTO){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        Event event = new Event();
        event = modelMapper.map(eventDTO, Event.class);

        return event;
    }

    public City cityDtoToEntityConverter(CityDTO cityDTO){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        City city = new City();
        city = modelMapper.map(cityDTO, City.class);

        return city;
    }

    public User userDtoToEntityConverter(UserDetailsDTO userDetailsDTO){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        User user = new User();
        user = modelMapper.map(userDetailsDTO, User.class);

        return user;
    }

    public User userDtoToEntitySave(UserDTO userDTO){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        User user = new User();
        user = modelMapper.map(userDTO, User.class);

        return user;
    }

    public UserDetailsDTO convertUserToUserDetailsDTO(User user){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        userDetailsDTO = modelMapper.map(user, UserDetailsDTO.class);
        return userDetailsDTO;
    }

    public TicketDTO convertTicketToTicketDTO(Ticket ticket){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO = modelMapper.map(ticket, TicketDTO.class);
        return ticketDTO;
    }

    public EventDetailsDTO convertEventToEventDetailsDTO(Event event){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        EventDetailsDTO eventDetailsDTO = new EventDetailsDTO();
        eventDetailsDTO = modelMapper.map(event, EventDetailsDTO.class);
        return eventDetailsDTO;
    }
}
