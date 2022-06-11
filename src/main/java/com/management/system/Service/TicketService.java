package com.management.system.Service;

import com.management.system.DAO.TicketRepository;
import com.management.system.Entity.DTO.TicketDTO;
import com.management.system.Entity.Event;
import com.management.system.Entity.Ticket;
import com.management.system.Utility.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService{

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private DTOConverter dtoConverter;


    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }


    public Ticket getTicketByID(long id) {
        return ticketRepository.findById(id).get();
    }


    public void saveTicket(Ticket ticket){
        ticketRepository.save(ticket);
    }

    @Transactional
    public boolean saveBulkTickets(List<Ticket> tickets){
        boolean success = true;

        try{
            ticketRepository.saveAll(tickets);
        } catch (Exception e){
            success = false;
            e.printStackTrace();
        }

        return success;
    }


    public void deleteTicketById(long id){
        ticketRepository.deleteById(id);
    }


    public List<TicketDTO> getAllTicketsForUser(long userId){
      List<Ticket> tickets = ticketRepository.getAllTicketsForUser(userId);
      List<TicketDTO> ticketsDTO = new ArrayList<>();

      for(Ticket ticket : tickets) {
          TicketDTO dto = dtoConverter.convertTicketToTicketDTO(ticket);
          dto.setBillId(ticket.getBill().getBillId());
          dto.setUserId(ticket.getUser().getUserId());
          dto.setEventId(ticket.getEvent().getEventId());
          ticketsDTO.add(dto);
      }

      return ticketsDTO;
    }


    public Ticket getTicketForUserById(long userId, long ticketId){
        List<Ticket> tickets = ticketRepository.getTicketForUserById(ticketId, userId);
        if(tickets.size() > 0) {
            return tickets.get(0);
        }

        return null;
    }

    public int getNoOfTicketsForEvent(long eventId) {
        return ticketRepository.getNoOfTicketsForEvent(eventId);
    }

    public List<TicketDTO> convertTicketsToDTO(List<Ticket> tickets){
        List<TicketDTO> ticketDTOs = new ArrayList<>();
        for(Ticket ticket : tickets){
            ticketDTOs.add(dtoConverter.convertTicketToTicketDTO(ticket));
        }

        return ticketDTOs;
    }
}
