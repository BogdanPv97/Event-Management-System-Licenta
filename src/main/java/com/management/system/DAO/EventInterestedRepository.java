package com.management.system.DAO;

import com.management.system.Entity.EventGoing;
import com.management.system.Entity.EventInterested;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventInterestedRepository extends JpaRepository<EventInterested, Long> {

    @Query(nativeQuery = true, value = "select * from event_interested e where e.user_id=?1 and e.event_id=?2")
    public EventInterested findRecord(long userId, long eventId);

    @Query(nativeQuery = true, value = "select COUNT(user_id) from event_interested where event_id=?1")
    public int getInterestedCounterForEvent(long eventId);

    @Query(nativeQuery = true, value = "select event_id from event_interested where user_id=?1")
    public List<Long> getInterestedEventsForUserId(long userId);
}
