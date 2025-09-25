package com.jamlech.customerservice.customer.response;

import com.jamlech.customerservice.customer.address.Address;
import lombok.Builder;

@Builder
public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email,
        Address address
) {
}
