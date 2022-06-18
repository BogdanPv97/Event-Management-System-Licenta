package com.management.system.Service;

import com.management.system.Entity.Bill;
import com.management.system.Entity.DTO.PaymentInfoDTO;
import com.management.system.Entity.Ticket;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CheckoutService {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private BillService billService;

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;


    public CheckoutService(@Value("${stripe.key.secret}") String secretKey) {
        //initialize Stripe API wih secret key
        Stripe.apiKey = secretKey;
    }

    public PaymentIntent createPaymentIntent(PaymentInfoDTO paymentInfo) throws StripeException {

        List<String> paymentMethodTypes = new ArrayList<>();

        paymentMethodTypes.add("card");

        Map<String, Object> params = new HashMap<>();
        params.put("amount", paymentInfo.getAmount());
        params.put("currency", paymentInfo.getCurrency());
        params.put("payment_method_types", paymentMethodTypes);

        return PaymentIntent.create(params);
    }

    public Bill placeOrder(long eventId, long userId){
        Bill bill = new Bill();
        bill.setUser(userService.getUserById(userId));
        bill.setAmount(eventService.getEventById(eventId).getPrice());
        bill.setIssuedDate( LocalDate.now());
        bill.setPaid(true);
        bill.setDescription("Ticket for event " + eventService.getEventById(eventId).getName());
        billService.saveBill(bill);

        Ticket ticket = new Ticket();
        ticket.setBill(bill);
        ticket.setEvent(eventService.getEventById(eventId));
        ticket.setUser(userService.getUserById(userId));

        ticketService.saveTicket(ticket);

        return bill;

    }
}
