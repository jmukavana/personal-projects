package com.jamlech.inventoryservice.inventory;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "inventories")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "sku_code")
    private String skuCode;
    private Integer quantity;
}
