package com.management.system.Entity.DTO;

public class EventUserMappingDTO {

    private long userId;
    private long eventId;

    public EventUserMappingDTO(){}

    public EventUserMappingDTO(long userId, long eventId) {
        this.userId = userId;
        this.eventId = eventId;
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
