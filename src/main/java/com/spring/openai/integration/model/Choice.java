package com.spring.openai.integration.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Choice {

    private int index;
    private Message message;
    private String finishReason;

    @JsonProperty("index")
    public int getIndex() {
        return index;
    }

    @JsonProperty("message")
    public Message getMessage() {
        return message;
    }

    @JsonProperty("finish_reason")
    public String getFinishReason() {
        return finishReason;
    }
}

