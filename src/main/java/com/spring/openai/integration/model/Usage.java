package com.spring.openai.integration.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Usage {

    private int promptTokens;
    private int completionTokens;
    private int totalTokens;

    @JsonProperty("prompt_tokens")
    public int getPromptTokens() {
        return promptTokens;
    }

    @JsonProperty("completion_tokens")
    public int getCompletionTokens() {
        return completionTokens;
    }

    @JsonProperty("total_tokens")
    public int getTotalTokens() {
        return totalTokens;
    }
}

