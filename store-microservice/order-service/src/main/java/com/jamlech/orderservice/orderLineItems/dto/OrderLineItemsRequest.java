package com.jamlech.orderservice.orderLineItems.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record OrderLineItemsRequest(
       String skuCode,
       BigDecimal price,
       Integer quantity
) {
}
