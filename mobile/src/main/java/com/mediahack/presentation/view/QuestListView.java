package com.mediahack.presentation.view;

import com.questforrest.dto.QuestMetadataResponseDto;
import com.questforrest.dto.QuestProgressResponseDto;
import com.questforrest.dto.QuestShortInfoDto;

import java.util.List;

/**
 * Created by Roma on 08.10.2016.
 */

public interface QuestListView {
    void showQuestList(List<QuestShortInfoDto> quests);

    void showMessageDialog(String text);

    void showSelectedQuest(QuestProgressResponseDto responseDto, String questName);

    void onSuccessfulCreateTeam(QuestProgressResponseDto questProgressResponseDto, String questName);

    void onFailureCreateTeam();

    void onSuccessfulEnrollQuest(QuestProgressResponseDto questProgressResponseDto, String questName);

    void onFailureEnrollQuest();

    void onFailureRetrieveQuests();

    void redirectToLoginScreen();
}
