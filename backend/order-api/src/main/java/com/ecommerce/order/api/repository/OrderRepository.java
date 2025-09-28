package com.ecommerce.order.api.repository;

import com.ecommerce.order.api.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    public Order findByRazorPayOrderId(String razorPayOrderId);
    public List<Order> findByEmail(String email);
    //public Order save(OrderDTO order);
}

