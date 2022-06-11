package com.management.system.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "event_going")
public class EventGoing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_going_id")
    private long eventGoingId;

    @Column(name = "user_id")
    @NotNull
    private long userId;

    @Column(name = "event_id")
    @NotNull
    private long eventId;

    public EventGoing(){}

    public long getEventGoingId() {
        return eventGoingId;
    }

    public void setEventGoingId(long eventGoingId) {
        this.eventGoingId = eventGoingId;
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
