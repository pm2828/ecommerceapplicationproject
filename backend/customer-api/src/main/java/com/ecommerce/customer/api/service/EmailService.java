package com.ecommerce.customer.api.service;

public interface EmailService {
    boolean sendEmail(String to, String subject, String body);
}
