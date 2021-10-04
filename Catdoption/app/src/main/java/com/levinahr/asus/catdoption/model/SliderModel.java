package com.levinahr.asus.catdoption.model;

public class SliderModel {
    private String imageUrl;
    private String description;

    public SliderModel(String imageUrl, String description) {
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
