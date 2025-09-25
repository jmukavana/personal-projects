package com.jamlech.customerservice.customer;

import com.jamlech.customerservice.customer.address.Address;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customer")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class Customer {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
}
