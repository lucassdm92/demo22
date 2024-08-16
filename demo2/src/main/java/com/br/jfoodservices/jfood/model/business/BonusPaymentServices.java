package com.br.jfoodservices.jfood.model.business;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("BonusPaymentServices")
public class BonusPaymentServices implements Payble{
    /**
     *
     */
    @Override
    public void doPaybleProcess() {

        System.out.println("Bonus Payment");
    }
}
