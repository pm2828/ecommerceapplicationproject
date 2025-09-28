package com.ecommerce.customer.api.service;


import com.ecommerce.customer.api.dto.CustomerDTO;


import com.ecommerce.customer.api.dto.CustomerLoginResponse;

public interface CustomerService {

    CustomerDTO registerCustomer(CustomerDTO customerDTO); // registration + email

    CustomerLoginResponse login(String email, String password); // login placeholder

    void resetPassword(String email, String newPassword);

    CustomerDTO getCustomerByEmail(String email);

    CustomerDTO updateCustomer(Long customerId, CustomerDTO customerDTO);

    CustomerDTO getCustomerByID(Long customerId);

    void deleteCustomer(Long customerId);

    String generateRandomPwd(int pwdLength);
}

