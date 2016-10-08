package com.questforrest.dto;

import java.io.Serializable;

/**
 * Created by root on 08.10.16.
 */
public class TaskProgressDto implements Serializable {
    public enum Type {
        QR, TEXT, LOCATION
    }

    private Long id;
    private String name;
    private String pictureUrl;
    private String description;
    private Type type;
    private boolean solved;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }
}
