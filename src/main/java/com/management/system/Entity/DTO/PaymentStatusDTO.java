package com.management.system.Entity.DTO;

public class PaymentStatusDTO {

    private long billId;
    private boolean validatedPayment;

    public PaymentStatusDTO(){}

    public PaymentStatusDTO(long billId, boolean validatedPayment){
        this.billId = billId;
        this.validatedPayment = validatedPayment;
    }

    public long getBillId() {
        return billId;
    }

    public void setBillId(long billId) {
        this.billId = billId;
    }

    public boolean isValidatedPayment() {
        return validatedPayment;
    }

    public void setValidatedPayment(boolean validatedPayment) {
        this.validatedPayment = validatedPayment;
    }
}
