package com.jamlech.customerservice.customer.mapper;

import com.jamlech.customerservice.customer.Customer;
import com.jamlech.customerservice.customer.request.CustomerRequest;
import com.jamlech.customerservice.customer.response.CustomerResponse;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public Customer toCustomer(@Valid CustomerRequest request) {
        if (request == null) return null;
        return Customer.builder()
                .id(request.id())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .address(request.address())
                .build();
    }

    public void mergeCustomer(Customer customer, CustomerRequest request) {
        if (StringUtils.isNotBlank(request.firstName())) customer.setFirstName(request.firstName());
        if (StringUtils.isNotBlank(request.lastName())) customer.setLastName(request.lastName());
        if (StringUtils.isNotBlank(request.email())) customer.setEmail(request.email());
        if (request.address() != null) customer.setAddress(request.address());
    }

    public CustomerResponse toCustomerResponse(Customer customer) {
        if (customer == null) return null;
        return CustomerResponse.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .address(customer.getAddress())
                .build();
    }
}
