package com.management.system.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "event_interested")
public class EventInterested {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_interested_id")
    private long eventInterestedId;

    @Column(name = "user_id")
    @NotNull
    private long userId;

    @Column(name = "event_id")
    @NotNull
    private long eventId;

    public EventInterested(){}

    public long getEventInterestedId() {
        return eventInterestedId;
    }

    public void setEventInterestedId(long eventInterestedId) {
        this.eventInterestedId = eventInterestedId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }
}
