package com.codetechsolution.store.mappers;

import com.codetechsolution.store.dtos.CartDto;
import com.codetechsolution.store.dtos.CartItemDto;
import com.codetechsolution.store.dtos.CartItemProductDto;
import com.codetechsolution.store.entities.Cart;
import com.codetechsolution.store.entities.CartItem;
import com.codetechsolution.store.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target = "totalPrice", expression="java(cart.getTotalPrice())")
    CartDto toDto(Cart cart);
    @Mapping(target = "totalPrice",expression = "java(item.getTotalPrice())")
    CartItemDto toDto(CartItem item);
    CartItemProductDto toDto(Product product);
}

