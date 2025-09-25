package com.jamlech.orderservice.order.dto;

import lombok.Builder;

@Builder
public  record InventoryResponse(
        String skuCode,
        boolean isInStock
) {
}
