package com.spring.openai.integration.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContentPart {

    private final String type;
    private final String text;
    private final ImageUrl imageUrl;

    public ContentPart(@JsonProperty("type") String type,
                       @JsonProperty("text") String text,
                       @JsonProperty("image_url") ImageUrl imageUrl) {
        this.type = type;
        this.text = text;
        this.imageUrl = imageUrl;
    }

    public static ContentPart text(String text) {
        return new ContentPart("text", text, null);
    }

    public static ContentPart image(String imageUrl) {
        return new ContentPart("image_url", null, new ImageUrl(imageUrl));
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("image_url")
    public ImageUrl getImageUrl() {
        return imageUrl;
    }
}

