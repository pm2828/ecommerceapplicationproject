package com.ecommerce.customer.api.service.impl;



// EmailServiceImpl.java

import com.ecommerce.customer.api.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username:pramodmandlik28@gmail.com}")
    private String from;

    @Override
    public boolean sendEmail(String subject, String body, String to) {
        MimeMessage message= mailSender.createMimeMessage();
        MimeMessageHelper helper= new MimeMessageHelper(message);

        try{

            helper.setSubject(subject);
            helper.setText(body,true);
            helper.setTo(to);

            mailSender.send(message);
            return true;

        }catch(Exception e){
            e.printStackTrace();
        }


        return false;
    }
}


