package com.codetechsolution.store.mappers;

import com.codetechsolution.store.dtos.OrderProductDto;
import com.codetechsolution.store.entities.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderProductMapper {
    OrderProductDto toDto(Product product);
}

