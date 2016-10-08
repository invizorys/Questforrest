package com.mediahack.presentation.view;

import com.questforrest.dto.QuestDto;

import java.util.List;

/**
 * Created by Roma on 08.10.2016.
 */

public interface QuestListView {
    void showQuestList(List<QuestDto> quests);
    void showMessageDialog(String text);
}
