package com.ecommerce.customer.api.dto;


import lombok.*;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CustomerLoginResponse {
    private String token;
    private String email;
}

