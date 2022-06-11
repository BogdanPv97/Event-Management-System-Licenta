package com.management.system.DAO;

import com.management.system.Entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CityRepository extends JpaRepository<City, Long> {

    @Query(nativeQuery = true, value = "select COUNT(event_id) from city c where c.city_id=?1")
    public Integer getNumberOfEventsInCity(long cityId);

    @Query(nativeQuery = true, value = "select * from city c where c.name=?1")
    public City getCityByName(String cityName);

    @Query(nativeQuery = true, value = "select name from city c where c.city_id=?1")
    public String getCityNameById(long cityId);

    @Query(nativeQuery = true, value = "select * from city c where c.city_id=?1")
    public City getCityById(long cityId);

}
