package com.ecommerce.customer.api.dto;


import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDTO {
    private Long id;
    private String name;
    private String pwd;
    private String email;
    private Long phno;
    private List<ShippingAddressDTO> addresses;
}

