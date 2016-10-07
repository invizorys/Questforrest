package com.mediahack.presentation.presenter;

import com.mediahack.presentation.view.MainView;
import com.mediahack.repository.rest.MediaHackClient;
import com.mediahack.repository.rest.ServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Roma on 05.10.2016.
 */

public class MainPresenter {
    private MainView view;

    public MainPresenter(MainView view) {
        this.view = view;
    }

    public void getSomething() {
        MediaHackClient client = ServiceGenerator.createService(MediaHackClient.class);

        Call<List<Object>> call =
                client.contributors("fs_opensource", "android-boilerplate");

        call.enqueue(new Callback<List<Object>>() {
            @Override
            public void onResponse(Call<List<Object>> call, Response<List<Object>> response) {

            }

            @Override
            public void onFailure(Call<List<Object>> call, Throwable t) {

            }
        });
    }
}
