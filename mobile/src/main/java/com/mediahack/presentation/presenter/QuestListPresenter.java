package com.mediahack.presentation.presenter;

import com.mediahack.presentation.view.QuestListView;
import com.mediahack.repository.rest.MediaHackClient;
import com.mediahack.repository.rest.ServiceGenerator;
import com.questforrest.dto.QuestDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Roma on 08.10.2016.
 */

public class QuestListPresenter {
    private QuestListView view;

    public QuestListPresenter(QuestListView view) {
        this.view = view;
    }

    public void getQuestList() {
        MediaHackClient client = ServiceGenerator.createService(MediaHackClient.class);

        Call<List<QuestDto>> call = client.getQuests();

        call.enqueue(new Callback<List<QuestDto>>() {

            @Override
            public void onResponse(Call<List<QuestDto>> call, Response<List<QuestDto>> response) {
                List<QuestDto> quests = response.body();
                view.showQuestList(quests);
            }

            @Override
            public void onFailure(Call<List<QuestDto>> call, Throwable t) {
                view.showMessageDialog(t.getMessage());
            }
        });
    }
}