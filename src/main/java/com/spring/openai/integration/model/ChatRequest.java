package com.spring.openai.integration.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatRequest {

    private final String model;
    private final List<Message> messages;
    private final Integer n;
    private final Double temperature;

    private ChatRequest(Builder builder) {
        this.model = builder.model;
        this.messages = builder.messages;
        this.n = builder.n;
        this.temperature = builder.temperature;
    }

    @JsonProperty("model")
    public String getModel() {
        return model;
    }

    @JsonProperty("messages")
    public List<Message> getMessages() {
        return messages;
    }

    @JsonProperty("n")
    public Integer getN() {
        return n;
    }

    @JsonProperty("temperature")
    public Double getTemperature() {
        return temperature;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String model;
        private List<Message> messages;
        private Integer n;
        private Double temperature;

        public Builder model(String model) {
            this.model = model;
            return this;
        }

        public Builder messages(List<Message> messages) {
            this.messages = messages;
            return this;
        }

        public Builder n(Integer n) {
            this.n = n;
            return this;
        }

        public Builder temperature(Double temperature) {
            this.temperature = temperature;
            return this;
        }

        public ChatRequest build() {
            return new ChatRequest(this);
        }
    }
}
