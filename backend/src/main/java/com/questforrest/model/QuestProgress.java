package com.questforrest.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Ira Zyabkina on 08.10.2016.
 */
@Entity
@Table(name = "quest_progress")
public class QuestProgress {
    public enum Status{
        NOT_STARTED, IN_PROGRESS, FINISHED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quest_progress_id")
    private Long id;
    @OneToMany(mappedBy = "questProgress", cascade = CascadeType.ALL)
    private List<TaskProgress> taskProgresses;
    @OneToMany(mappedBy = "questProgress")
    private List<Participant> participants;
    @Column(name = "status")
    @Enumerated
    private Status status;
    @ManyToOne()
    @JoinColumn(name = "quest_id")
    private Quest quest;
    @DateTimeFormat
    @Column(name = "quest_progress_start_date")
    private Date startDate;
    @DateTimeFormat
    @Column(name = "quest_progress_end_date")
    private Date endDate;
    @Column(name = "quest_progress_team_name")
    private String teamName;
    @Column(name = "quest_progress_code")
    private String code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TaskProgress> getTaskProgresses() {
        return taskProgresses;
    }

    public void setTaskProgresses(List<TaskProgress> taskProgresses) {
        this.taskProgresses = taskProgresses;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
