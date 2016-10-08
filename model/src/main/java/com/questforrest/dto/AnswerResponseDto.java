package com.questforrest.dto;

import java.io.Serializable;

/**
 * Created by Ira Zyabkina on 09.10.2016.
 */
public class AnswerResponseDto implements Serializable {
    private QuestProgressResponseDto questProgressResponseDto;
    private boolean rightAnswer;

    public QuestProgressResponseDto getQuestProgressResponseDto() {
        return questProgressResponseDto;
    }

    public void setQuestProgressResponseDto(QuestProgressResponseDto questProgressResponseDto) {
        this.questProgressResponseDto = questProgressResponseDto;
    }

    public boolean isRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(boolean rightAnswer) {
        this.rightAnswer = rightAnswer;
    }
}
