package com.management.system.Utility;

import com.management.system.Entity.Event;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class SortEvents {

    public List<Event> sortEventsByNumberOfPeopleGoing(List<Event> events){

        Collections.sort(events, new EventComparator());
        Collections.reverse(events);

        return events;
    }
}
