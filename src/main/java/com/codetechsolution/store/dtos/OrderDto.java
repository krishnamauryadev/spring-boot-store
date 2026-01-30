package com.codetechsolution.store.dtos;

import com.codetechsolution.store.entities.PaymentStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private PaymentStatus status;
    private LocalDateTime createdAt;
    private List<OrderItemDto> orderItems;
    private BigDecimal totalPrice;
}
