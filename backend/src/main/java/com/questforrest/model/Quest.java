package com.questforrest.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Ira Zyabkina on 08.10.2016.
 */
@Entity
@Table(name = "quest")
public class Quest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quest_id")
    private Long id;
    @Column(name = "quest_name")
    private String name;
    @Column(name = "quest_description")
    private String description;
    @OneToMany(mappedBy = "quest", cascade = CascadeType.ALL)
    private List<Task> tasks;
    @Column(name = "quest_maxPlayers")
    private int maxPlayers;
    @Column(name = "quest_picture")
    private String pictureUrl;

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
