package com.mediahack.presentation.presenter;

import com.mediahack.presentation.view.QuestView;
import com.mediahack.repository.rest.MediaHackClient;
import com.mediahack.repository.rest.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Roma on 09.10.2016.
 */

public class QuestPresenter {
    private QuestView view;
    private MediaHackClient client;

    public QuestPresenter(QuestView view) {
        this.view = view;
        this.client = ServiceGenerator.createService(MediaHackClient.class, true);
    }

    public void shareVk() {
        Call<Void> call = client.shareVk();

        call.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    view.onSuccessfulVkShare();
                } else {
                    view.onFailureVkShare();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                view.showMessageDialog(t.getMessage());
            }
        });
    }
}
