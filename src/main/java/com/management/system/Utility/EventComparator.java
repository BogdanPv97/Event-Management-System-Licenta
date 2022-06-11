package com.management.system.Utility;

import com.management.system.Entity.Event;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class EventComparator implements Comparator<Event> {

    @Override
    public int compare(Event o1, Event o2) {
        if(o1.getGoing() == o2.getGoing())
            return 0;
        else if(o1.getGoing() < o2.getGoing())
            return -1;
        else
            return 1;
    }


}
