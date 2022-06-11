package com.management.system.Entity.DTO;

import com.management.system.Entity.Ticket;

import java.util.List;

public class PaymentInfoDTO {


    private int amount;
    private String currency;


    public PaymentInfoDTO(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }


}
