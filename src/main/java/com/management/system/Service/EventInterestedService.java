package com.management.system.Service;

import com.management.system.DAO.EventInterestedRepository;
import com.management.system.Entity.Event;
import com.management.system.Entity.EventInterested;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventInterestedService{

    @Autowired
    private EventInterestedRepository eventInterestedRepository;

    public EventInterested save(long userId, long eventId) {
        EventInterested eventInterested = new EventInterested();

        eventInterested.setUserId(userId);
        eventInterested.setEventId(eventId);

        return eventInterestedRepository.save(eventInterested);
    }


    public void deleteInterestedMapping(long userId, long eventId) {
        EventInterested eventInterested = findRecord(userId, eventId);

        eventInterestedRepository.delete(eventInterested);

    }



    public List<Long> getInterestedEventsForUserId(long userId){
        return eventInterestedRepository.getInterestedEventsForUserId(userId);
    }


    public EventInterested findRecord(long userId, long eventId) {
        return eventInterestedRepository.findRecord(userId, eventId);
    }


    public int getInterestedCounterForEvent(long eventId) {
        return eventInterestedRepository.getInterestedCounterForEvent(eventId);
    }
}
