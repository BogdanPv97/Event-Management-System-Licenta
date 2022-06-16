import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { EventDetails } from 'src/app/common/event-details';
import { PaymentInfo } from 'src/app/common/payment-info';
import { CheckoutService } from 'src/app/service/checkout.service';
import { EventService } from 'src/app/service/event.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-checkout-payment',
  templateUrl: './checkout-payment.component.html',
  styleUrls: ['./checkout-payment.component.css'],
})
export class CheckoutPaymentComponent implements OnInit {
  ticketToBeBought: EventDetails;

  checkoutFormGroup: FormGroup;

  stripe = Stripe(environment.stripePublishableKey);
  paymentInfo: PaymentInfo = new PaymentInfo();
  cardElement: any;
  displayError: any = '';

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private checkoutService: CheckoutService,
    private eventService: EventService
  ) {}

  ngOnInit(): void {
    this.ticketToBeBought = this.eventService.getEventForTicket();
    this.setupStripePaymentForm();

    this.checkoutFormGroup = this.formBuilder.group({
      creditCard: this.formBuilder.group({}),
    });
  }

  setupStripePaymentForm() {
    var elements = this.stripe.elements();

    this.cardElement = elements.create('card', { hidePostalCode: true });
    this.cardElement.mount('#card-element');
    this.cardElement.on('change', (event: { complete: any; error: any }) => {
      this.displayError = document.getElementById('card-errors');
      if (event.complete) {
        this.displayError.textContent = '';
      } else if (event.error) {
        this.displayError.textContent = event.error.message;
      }
    });
  }

  onSubmit() {
    let event = this.ticketToBeBought;
    console.log(event.price);
    this.paymentInfo.amount = event.price * 100;
    this.paymentInfo.currency = 'RON';

    if (this.displayError.textContent === '') {
      this.checkoutService
        .createPaymentIntent(this.paymentInfo)
        .subscribe((paymentIntentResponse) => {
          this.stripe
            .confirmCardPayment(
              paymentIntentResponse.client_secret,
              {
                payment_method: {
                  card: this.cardElement,
                },
              },
              { handleActions: false }
            )
            .then(
              function (result) {
                if (result.error) {
                  alert(`There was an error: ${result.error.message}`);
                } else {
                  this.checkoutService.placeOrder(event.eventId, 30).subscribe({
                    next: (response) => {
                      alert(`Your order has been received.`);
                      this.resetCart();
                    },
                    error: (err) => {
                      alert(`There was an error: ${err.message}`);
                    },
                  });
                }
              }.bind(this)
            );
        });
    } else {
      this.checkoutFormGroup.markAllAsTouched();
      return;
    }
  }

  resetCart() {
    this.checkoutFormGroup.reset();
  }
}
