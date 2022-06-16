package com.management.system.API;

import com.management.system.Entity.DTO.EventDetailsDTO;
import com.management.system.Entity.Event;
import com.management.system.Entity.EventCategory;
import com.management.system.Entity.DTO.EventDTO;
import com.management.system.Service.EmailService;
import com.management.system.Service.EventCategoryService;
import com.management.system.Service.EventService;
import com.management.system.Utility.DTOConverter;
import com.management.system.Utility.SortEvents;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/events")
@CrossOrigin
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private EventCategoryService eventCategoryService;

    @Autowired
    private DTOConverter dtoConverter;

    @Autowired
    private SortEvents sortEvents;


    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Event>> getEvents(){
        return new ResponseEntity<>(sortEvents.sortEventsByNumberOfPeopleGoing(eventService.getAllEvents()), HttpStatus.OK);
    }

    @GetMapping(path="/eventDetails",produces = "application/json")
    public ResponseEntity<List<EventDetailsDTO>> getEventsDetails(){
        return new ResponseEntity<>(eventService.getAllEventsDetails(), HttpStatus.OK);
    }

    @GetMapping(path="/eventDetails/{eventId}")
    public ResponseEntity<EventDetailsDTO> getEventDetailsById(@PathVariable("eventId") long eventId){
        try{
            return new ResponseEntity<>(eventService.getEventDetailsById(eventId), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<Event> getEventById(@PathVariable("eventId") long id){
        if(eventService.getEventById(id) != null){
            return new ResponseEntity<Event>(eventService.getEventById(id), HttpStatus.OK);
        }else{
            throw new IllegalArgumentException("Cannot find event with id" + id);
        }
    }

    @GetMapping("/categories")
    public ResponseEntity<List<EventCategory>> getAllCategories(){
        return new ResponseEntity<>(eventCategoryService.getAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<List<Event>> getEventsByCategory(@PathVariable("categoryId") long categoryId){
        return new ResponseEntity<>(sortEvents.sortEventsByNumberOfPeopleGoing(eventService.getEventsByCategory(categoryId)), HttpStatus.OK);
    }

    @GetMapping("/city/{cityId}")
    public ResponseEntity<List<Event>> getAllEventsFromCity(@PathVariable("cityId") long cityId){
        try{
            return new ResponseEntity<>(eventService.getAllEventsFromCity(cityId), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/top20")
    public ResponseEntity<List<Event>> getTop20Events(){
        try{
            return new ResponseEntity<>(eventService.getTop20Events(), HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PostMapping(path = "/saveEvent",consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> saveEvent(@RequestBody @Valid EventDTO event){
        try{
            eventService.saveEvent(event);
            //        emailService.sendNewsletterEmail(event);
            return new ResponseEntity<String>("Created", HttpStatus.CREATED);
        } catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PutMapping(path = "/update/{eventId}", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Event> updateEvent(@RequestBody @Valid EventDTO event){
        try{
            return new ResponseEntity<>(eventService.saveEvent(event), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PatchMapping(path = "/partialUpdate/{eventId}", consumes = "application/json")
    public ResponseEntity<Event> partialUpdateEvent(@PathVariable("eventId") long id, @RequestBody @Valid Event patchEvent){
        Event event = null;
        try{
            event = eventService.getEventById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (patchEvent.getName() != null) {
            event.setName(patchEvent.getName());
        }
        if (patchEvent.getLocation() != null) {
            event.setLocation(patchEvent.getLocation());
        }
        if (patchEvent.getDescription() != null) {
            event.setDescription(patchEvent.getDescription());
        }
        if (patchEvent.getSizeLimit() > 0) {
            event.setSizeLimit(patchEvent.getSizeLimit());
        }
        if(patchEvent.getPrice() > 0) {
            event.setPrice(patchEvent.getPrice());
        }

        if(event != null) {
            return new ResponseEntity<Event>(event,HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/{eventId}/going/{userId}/increment")
    public ResponseEntity<Integer> incrementGoingCounter(@PathVariable("userId") long userId, @PathVariable("eventId") long eventId){
         Integer count = eventService.incrementGoing(userId, eventId);

         return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @PostMapping("/{eventId}/interested/{userId}/increment")
    public ResponseEntity<Integer> incrementInterestedCounter(@PathVariable("userId") long userId, @PathVariable("eventId") long eventId){
        Integer count = eventService.incrementInterested(userId, eventId);

        return new ResponseEntity<>(count, HttpStatus.OK);
    }


    @DeleteMapping("/deleteEvent/{eventId}")
    public ResponseEntity<String> deleteEvent(@PathVariable("eventId") long id){
        try {
            eventService.deleteEventById(id);
            return new ResponseEntity<>("Event successfully deleted", HttpStatus.OK);
        } catch (EmptyResultDataAccessException e){
            e.printStackTrace();
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
