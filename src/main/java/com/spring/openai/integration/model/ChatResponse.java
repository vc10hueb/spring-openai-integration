package com.spring.openai.integration.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ChatResponse {

    private String id;
    private String object;
    private long created;
    private String model;
    private List<Choice> choices;
    private Usage usage;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("object")
    public String getObject() {
        return object;
    }

    @JsonProperty("created")
    public long getCreated() {
        return created;
    }

    @JsonProperty("model")
    public String getModel() {
        return model;
    }

    @JsonProperty("choices")
    public List<Choice> getChoices() {
        return choices;
    }

    @JsonProperty("usage")
    public Usage getUsage() {
        return usage;
    }
}
