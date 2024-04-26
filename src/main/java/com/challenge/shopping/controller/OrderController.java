package com.challenge.shopping.controller;


import com.challenge.shopping.entity.Order;
import com.challenge.shopping.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/{customerId}/products")
    public ResponseEntity<Order> placeOrderWithProductIds(@PathVariable Long customerId, @RequestBody List<Long> productIds) {
        Order order = orderService.placeOrder(customerId, productIds);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/{orderCode}")
    public ResponseEntity<Order> getOrderForCode(@PathVariable String orderCode) {
        Order order = orderService.getOrderForCode(orderCode);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Order>> getAllOrdersForCustomer(@PathVariable Long customerId) {
        List<Order> orders = orderService.getAllOrdersForCustomer(customerId);
        return ResponseEntity.ok(orders);
    }
}
