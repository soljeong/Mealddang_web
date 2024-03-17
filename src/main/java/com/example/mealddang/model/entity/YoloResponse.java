package com.example.mealddang.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;

public class YoloResponse {
    private String[] images;

    // 기본 생성자
    public YoloResponse() {
    }

    // 생성자
    @JsonCreator
    public YoloResponse(@JsonProperty("images") String[] images) {
        this.images = images;
    }

    // Getter 메서드
    public String[] getImages() {
        return images;
    }

    // Setter 메서드
    public void setImages(String[] images) {
        this.images = images;
    }
}
