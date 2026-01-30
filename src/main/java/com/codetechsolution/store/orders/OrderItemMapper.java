package com.codetechsolution.store.orders;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = OrderProductMapper.class)
public interface OrderItemMapper {
    OrderItemDto toDto(OrderItem orderItem);
}

