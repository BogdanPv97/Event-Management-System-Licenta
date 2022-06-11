package com.management.system.DAO;


import com.management.system.Entity.EventGoing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventGoingRepository extends JpaRepository<EventGoing, Long> {

    @Query(nativeQuery = true, value = "select * from event_going e where e.user_id=?1 and e.event_id=?2")
    public EventGoing findRecord(long userId, long eventId);

    @Query(nativeQuery = true, value = "select COUNT(user_id) from event_going where event_id=?1")
    public int getGoingCounterForEvent(long eventId);

    @Query(nativeQuery = true, value = "select event_id from event_going where user_id=?1")
    public List<Long> getGoingEventsForUserId(long userId);
}
