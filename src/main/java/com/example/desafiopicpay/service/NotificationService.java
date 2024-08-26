package com.example.desafiopicpay.service;

import com.example.desafiopicpay.dto.EmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${app.notification-service}")
    private String apiKey;


    public boolean sendNotification(EmailDTO emailDTO){
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(apiKey, emailDTO, String.class);
            return true;
        } catch (HttpServerErrorException e){
            return false;
        }
    }
}
