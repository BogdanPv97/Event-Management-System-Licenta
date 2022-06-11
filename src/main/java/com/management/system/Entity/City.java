package com.management.system.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private long cityId;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "city", cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonManagedReference
    private List<User> users;

    @OneToMany(mappedBy = "city", cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonManagedReference
    private List<Event> events;

    public City(){}

    public City(String name){
        this.name = name;
    }

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return name;
    }

}
