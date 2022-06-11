package com.management.system.DAO;

import com.management.system.Entity.Ticket;
import com.management.system.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query(nativeQuery = true, value = "select COUNT(ticket_id) from ticket t where t.event_id=?1")
    public int getNoOfTicketsForEvent(long eventId);

    @Query(nativeQuery = true, value = "select * from ticket t where t.user_id=?1")
    public List<Ticket> getAllTicketsForUser(long userId);

    @Query(nativeQuery = true, value = "select * from ticket t where t.ticket_id=?1 and t.user_id=?2")
    public List<Ticket> getTicketForUserById(long ticketId, long userId);
}
