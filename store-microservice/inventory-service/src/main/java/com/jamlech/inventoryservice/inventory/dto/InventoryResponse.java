package com.jamlech.inventoryservice.inventory.dto;

import lombok.Builder;

@Builder
public  record InventoryResponse(
        String skuCode,
        boolean isInStock
) {
}
