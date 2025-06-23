package com.spring.openai.integration.service;

import com.spring.openai.integration.model.ChatRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChatService {
    private static final Logger logger
            = LoggerFactory.getLogger(ChatService.class);

    @Value("${OPENAI_API_KEY}")
    private String apiKey;

    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    public String sendChat(ChatRequest request) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        HttpEntity<ChatRequest> entity = new HttpEntity<>(request, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(API_URL, entity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            logger.debug(response.getBody());
            return response.getBody();
        } else {
            throw new RuntimeException("API error: " + response.getStatusCode());
        }
    }
}

