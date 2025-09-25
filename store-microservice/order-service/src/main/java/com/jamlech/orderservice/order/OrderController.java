package com.jamlech.orderservice.order;

import com.jamlech.orderservice.order.dto.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("")
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequest request) {
        orderService.placeOrder(request);
        return new ResponseEntity<>("Order Placed successfully", HttpStatus.CREATED);

    }

}
