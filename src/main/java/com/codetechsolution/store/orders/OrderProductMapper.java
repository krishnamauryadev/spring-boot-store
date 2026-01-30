package com.codetechsolution.store.orders;

import com.codetechsolution.store.products.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderProductMapper {
    OrderProductDto toDto(Product product);
}

