package com.questforrest.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by root on 08.10.16.
 */
public class QuestProgressResponseDto implements Serializable {
    private List<TaskDto> taskProgresses;
    private List<UserShortInfoDto> participants;
    private String teamName;
    private String code;

    public QuestProgressResponseDto(String teamName, String code, List<TaskDto> taskProgresses, List<UserShortInfoDto> participants) {
        this.taskProgresses = taskProgresses;
        this.participants = participants;
        this.teamName = teamName;
        this.code = code;
    }

    public List<TaskDto> getTaskProgresses() {
        return taskProgresses;
    }

    public void setTaskProgresses(List<TaskDto> taskProgresses) {
        this.taskProgresses = taskProgresses;
    }

    public List<UserShortInfoDto> getParticipants() {
        return participants;
    }

    public void setParticipants(List<UserShortInfoDto> participants) {
        this.participants = participants;
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
