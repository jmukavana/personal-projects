package com.jamlech.orderservice.orderLineItems.mapper;

import com.jamlech.orderservice.orderLineItems.OrderLineItems;
import com.jamlech.orderservice.orderLineItems.dto.OrderLineItemsRequest;
import org.springframework.stereotype.Component;

@Component
public class OrderLineItemsMapper {
    public OrderLineItems mapToEntity(OrderLineItemsRequest request) {
        return OrderLineItems.builder()
                .skuCode(request.skuCode())
                .price(request.price())
                .quantity(request.quantity())
                .build();
    }
}
