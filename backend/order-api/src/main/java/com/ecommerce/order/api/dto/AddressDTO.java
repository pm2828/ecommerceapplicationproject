package com.ecommerce.order.api.dto;


import com.ecommerce.order.api.entity.Customer;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class AddressDTO {

    private String houseno;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    @ManyToOne
    private Customer customer;
}
