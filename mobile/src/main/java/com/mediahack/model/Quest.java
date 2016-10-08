package com.mediahack.model;

import java.io.Serializable;

/**
 * Created by Roma on 08.10.2016.
 */

public class Quest implements Serializable {
    private String imageUrl;
    private String title;
    private String description;

    public Quest(String imageUrl, String title, String description) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
