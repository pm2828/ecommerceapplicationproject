package com.ecommerce.order.api.repository;


import com.ecommerce.order.api.dto.OrderItemDTO;
import com.ecommerce.order.api.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    void save(OrderItemDTO item);

}
