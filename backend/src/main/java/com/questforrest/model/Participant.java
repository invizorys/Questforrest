package com.questforrest.model;

import javax.persistence.*;

/**
 * Created by Ira Zyabkina on 08.10.2016.
 */
@Entity
@Table(name = "participant")
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "participant_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "quest_progress_id")
    private QuestProgress questProgress;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestProgress getQuestProgress() {
        return questProgress;
    }

    public void setQuestProgress(QuestProgress questProgress) {
        this.questProgress = questProgress;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
