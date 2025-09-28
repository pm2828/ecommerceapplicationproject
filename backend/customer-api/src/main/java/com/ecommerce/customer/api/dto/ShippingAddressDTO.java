package com.ecommerce.customer.api.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShippingAddressDTO {
    private Long id;
    private String houseNum;
    private String street;
    private String city;
    private String state;
    private String zipcode;
    private String country;
}
