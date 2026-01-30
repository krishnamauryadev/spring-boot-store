package com.codetechsolution.store.repositores;

import com.codetechsolution.store.entities.Order;
import com.codetechsolution.store.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @EntityGraph(attributePaths = "orderItems.product")
    @Query("select o from Order o where o.customer = :customer")
    List<Order> getOrderByCustomer(@Param("customer") User customer);


    @
    EntityGraph(attributePaths = "orderItems.product")
    @Query("select o from Order o where o.id = :orderId")
    Optional<Order> getOrderWithItems(@Param("orderId") Long orderId);
}