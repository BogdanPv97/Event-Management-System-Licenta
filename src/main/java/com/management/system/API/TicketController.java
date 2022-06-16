package com.management.system.API;

import com.management.system.Entity.DTO.TicketDTO;
import com.management.system.Entity.Ticket;
import com.management.system.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tickets")
@CrossOrigin
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/{userId}")

    public ResponseEntity<List<TicketDTO>> getTicketsFromUser(@PathVariable("userId") long userId){
        try{
            return new ResponseEntity<>(ticketService.getAllTicketsForUser(userId), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{userId}/{ticketId}")

    public ResponseEntity<Ticket> getTicketForUserById(@PathVariable("userId") long userId, @PathVariable("ticketId") long ticketId){
        try{
            return new ResponseEntity<>(ticketService.getTicketForUserById(userId, ticketId), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/eventTickets/{event_id}")

    public ResponseEntity<Integer> getNoOfTicketsForEvent(@PathVariable("event_id") long eventId){
        try{
            return new ResponseEntity<>(ticketService.getNoOfTicketsForEvent(eventId), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PostMapping(path = "/saveTicket",consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)

    public void saveTicket(@RequestBody @Valid Ticket ticket){
        try {
            ticketService.saveTicket(ticket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/deleteTicket/{ticketId}")
    public void deleteTicketById(@PathVariable("ticketId") long ticketId){
        try{
            ticketService.deleteTicketById(ticketId);
        } catch(EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
    }

}
