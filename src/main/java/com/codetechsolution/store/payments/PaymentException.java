package com.codetechsolution.store.payments;

public class PaymentException extends RuntimeException {
    public PaymentException(String message){
        super(message);
    }
}
