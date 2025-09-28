package com.ecommerce.order.api.dto;


import java.util.List;


import com.ecommerce.order.api.entity.Address;
import com.ecommerce.order.api.entity.Customer;
import com.ecommerce.order.api.entity.OrderItem;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class OrderDTO {

    private int totalquantity;
    private double totalprice;
    private String razorPayOrderId;
    private String orderStatus;
    private String razorpayPaymentId;

    @ManyToOne
    Customer customer;

    @ManyToOne
    private Address address;

    @ElementCollection
    private List<OrderItem> orderItems;
}
