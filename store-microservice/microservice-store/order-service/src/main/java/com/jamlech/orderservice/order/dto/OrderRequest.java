package com.jamlech.orderservice.order.dto;

import com.jamlech.orderservice.orderLineItems.OrderLineItems;
import com.jamlech.orderservice.orderLineItems.dto.OrderLineItemsRequest;
import lombok.Builder;

import java.util.List;

@Builder
public record OrderRequest(
        List<OrderLineItemsRequest> orderLineItems


) {
}
