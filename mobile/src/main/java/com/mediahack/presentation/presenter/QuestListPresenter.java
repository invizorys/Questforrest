package com.mediahack.presentation.presenter;

import android.text.TextUtils;

import com.mediahack.presentation.view.QuestListView;
import com.mediahack.repository.rest.MediaHackClient;
import com.mediahack.repository.rest.ServiceGenerator;
import com.questforrest.dto.QuestListResponseDto;
import com.questforrest.dto.QuestProgressResponseDto;
import com.questforrest.dto.QuestShortInfoDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Roma on 08.10.2016.
 */

public class QuestListPresenter {
    private QuestListView view;
    private MediaHackClient client;
    private final int UNAUTHORISED = 401;

    public QuestListPresenter(QuestListView view) {
        this.view = view;
        this.client = ServiceGenerator.createService(MediaHackClient.class, true);
    }

    public void getQuestList() {
        Call<QuestListResponseDto> call = client.getQuests();

        call.enqueue(new Callback<QuestListResponseDto>() {

            @Override
            public void onResponse(Call<QuestListResponseDto> call, Response<QuestListResponseDto> response) {
                if (response.isSuccessful()) {
                    List<QuestShortInfoDto> quests = response.body().getQuests();
                    view.showQuestList(quests);
                } else {
                    if (response.code() == UNAUTHORISED) {
                        view.redirectToLoginScreen();
                    } else {
                        view.onFailureRetrieveQuests();
                    }
                }
            }

            @Override
            public void onFailure(Call<QuestListResponseDto> call, Throwable t) {
                String text = t.getMessage();
                if (TextUtils.isEmpty(text)) {
                    text = t.getStackTrace().toString();
                }
                view.showMessageDialog(text);
            }
        });
    }

    public void getQuest(Long questId, final String questName) {
        Call<QuestProgressResponseDto> call = client.getQuestProgress(questId);

        call.enqueue(new Callback<QuestProgressResponseDto>() {

            @Override
            public void onResponse(Call<QuestProgressResponseDto> call, Response<QuestProgressResponseDto> response) {
                view.showSelectedQuest(response.body(), questName);
            }

            @Override
            public void onFailure(Call<QuestProgressResponseDto> call, Throwable t) {
                view.showMessageDialog(t.getMessage());
            }
        });
    }

    public void createTeam(Long questId, String teamName, final String questName) {
        Call<QuestProgressResponseDto> call = client.createTeam(questId, teamName);

        call.enqueue(new Callback<QuestProgressResponseDto>() {

            @Override
            public void onResponse(Call<QuestProgressResponseDto> call, Response<QuestProgressResponseDto> response) {
                if (response.isSuccessful()) {
                    view.onSuccessfulCreateTeam(response.body(), questName);
                } else {
                    if (response.code() == UNAUTHORISED) {
                        view.redirectToLoginScreen();
                    } else {
                        view.onFailureCreateTeam();
                    }
                }
            }

            @Override
            public void onFailure(Call<QuestProgressResponseDto> call, Throwable t) {
                view.showMessageDialog(t.getMessage());
            }
        });
    }

    public void enrollQuest(Long questId, String questCode, final String questName) {
        Call<QuestProgressResponseDto> call = client.enrollQuest(questId, questCode);

        call.enqueue(new Callback<QuestProgressResponseDto>() {

            @Override
            public void onResponse(Call<QuestProgressResponseDto> call, Response<QuestProgressResponseDto> response) {
                if (response.isSuccessful()) {
                    view.onSuccessfulEnrollQuest(response.body(), questName);
                } else {
                    view.onFailureEnrollQuest();
                }
            }

            @Override
            public void onFailure(Call<QuestProgressResponseDto> call, Throwable t) {
                view.showMessageDialog(t.getMessage());
            }
        });
    }
}