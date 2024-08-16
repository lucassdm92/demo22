package com.br.jfoodservices.jfood.model.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class PaymentProcessTest {


    int[] c = {};

    @InjectMocks
    private PaymentProcess paymentProcess;

    @Mock()
    private Payble payble;

    @Test
    void cardPayment() {

        paymentProcess.cardPayment();
    }

    @Test
    void bonusPayment() {
    }

    @Test
    void cashPayment() {
    }
}