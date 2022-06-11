package com.management.system.Entity;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "event")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "eventId")
public class Event implements Comparable<Event> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private long eventId;

    @Column(name = "event_name")
    @NotNull
    @NotEmpty
    private String name;

    @Column(name = "location")
    @NotNull
    @NotEmpty
    private String location;

    @Column(name = "description")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "size_limit")
    private int sizeLimit;

    @Column(name = "price")
    @NotNull
    private double price;

    @Column(name = "age_restriction")
    private boolean ageRestriction;

    @Column(name = "going")
    private int going;

    @Column(name = "interested")
    private int interested;

    @Column(name = "start_date")
    @CreationTimestamp
    private LocalDate startDate;

    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDate lastUpdate;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "city_id")

    @JsonBackReference
    private City city;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
                fetch = FetchType.LAZY)
    @JoinTable(name = "user_event",
                joinColumns = @JoinColumn(name = "event_id"),
                inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnore
    private Set<User> users;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Ticket> eventTickets;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "event_category_id")
    @NotNull
    @JsonBackReference
    private EventCategory eventCategory;

    public Event(){}

    public Event(String name, String location, String description, int sizeLimit, double price, boolean ageRestriction) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.sizeLimit = sizeLimit;
        this.price = price;
        this.ageRestriction = ageRestriction;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public List<Ticket> getEventTickets() {
        return eventTickets;
    }

    public void setEventTickets(List<Ticket> eventTickets) {
        this.eventTickets = eventTickets;
    }

    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }

    @Override
    public int compareTo(Event o) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return eventId == event.eventId && sizeLimit == event.sizeLimit && Double.compare(event.price, price) == 0 && ageRestriction == event.ageRestriction && Objects.equals(name, event.name) && Objects.equals(location, event.location) && Objects.equals(description, event.description) && Objects.equals(startDate, event.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, name, location, description, sizeLimit, price, ageRestriction, startDate);
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", sizeLimit=" + sizeLimit +
                ", price=" + price +
                ", ageRestriction=" + ageRestriction +
                ", startDate=" + startDate +
                '}';
    }
}
