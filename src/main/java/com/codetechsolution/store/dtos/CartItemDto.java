package com.codetechsolution.store.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemDto {
    private CartItemProductDto product;
    private int quantity;
    private BigDecimal totalPrice;

}
