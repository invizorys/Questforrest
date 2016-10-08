package com.questforrest.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ira Zyabkina on 08.10.2016.
 */
public class QuestEnrollResponseDto implements Serializable {
    private Long questProgressId;
    private String code;
    private String teamName;
    private List<UserShortInfoDto> participants;

    public QuestEnrollResponseDto() {
    }

    public QuestEnrollResponseDto(Long questProgressId, String code, String teamName, List<UserShortInfoDto> participants) {
        this.questProgressId = questProgressId;
        this.code = code;
        this.teamName = teamName;
        this.participants = participants;
    }

    public String getCode() {
        return code;
    }

    public String getTeamName() {
        return teamName;
    }

    public List<UserShortInfoDto> getParticipants() {
        return participants;
    }

    public Long getQuestProgressId() {
        return questProgressId;
    }
}
