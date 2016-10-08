package com.questforrest.model;

import javax.persistence.*;

/**
 * Created by Ira Zyabkina on 08.10.2016.
 */
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long id;
    @Column(name = "task_name")
    private String name;
    @Column(name = "task_picture")
    private String pictureUrl;
    @Column(name = "task_description")
    private String description;
    @Column(name = "task_solution")
    private String solution;
    @Column(name = "task_order_number")
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
