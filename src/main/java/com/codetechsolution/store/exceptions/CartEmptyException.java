package com.codetechsolution.store.exceptions;

public class CartEmptyException extends RuntimeException {
    public CartEmptyException(){
        super("Cart is empty.");
    }
}
