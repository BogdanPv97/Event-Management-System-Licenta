package com.management.system.Service;

import com.management.system.DAO.EventRepository;
import com.management.system.Entity.DTO.EventDTO;
import com.management.system.Entity.DTO.EventDetailsDTO;
import com.management.system.Entity.Event;
import com.management.system.Entity.User;
import com.management.system.Utility.DTOConverter;
import com.management.system.Utility.EventComparator;
import com.management.system.Utility.SortEvents;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EventService{
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EventGoingService eventGoingService;
    @Autowired
    private EventInterestedService eventInterestedService;
    @Autowired
    private DTOConverter dtoConverter;
    @Autowired
    private SortEvents sortEvents;
    @Autowired
    private EventCategoryService eventCategoryService;
    @Autowired
    private CityService cityService;


    public Event persistEvent(Event event){
        return eventRepository.save(event);
    }

    public Event saveEvent(EventDTO event) {


        Event eventEntity = dtoConverter.eventDtoToEntityConverter(event);
        eventEntity.setCity(cityService.getCityById(event.getCityId()));
        eventEntity.setEventCategory(eventCategoryService.getCategoryById(event.getCategoryId()));

        return eventRepository.save(eventEntity);

    }


    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public EventDetailsDTO getEventDetailsById(long eventId){
        List<Event> events = getAllEvents();
        EventDetailsDTO eventDetails = null;

        for(Event ev: events){
            if(ev.getEventId() == eventId){
                EventDetailsDTO eventDTO = dtoConverter.convertEventToEventDetailsDTO(ev);
                eventDTO.setCategory(eventCategoryService.getCategoryNameById(ev.getEventCategory().getEventCategoryId()));
                eventDTO.setCity(cityService.getCityNameById(ev.getCity().getCityId()));

                eventDetails = eventDTO;
            }
        }

        return eventDetails;
    }

    public List<EventDetailsDTO> getAllEventsDetails(){
        List<Event> events = getAllEvents();
        List<EventDetailsDTO> eventDetails = new ArrayList<>();

        for(Event ev : events){
            EventDetailsDTO eventDTO = dtoConverter.convertEventToEventDetailsDTO(ev);
            eventDTO.setCategory(eventCategoryService.getCategoryNameById(ev.getEventCategory().getEventCategoryId()));
            eventDTO.setCity(cityService.getCityNameById(ev.getCity().getCityId()));

            eventDetails.add(eventDTO);
        }

        return eventDetails;
    }


    public Event getEventById(long eventId){
        return eventRepository.findById(eventId).get();
    }

    public Event getEventByName(String name){
        List<Event> events = eventRepository.findEventByName(name);
        if(events.size() > 0) {
            return events.get(0);
        }

        return null;
    }


    public List<Event> getEventsByCategory(long categoryId){
       return eventRepository.getEventsByCategory(categoryId);
    }





    public List<Event> getTopFiveByCategory(long categoryId){
        List<Event> categoryEvents = sortEvents.sortEventsByNumberOfPeopleGoing(eventRepository.getEventsByCategory(categoryId));

        if(categoryEvents.size() <= 5){
            return categoryEvents;
        }else{
            List<Event> topFive = categoryEvents.subList(0,5);
            return topFive;
        }
    }


    public Integer incrementGoing(long userId, long eventId){
        Event event = getEventById(eventId);

        if(eventGoingService.findRecord(userId, eventId) == null){
            eventGoingService.save(userId, eventId);
            event.setGoing(eventGoingService.getGoingCounterForEvent(eventId));
        }else{
            eventGoingService.deleteGoingMapping(userId, eventId);
            event.setGoing(eventGoingService.getGoingCounterForEvent(eventId));
        }

        persistEvent(event);

        return eventGoingService.getGoingCounterForEvent(eventId);
    }


    public Integer incrementInterested(long userId, long eventId){
        Event event = getEventById(eventId);

        if(eventInterestedService.findRecord(userId,eventId) == null){
            eventInterestedService.save(userId, eventId);
            event.setInterested(eventInterestedService.getInterestedCounterForEvent(eventId));
        }else{
            eventInterestedService.deleteInterestedMapping(userId, eventId);
            event.setInterested(eventInterestedService.getInterestedCounterForEvent(eventId));
        }

        persistEvent(event);

        return eventInterestedService.getInterestedCounterForEvent(eventId);
    }


    public List<Event> getAllEventsFromCity(long cityId){
        return eventRepository.getAllEventsFromCity(cityId);
    }


    public void deleteEvent(Event event){
        eventRepository.delete(event);
    }


    public void deleteEventById(long eventId){
        eventRepository.deleteById(eventId);
    }


    public List<Event> getGoingEventsForUserId(long userId){
        List<Long> eventIds= eventGoingService.getGoingEventsForUserId(userId);
        List<Event> events = new ArrayList<>();

        for(Long id : eventIds){
            events.add(getEventById(id));
        }

        return events;
    }

    public List<Event> getInterestedEventsForUserId(long userId){
        List<Long> eventIds= eventInterestedService.getInterestedEventsForUserId(userId);
        List<Event> events = new ArrayList<>();

        for(Long id : eventIds){
            events.add(getEventById(id));
        }

        return events;
    }

    public List<Event> getTop20Events(){
        List<Event> events = getAllEvents();
        sortEvents.sortEventsByNumberOfPeopleGoing(events);

        List<Event> topEvents = new ArrayList<>();
        int count = 0;
        for(Event ev : events) {
            if(count<20){
                topEvents.add(ev);
                count++;
            }else
                break;
        }

        return topEvents;
    }


}
