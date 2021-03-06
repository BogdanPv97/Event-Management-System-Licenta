package com.management.system.Entity.DTO;

public class CityDTO {

    private String name;

    public CityDTO(){}

    public CityDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CityDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
