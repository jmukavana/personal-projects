package com.jamlech.orderservice.order;

import com.jamlech.orderservice.order.dto.OrderRequest;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor

public class OrderController {
    private final OrderService orderService;

    @PostMapping("")
    @TimeLimiter(name = "inventory")
    public CompletableFuture<ResponseEntity<String>> placeOrder(@RequestBody OrderRequest request) {
        var orderPlaced =orderService.placeOrder(request);
        return CompletableFuture.supplyAsync(()->  new ResponseEntity<>(orderPlaced, HttpStatus.CREATED));

    }

}
