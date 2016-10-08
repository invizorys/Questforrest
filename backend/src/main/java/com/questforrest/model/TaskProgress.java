package com.questforrest.model;

import javax.persistence.*;

/**
 * Created by Ira Zyabkina on 08.10.2016.
 */
@Entity
@Table(name = "task_progress")
public class TaskProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_progress_id")
    private Long id;
    @Column(name = "task_progress_status")
    private boolean solved;
    @ManyToOne
    @JoinColumn(name = "task_progress_task_id")
    private Task task;
    @ManyToOne
    @JoinColumn(name = "task_progress_quest_progress_id")
    private QuestProgress questProgress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public QuestProgress getQuestProgress() {
        return questProgress;
    }

    public void setQuestProgress(QuestProgress questProgress) {
        this.questProgress = questProgress;
    }
}
