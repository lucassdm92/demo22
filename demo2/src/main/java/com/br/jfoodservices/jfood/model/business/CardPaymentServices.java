package com.br.jfoodservices.jfood.model.business;

import com.br.jfoodservices.jfood.controller.CardValidation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("CardPaymentServices")
public class CardPaymentServices implements Payble, CardValidation {

    /**
     *
     */
    @Override
    public void doPaybleProcess() {
        //Do the process of card
        System.out.print("CardPayment");
    }

    /**
     *
     */
    @Override
    public void validateCard() {
        System.out.print("Validate Card");
    }
}
