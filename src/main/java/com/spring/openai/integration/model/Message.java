package com.spring.openai.integration.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message {

    private final String role;
    private final List<ContentPart> content;

    public Message(@JsonProperty("role") String role,
                   @JsonProperty("content") List<ContentPart> content) {
        this.role = role;
        this.content = content;
    }

    @JsonProperty("role")
    public String getRole() {
        return role;
    }

    @JsonProperty("content")
    public List<ContentPart> getContent() {
        return content;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String role;
        private List<ContentPart> content;

        public Builder role(String role) {
            this.role = role;
            return this;
        }

        public Builder content(List<ContentPart> content) {
            this.content = content;
            return this;
        }

        public Message build() {
            return new Message(role, content);
        }
    }
}
