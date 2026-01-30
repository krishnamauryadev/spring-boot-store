package com.codetechsolution.store.mappers;

import com.codetechsolution.store.dtos.OrderItemDto;
import com.codetechsolution.store.entities.OrderItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = OrderProductMapper.class)
public interface OrderItemMapper {
    OrderItemDto toDto(OrderItem orderItem);
}

