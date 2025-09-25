package com.jamlech.orderservice.order;

import com.jamlech.orderservice.order.dto.OrderRequest;
import com.jamlech.orderservice.order.mapper.OrderMapper;
import com.jamlech.orderservice.orderLineItems.OrderLineItemsRepository;
import com.jamlech.orderservice.orderLineItems.mapper.OrderLineItemsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderLineItemsRepository orderLineItemsRepository;
    private  final OrderLineItemsMapper orderLineItemsMapper;
    private  final OrderMapper orderMapper;

    public void placeOrder(OrderRequest request) {
        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderLineItemsList(request.orderLineItems()
                        .stream()
                        .map(orderLineItemsMapper::mapToEntity)
                        .toList())
                .build();
        log.info("Order created");
       orderRepository.save(order);
    }
}
