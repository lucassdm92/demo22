package com.br.jfoodservices.jfood.model.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class PaymentProcess {

    @Autowired
    @Qualifier("CardPaymentServices")
    private Payble cardPaymentServices;

    @Autowired
    @Qualifier("BonusPaymentServices")
    private Payble bonusPaymentServices;


    public void cardPayment() {
        cardPaymentServices.doPaybleProcess();
        bonusPaymentServices.doPaybleProcess();
    }

    public void bonusPayment() {

  //      this.doPaymentProcess(bonusPaymentServices);

    }

    public void cashPayment() {
    //    doPaymentProcess(cashPaymentServices);
    }

}
