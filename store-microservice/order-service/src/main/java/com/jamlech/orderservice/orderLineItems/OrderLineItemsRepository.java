package com.jamlech.orderservice.orderLineItems;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface OrderLineItemsRepository extends JpaRepository<OrderLineItems, String> {
}