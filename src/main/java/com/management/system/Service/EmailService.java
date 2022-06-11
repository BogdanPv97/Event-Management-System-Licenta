package com.management.system.Service;

import com.management.system.Entity.Bill;
import com.management.system.Entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class EmailService{

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserService userService;

    private final String newEventEmailSubject = "New Event In Town";
    private final String invoiceEmailSubject = "Invoice";

    private void sendEmail(List<String> toEmail, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();

        String[] emails = new String[toEmail.size()];

        for(int i = 0 ; i < toEmail.size() ; i++){
            emails[i] = toEmail.get(i);
        }

        for(String str : emails){
            System.out.println(str);
        }

        for(String str : emails) {
            message.setFrom("bogdanpaval97@gmail.com");
            message.setTo(str);

            message.setText(body);
            message.setSubject(subject);

            javaMailSender.send(message);
        }

    }




    public void sendNewsletterEmail(Event event){
       String emailBody =  prepareNewsletterEmail(event);

       sendEmail(userService.getAllEmailsFromSubscription(), newEventEmailSubject, emailBody);

    }


    public void sendInvoiceEmail(Bill bill){

    }


    private String prepareNewsletterEmail(Event event){
        return "Hello, \n" +
                "Check out the new event that will take place in your town \n" +
                event.getName() +
                " on " +
                event.getStartDate() +
                "will be hosted in " + event.getLocation()+
                "\n\n" +
                event.getDescription();
    }
}
