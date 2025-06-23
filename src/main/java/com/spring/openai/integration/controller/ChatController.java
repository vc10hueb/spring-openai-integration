package com.spring.openai.integration.controller;

import com.spring.openai.integration.model.ChatRequest;
import com.spring.openai.integration.model.ContentPart;
import com.spring.openai.integration.model.Message;
import com.spring.openai.integration.service.ChatService;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping(value = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Retry(name = "openaiRetry", fallbackMethod = "fallbackResponse")
    @CircuitBreaker(name = "openaiCircuitBreaker", fallbackMethod = "fallbackResponse")
    public String chatWithImage(
            @RequestParam("prompt") String prompt,
            @RequestParam("image") MultipartFile image
    ) throws IOException {
        String base64 = Base64.encodeBase64String(image.getBytes());
        String mimeType = image.getContentType(); // e.g., image/jpeg
        String base64Url = "data:" + mimeType + ";base64," + base64;

        // Build message
        Message message = Message.builder().role("user").content(List.of(
                ContentPart.text(prompt),
                ContentPart.image(base64Url))).build();

        // Build request
        ChatRequest request = ChatRequest.builder().model("gpt-4o-mini").temperature(0.2).messages(List.of(message)).build();
        return chatService.sendChat(request);
    }

    @GetMapping(value = "/textPrompt", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Retry(name = "openaiRetry", fallbackMethod = "fallbackResponse")
    @CircuitBreaker(name = "openaiCircuitBreaker", fallbackMethod = "fallbackResponse")
    public String chatWithTextOnly(
            @RequestParam("prompt") String prompt
    ) throws IOException {
        // Build message
        Message message = Message.builder().role("user").content(List.of(ContentPart.text(prompt))).build();

        // Build request
        ChatRequest request = ChatRequest.builder().model("gpt-4o-mini").temperature(0.2).messages(List.of(message)).build();
        return chatService.sendChat(request);
    }

    // Fallback method must match original method signature + Throwable
    public String fallbackResponse(String prompt, MultipartFile image, Throwable t) {
        return "OpenAI service is currently unavailable. Please try again later.";
    }
}
