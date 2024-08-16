package com.br.jfoodservices.jfood.controller;

import com.br.jfoodservices.jfood.model.business.CardPaymentServices;
import com.br.jfoodservices.jfood.model.business.PaymentProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentProcess paymentCard;

    @PostMapping(path = "/doCardPayment")
    public void doCardPayment() {
        paymentCard.cardPayment();
    }

    @PostMapping(path = "/doCashPayment")
    public void doCashPayment() {
       // paymentProcess.cashPayment();
    }

    @PostMapping(path = "/doBonusPayment")
    public void doBonusPayment() {
       // paymentProcess.bonusPayment();
    }
}
