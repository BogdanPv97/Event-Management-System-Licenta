package com.management.system.Service;

import com.management.system.DAO.EventCategoryRepository;
import com.management.system.Entity.EventCategory;
import com.management.system.Utility.SortEvents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventCategoryService{

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @Autowired
    private SortEvents sortEvents;

    public List<EventCategory> getAllCategories(){
        List<EventCategory> categoryEvents =  eventCategoryRepository.findAll();

        for(EventCategory category : categoryEvents){
            sortEvents.sortEventsByNumberOfPeopleGoing(category.getEvents());
        }

        return categoryEvents;
    }

    public String getCategoryNameById(long categoryId){
        return eventCategoryRepository.getCategoryNameById(categoryId);
    }

    public EventCategory getCategoryById(long categoryId){
        return eventCategoryRepository.getCategoryById(categoryId);
    }
}
