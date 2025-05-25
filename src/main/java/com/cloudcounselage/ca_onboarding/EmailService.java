package com.cloudcounselage.ca_onboarding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${app.base-url}")
    private String baseUrl;

    public void sendWelcomeEmail(String to, String name, String utmLink) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject("Welcome to CA Onboarding!");
            String trackingLink = baseUrl + "/api/utm/track?utmLink=" + URLEncoder.encode(utmLink, StandardCharsets.UTF_8.toString());
            message.setText("Hello " + name + ",\n\nWelcome to our platform! Click here to visit: " + trackingLink);
            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email: " + e.getMessage(), e);
        }
    }
}