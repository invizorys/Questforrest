package com.mediahack.presentation.presenter;

import com.mediahack.presentation.view.TextQuestView;
import com.mediahack.repository.rest.MediaHackClient;
import com.mediahack.repository.rest.ServiceGenerator;
import com.questforrest.dto.AnswerResponseDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Roma on 09.10.2016.
 */

public class TextQuestPresenter {
    private TextQuestView view;
    private MediaHackClient client;

    public TextQuestPresenter(TextQuestView view) {
        this.view = view;
        this.client = ServiceGenerator.createService(MediaHackClient.class, true);
    }

    public void resolveTask(Long taskProgressId, String answer) {
        Call<AnswerResponseDto> call = client.resolveTask(taskProgressId, answer);

        call.enqueue(new Callback<AnswerResponseDto>() {

            @Override
            public void onResponse(Call<AnswerResponseDto> call, Response<AnswerResponseDto> response) {
                view.showResolveTaskResponse(response.body().isRightAnswer());
            }

            @Override
            public void onFailure(Call<AnswerResponseDto> call, Throwable t) {
                view.showMessageDialog(t.getMessage());
            }
        });
    }
}
