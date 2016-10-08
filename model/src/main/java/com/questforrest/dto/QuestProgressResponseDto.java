package com.questforrest.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by root on 08.10.16.
 */
public class QuestProgressResponseDto implements Serializable {
    private List<TaskDto> taskProgresses;
    private List<UserShortInfoDto> participants;

    public QuestProgressResponseDto(List<TaskDto> taskProgresses, List<UserShortInfoDto> participants) {
        this.taskProgresses = taskProgresses;
        this.participants = participants;
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
}
