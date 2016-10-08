package com.questforrest.dto;

import java.util.List;

/**
 * Created by Ira Zyabkina on 09.10.2016.
 */
public class QuestMetadataResponseDto {
    private Long id;
    private String name;
    private String description;
    private List<TaskDto> tasks;
    private int maxPlayers;
    private String pictureUrl;


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

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public enum Type {
        QR, TEXT, LOCATION
    }

    public class TaskDto{
        private Long id;
        private String name;
        private String pictureUrl;
        private String description;
        private String solution;
        private int taskOrderNumber;
        private Type type;

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

        public Type getType() {
            return type;
        }

        public void setType(Type type) {
            this.type = type;
        }
    }
}
