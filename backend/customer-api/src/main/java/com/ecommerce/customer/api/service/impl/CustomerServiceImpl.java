package com.ecommerce.customer.api.service.impl;

import com.ecommerce.customer.api.dto.CustomerDTO;
import com.ecommerce.customer.api.dto.CustomerLoginResponse;
import com.ecommerce.customer.api.entity.CustomerEntity;
import com.ecommerce.customer.api.exception.BadRequestException;
import com.ecommerce.customer.api.exception.ResourceNotFoundException;
import com.ecommerce.customer.api.repository.CustomerRepository;
import com.ecommerce.customer.api.service.CustomerService;
import com.ecommerce.customer.api.service.EmailService;
import com.ecommerce.customer.api.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;
    private  final EmailService emailService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public CustomerLoginResponse login(String email, String password) {

        // 1. Find customer by email
        CustomerEntity customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with email: " + email));

        // 2. Validate password (hash check later)
        if (!customer.getPwd().equals(password)) {
            throw new BadRequestException("Invalid email or password");
        }

        // 3. Map to DTO
        CustomerLoginResponse loginResponse = modelMapper.map(customer, CustomerLoginResponse.class);

        // 4. Generate JWT token
        String token = jwtService.generateToken(customer.getEmail());
        loginResponse.setToken(token);

        return loginResponse;
    }


    @Override
    public void resetPassword(String email, String newPassword) {
        CustomerEntity customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with email: " + email));

        // TODO: hash password
        customer.setPwd(passwordEncoder.encode(newPassword));
        customer.setPwdUpdated(LocalDateTime.now());
        customerRepository.save(customer);
    }

    @Override
    public CustomerDTO getCustomerByEmail(String email) {
        CustomerEntity customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with email: " + email));

        return modelMapper.map(customer, CustomerDTO.class);
    }

    @Override
    public CustomerDTO updateCustomer(Long customerId, CustomerDTO customerDTO) {
        CustomerEntity entity = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + customerId));

        entity.setName(customerDTO.getName());
        entity.setPhno(customerDTO.getPhno());
        CustomerEntity updated = customerRepository.save(entity);
        return modelMapper.map(updated, CustomerDTO.class);
    }

    @Override
    public CustomerDTO getCustomerByID(Long customerId) {

       CustomerEntity customer= customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer not found with id "+ customerId));
        return modelMapper.map(customer, CustomerDTO.class);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        CustomerEntity entity = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + customerId));
        customerRepository.delete(entity);
    }

    @Override
    public CustomerDTO registerCustomer(CustomerDTO customerDTO) {
        if (customerRepository.findByEmail(customerDTO.getEmail()).isPresent()) {
            throw new BadRequestException("Email already registered: " + customerDTO.getEmail());
        }

        CustomerEntity entity = modelMapper.map(customerDTO, CustomerEntity.class);
        entity.setPwd(generateRandomPwd(7)); // temporary password
        entity.setPwdUpdated(LocalDateTime.now());

        if (entity.getAddresses() != null) {
            entity.getAddresses().forEach(address -> address.setCustomer(entity));
        }

        CustomerEntity saved = customerRepository.save(entity);

        // Send email
        String subject = "Your Account Created";
        String body = "<h2>Your temporary password: " + entity.getPwd() + "</h2>";
        emailService.sendEmail(subject, body, entity.getEmail());

        // Convert saved entity back to DTO
        return modelMapper.map(saved, CustomerDTO.class);
    }



    @Override
    public String generateRandomPwd(int pwdLength) {
        Random random = new Random();

        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789$#@%&*";

        StringBuffer buffer = new StringBuffer(pwdLength);
        for (int i = 0; i < chars.length(); i++) {

            int randomIndex = random.nextInt(chars.length() - 1);
            char ch = chars.charAt(randomIndex);
            buffer.append(ch);
        }
        return buffer.toString();
    }

}

