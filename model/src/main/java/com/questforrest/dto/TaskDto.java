package com.questforrest.dto;

import com.questforrest.model.Quest;

/**
 * Created by root on 08.10.16.
 */
public class TaskDto {
    private Long id;
    private String name;
    private String pictureUrl;
    private String description;
    private String solution;
    private int taskOrderNumber;

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

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public int getTaskOrderNumber() {
        return taskOrderNumber;
    }

    public void setTaskOrderNumber(int taskOrderNumber) {
        this.taskOrderNumber = taskOrderNumber;
    }
}
