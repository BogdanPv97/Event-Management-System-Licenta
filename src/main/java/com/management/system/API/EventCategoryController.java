package com.management.system.API;

import com.management.system.Entity.Event;
import com.management.system.Entity.EventCategory;
import com.management.system.Service.EventCategoryService;
import com.management.system.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@CrossOrigin
public class EventCategoryController {
    @Autowired
    private EventCategoryService eventCategoryService;
    @Autowired
    private EventService eventService;

    @GetMapping("/categoryName/{eventId}")
    public ResponseEntity<EventCategory> getCategoryNameByEventId(@PathVariable(value = "eventId") long eventId){
        EventCategory evCategory = eventService.getEventById(eventId).getEventCategory();

        try{
            return new ResponseEntity<>(evCategory, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
