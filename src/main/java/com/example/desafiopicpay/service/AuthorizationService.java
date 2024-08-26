package com.example.desafiopicpay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthorizationService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${app.authorization-service}")
    private String apiKey;

    public boolean authorizeTransaction(){
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(apiKey, String.class);
            return true;
        } catch (HttpClientErrorException e) {
            return false;
        }
    }
}
