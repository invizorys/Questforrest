package com.questforrest.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ira Zyabkina on 08.10.2016.
 */
public class CreateQuestRequestDto implements Serializable {
    private String name;
    private String description;
    private List<TaskDto> tasks;
    private String pictureUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TaskDto> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDto> tasks) {
        this.tasks = tasks;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
