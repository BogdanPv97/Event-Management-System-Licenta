package com.management.system.API;

import com.management.system.Entity.Bill;
import com.management.system.Service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bills")
@CrossOrigin
public class BillController {

    @Autowired
    private BillService billService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Bill>> getBillsForUser(@PathVariable("userId") long userId){
         try{
             return new ResponseEntity<>(billService.getAllBillsForUser(userId), HttpStatus.OK);
         } catch (Exception e) {
             e.printStackTrace();
         }

         return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{userId}/{billId}")
    public ResponseEntity<Bill> getBillForUserById(@PathVariable("userId") long userId, @PathVariable("billId") long billId){
        try{
            return new ResponseEntity<>(billService.getBillForUserById(userId, billId), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

}
