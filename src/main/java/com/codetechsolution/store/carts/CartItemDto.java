package com.codetechsolution.store.carts;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemDto {
    private CartItemProductDto product;
    private int quantity;
    private BigDecimal totalPrice;

}
