//package com.ecommerce.order.api.service;
//
//import com.ecommerce.order.api.entity.CustomerEntity;
//import com.ecommerce.order.api.repository.CustomerRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//
//@Service
//@RequiredArgsConstructor
//public class CustomerService {
//
//    private final CustomerRepository customerRepo;
//
//    public CustomerEntity getOrCreateCustomer(OrderRequestDTO.CustomerRequest customerDTO) {
//        return customerRepo.findByEmail(customerDTO.getEmail())
//                .orElseGet(() -> {
//                    CustomerEntity customer = new CustomerEntity();
//                    customer.setName(customerDTO.getName());
//                    customer.setEmail(customerDTO.getEmail());
//                    customer.setPhno(Long.valueOf(customerDTO.getPhno()));
//                    customer.setPwd(customerDTO.getPwd());
//                    customer.setPwdUpdated(LocalDateTime.now());
//                    return customerRepo.save(customer);
//                });
//    }
//
//}
