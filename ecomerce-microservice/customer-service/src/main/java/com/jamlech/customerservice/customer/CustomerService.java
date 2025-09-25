package com.jamlech.customerservice.customer;

import com.jamlech.customerservice.customer.mapper.CustomerMapper;
import com.jamlech.customerservice.customer.request.CustomerRequest;
import com.jamlech.customerservice.customer.response.CustomerResponse;
import com.jamlech.customerservice.exceptions.CustomerNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private  final CustomerRepository customerRepository;
    private  final CustomerMapper customerMapper;

    public String createCustomer(@Valid CustomerRequest request) {
        var customer = customerMapper.toCustomer(request);
        customerRepository.save(customer);
        return customer.getId();
    }

    public void updateCustomer(String customerId, CustomerRequest request) {
        var customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new CustomerNotFoundException("Customer with id ::" + customerId + " not found"));
        customerMapper.mergeCustomer(customer, request);
        customerRepository.save(customer);

    }

    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toCustomerResponse)
                .toList();
    }

    public Boolean existById(String customerId) {
        return customerRepository.findById(customerId)
                .isPresent();
    }

    public CustomerResponse findById(String customerId) {
        return customerRepository.findById(customerId)
                .map(customerMapper::toCustomerResponse)
                .orElseThrow(()-> new CustomerNotFoundException("Customer with id ::" + customerId + " not found"));
    }
    public void deleteById(String customerId) {
        customerRepository.deleteById(customerId);
    }
}
