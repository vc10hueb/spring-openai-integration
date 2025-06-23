package com.spring.openai.integration.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImageUrl {

    private final String url;

    public ImageUrl(@JsonProperty("url") String url) {
        this.url = url;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }
}

