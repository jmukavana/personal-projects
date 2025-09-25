package com.jamlech.orderservice.order;

import com.jamlech.orderservice.order.dto.InventoryResponse;
import com.jamlech.orderservice.order.dto.OrderRequest;
import com.jamlech.orderservice.order.mapper.OrderMapper;
import com.jamlech.orderservice.orderLineItems.OrderLineItems;
import com.jamlech.orderservice.orderLineItems.OrderLineItemsRepository;
import com.jamlech.orderservice.orderLineItems.mapper.OrderLineItemsMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderLineItemsRepository orderLineItemsRepository;
    private final OrderLineItemsMapper orderLineItemsMapper;
    private final OrderMapper orderMapper;
    private final WebClient.Builder webClientbuilder;
    @Transactional
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")

    public String placeOrder(OrderRequest request) {
        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderLineItemsList(request.orderLineItems()
                        .stream()
                        .map(orderLineItemsMapper::mapToEntity)
                        .toList())
                .build();
        log.info("Order created");
        List<String> skuCodes = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

        InventoryResponse[] inventoryResponses = webClientbuilder.build()
                .get()
                .uri("http://inventory-service/api/inventory",
                        uriBuilder -> uriBuilder
                                .queryParam("skuCode", skuCodes)
                                .build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();
        boolean allProductsInStock = Arrays.stream(inventoryResponses)
                .allMatch(InventoryResponse::isInStock);
        if (!allProductsInStock) throw new IllegalArgumentException("Product is not in stock");
        orderRepository.save(order);
        log.info("Order placed successfully");
        return "Order placed successfully";
    }

    @Transactional
    public String fallbackMethod(OrderRequest orderRequest, RuntimeException runtimeException) {
        log.error("Inventory service is not available");
        return ("Inventory service is not available.Please try again later");
    }
}
