package com.codetechsolution.store.carts;

import com.codetechsolution.store.products.Product;
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

