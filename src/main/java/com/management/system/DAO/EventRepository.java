package com.management.system.DAO;

import com.management.system.Entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event,Long> {

    @Query(nativeQuery = true, value = "select * from event e where e.event_category_id = ?1")
    public List<Event> getEventsByCategory(long id);

    @Query(nativeQuery = true, value = "select * from event e where e.city_id = ?1")
    public List<Event> getAllEventsFromCity(long cityId);

    @Query(nativeQuery = true, value = "Select * from event e where e.name=?1")
    public List<Event> findEventByName(String name);
}
