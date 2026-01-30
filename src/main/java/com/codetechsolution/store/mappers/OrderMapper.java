package com.codetechsolution.store.mappers;

import com.codetechsolution.store.dtos.OrderDto;
import com.codetechsolution.store.entities.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = OrderItemMapper.class)
public interface OrderMapper {
    OrderDto toDto(Order order);
}
