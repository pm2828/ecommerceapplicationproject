package com.ecommerce.order.api.dto;

import lombok.Data;

@Data
public class OrderResponse {

    private String razorpayOrderId;
    private String orderStatus;
    private String orderTrackingNumber;

}
