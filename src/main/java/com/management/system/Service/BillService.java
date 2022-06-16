package com.management.system.Service;

import com.management.system.DAO.BillRepository;
import com.management.system.DAO.UserRepository;
import com.management.system.Entity.Bill;
import com.management.system.Entity.DTO.PaymentStatusDTO;
import com.management.system.Entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillService{

    @Autowired
    private BillRepository billRepository;
    @Autowired
    private UserService userService;

    public Bill saveBill(Bill bill){
        return billRepository.save(bill);
    }

    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }


    public Bill getBillById(long id) {
        return billRepository.findById(id).get();
    }


    public List<Bill> getAllBillsForUser(long userId){
        List<Bill> allBills = getAllBills();
        List<Bill> userBills = new ArrayList<>();

        for(Bill bill : allBills){
            if(bill.getUser().getUserId() == userId)
                userBills.add(bill);
        }

        return userBills;
    }


    public Bill getBillForUserById(long userId, long billId){

        List<Bill> allBills = getAllBillsForUser(userId);

        for(Bill bill : allBills){
            if(bill.getBillId() == billId)
                return bill;
        }
        return null;
    }


    public void deleteBill(Bill bill) {
        billRepository.delete(bill);
    }


    public void createBill(long userId, List<Ticket> tickets, int amount){
        Bill bill = new Bill();

        bill.setUser(userService.getUserById(userId));
        bill.setDescription(createDescription(tickets));
        bill.setAmount(amount);
        bill.setTickets(tickets);
        bill.setPaid(false);

        billRepository.save(bill);
    }

    public String createDescription(List<Ticket> tickets) {
        return "";
    }

    public void validatePayment(PaymentStatusDTO paymentStatusDTO){

    }
}
