package com.mediahack.presentation.presenter;

import com.mediahack.presentation.view.QRCodeView;
import com.mediahack.repository.rest.MediaHackClient;
import com.mediahack.repository.rest.ServiceGenerator;
import com.questforrest.dto.AnswerResponseDto;
import com.questforrest.dto.QuestProgressResponseDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Path;

/**
 * Created by Roma on 09.10.2016.
 */

public class QRCodeQuestPresenter {
    private QRCodeView view;
    private MediaHackClient client;

    public QRCodeQuestPresenter(QRCodeView view) {
        this.view = view;
        this.client = ServiceGenerator.createService(MediaHackClient.class, true);
    }

    public void resolveTask(Long taskProgressId, String answer) {
        Call<AnswerResponseDto> call = client.resolveTask(taskProgressId, answer);

        call.enqueue(new Callback<AnswerResponseDto>() {

            @Override
            public void onResponse(Call<AnswerResponseDto> call, Response<AnswerResponseDto> response) {
                view.showResolveTaskResponse(true);
            }

            @Override
            public void onFailure(Call<AnswerResponseDto> call, Throwable t) {
                view.showMessageDialog(t.getMessage());
            }
        });
    }
}
