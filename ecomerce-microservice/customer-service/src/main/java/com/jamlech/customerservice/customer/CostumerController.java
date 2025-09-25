package com.jamlech.customerservice.customer;

import com.jamlech.customerservice.customer.request.CustomerRequest;
import com.jamlech.customerservice.customer.response.CustomerResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CostumerController {
    private  final CustomerService customerService;

    @PostMapping("")
    public ResponseEntity<String> createCustomer(
            @Valid @RequestBody CustomerRequest request) {
        return new ResponseEntity<>(customerService.createCustomer(request), HttpStatus.CREATED);

    }

    @PutMapping("customer/{customerId}")
    public ResponseEntity<Void> updateCustomer(
            @PathVariable("customerId") String customerId,
            @RequestBody CustomerRequest request) {
        customerService.updateCustomer(customerId, request);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }

    @GetMapping("")
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("customer/exists/{customerId}")
    public ResponseEntity<Boolean> existById(
            @PathVariable("customerId") String customerId
    ) {
       return new ResponseEntity<>(customerService.existById(customerId), HttpStatus.OK);
    }
    @GetMapping("customer/{customerId}")
    public ResponseEntity<CustomerResponse> findById(
            @PathVariable("customerId") String customerId
    ) {
        return new ResponseEntity<>(customerService.findById(customerId), HttpStatus.OK);
    }

    @DeleteMapping("/customer/{customerId}")
    public ResponseEntity<Void> deleteById(String customerId) {
        customerService.deleteById(customerId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


}
