package com.management.system.API;

import com.management.system.Entity.DTO.PaymentInfoDTO;
import com.management.system.Entity.DTO.PaymentStatusDTO;
import com.management.system.Service.BillService;
import com.management.system.Service.CheckoutService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checkout")
@CrossOrigin
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;
    @Autowired
    private BillService billService;


    @PostMapping("/payment-intent")
    public ResponseEntity<String> createPaymentIntent(@RequestBody PaymentInfoDTO paymentInfo) throws StripeException{
        PaymentIntent paymentIntent = checkoutService.createPaymentIntent(paymentInfo);

        String paymentStr = paymentIntent.toJson();

        return new ResponseEntity<>(paymentStr, HttpStatus.OK);
    }

    @PostMapping("/validatePayment")
    public ResponseEntity<Boolean> validateBillPayment(@RequestBody PaymentStatusDTO paymentStatusDTO){
        try{
            billService.validatePayment(paymentStatusDTO);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
