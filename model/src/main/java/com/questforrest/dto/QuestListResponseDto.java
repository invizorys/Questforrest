package com.questforrest.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ira Zyabkina on 08.10.2016.
 */
public class QuestListResponseDto implements Serializable {
    private List<QuestShortInfoDto> quests;

    public QuestListResponseDto(List<QuestShortInfoDto> questShortInfoDtos) {
        this.quests = questShortInfoDtos;
    }

    public List<QuestShortInfoDto> getQuests() {
        return quests;
    }

    public void setQuests(List<QuestShortInfoDto> quests) {
        this.quests = quests;
    }
}
