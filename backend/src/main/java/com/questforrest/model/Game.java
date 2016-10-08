package com.questforrest.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Ira Zyabkina on 08.10.2016.
 */
@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "quest_id")
    private Quest quest;
    @Column(name = "game_currentTaskOrderNumber")
    private int currentTaskOrderNumber;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "participants_games",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private List<Person> participants;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }

    public int getCurrentTaskOrderNumber() {
        return currentTaskOrderNumber;
    }

    public void setCurrentTaskOrderNumber(int currentTaskOrderNumber) {
        this.currentTaskOrderNumber = currentTaskOrderNumber;
    }

    public List<Person> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Person> participants) {
        this.participants = participants;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
