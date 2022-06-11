package com.management.system.Entity.DTO;

import com.management.system.Entity.City;
import com.management.system.Entity.EventCategory;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


public class EventDTO {

    @NotNull(message = "Event name cannot be null")
    @NotEmpty
    private String name;

    @NotNull(message = "Event location cannot be null")
    @NotEmpty
    private String location;

    @NotNull(message = "Event description cannot be null")
    @NotEmpty
    private String description;

    @NotNull(message = "Event size limit cannot be null")
    private int sizeLimit;

    @NotNull(message = "Event price cannot be null")
    private double price;

    @NotNull(message = "Event image url cannot be null")
    @NotEmpty
    private String imageUrl;

    private boolean ageRestriction;

    private LocalDate startDate;

    private long cityId;

    private long categoryId;

    public EventDTO(){}

    public EventDTO(String name, String location, String description, int sizeLimit, double price, String imageUrl, boolean ageRestriction, LocalDate startDate) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.sizeLimit = sizeLimit;
        this.price = price;
        this.imageUrl = imageUrl;
        this.ageRestriction = ageRestriction;
        this.startDate = startDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(boolean ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }
}
