package com.management.system.Entity.DTO;

import java.time.LocalDate;

public class EventDetailsDTO {

    private long eventId;
    private String name;
    private String location;
    private String description;
    private String imageUrl;
    private int sizeLimit;
    private double price;
    private boolean ageRestriction;
    private int going;
    private int interested;
    private LocalDate startDate;
    private LocalDate lastUpdate;
    private String city;
    private String category;

    public EventDetailsDTO() {}

    public EventDetailsDTO(long eventId,String name, String location, String description, String imageUrl, int sizeLimit, double price, boolean ageRestriction, int going, int interested, LocalDate startDate, LocalDate lastUpdate) {
        this.eventId = eventId;
        this.name=name;
        this.location = location;
        this.description = description;
        this.imageUrl = imageUrl;
        this.sizeLimit = sizeLimit;
        this.price = price;
        this.ageRestriction = ageRestriction;
        this.going = going;
        this.interested = interested;
        this.startDate = startDate;
        this.lastUpdate = lastUpdate;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getSizeLimit() {
        return sizeLimit;
    }

    public void setSizeLimit(int sizeLimit) {
        this.sizeLimit = sizeLimit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(boolean ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public int getGoing() {
        return going;
    }

    public void setGoing(int going) {
        this.going = going;
    }

    public int getInterested() {
        return interested;
    }

    public void setInterested(int interested) {
        this.interested = interested;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
