package com.management.system.Service;

import com.management.system.DAO.EventGoingRepository;
import com.management.system.Entity.Event;
import com.management.system.Entity.EventGoing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventGoingService{

    @Autowired
    private EventGoingRepository eventGoingRepository;


    public EventGoing save(long userId, long eventId) {
        EventGoing eventGoing = new EventGoing();

        eventGoing.setUserId(userId);
        eventGoing.setEventId(eventId);

        return eventGoingRepository.save(eventGoing);
    }


    public void deleteGoingMapping(long userId, long eventId) {
        EventGoing eventGoing = findRecord(userId, eventId);

        eventGoingRepository.delete(eventGoing);
    }

    public List<Long> getGoingEventsForUserId(long userId){
        return eventGoingRepository.getGoingEventsForUserId(userId);
    }


    public EventGoing findRecord(long userId, long eventId) {
        return eventGoingRepository.findRecord(userId, eventId);
    }


    public int getGoingCounterForEvent(long eventId){
        return eventGoingRepository.getGoingCounterForEvent(eventId);
    }
}
