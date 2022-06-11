package com.management.system.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "event_category")
public class EventCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_category_id")
    private long eventCategoryId;

    @Column(name = "category_name")
    @NotNull
    @NotEmpty
    private String categoryName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eventCategory", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Event> events;

    public EventCategory(){}

    public long getEventCategoryId() {
        return eventCategoryId;
    }

    public void setEventCategoryId(long eventCategoryId) {
        this.eventCategoryId = eventCategoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
