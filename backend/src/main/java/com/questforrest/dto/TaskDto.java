package com.questforrest.dto;

import javax.persistence.Column;

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
}
