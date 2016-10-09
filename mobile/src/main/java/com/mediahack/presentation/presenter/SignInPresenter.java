package com.mediahack.presentation.presenter;

import android.text.TextUtils;

import com.mediahack.presentation.view.SignInView;
import com.mediahack.repository.rest.MediaHackClient;
import com.mediahack.repository.rest.ServiceGenerator;
import com.mediahack.util.SharedPrefHelper;
import com.questforrest.dto.RegistrationRequestDto;
import com.questforrest.dto.UserDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Roma on 05.10.2016.
 */

public class SignInPresenter {
    private SignInView view;
    private MediaHackClient client;

    public SignInPresenter(SignInView view) {
        this.view = view;
        this.client = ServiceGenerator.createService(MediaHackClient.class, false);
    }

    public void login(String userId, String accessToken) {
        RegistrationRequestDto registrationRequestDto = new RegistrationRequestDto();
        registrationRequestDto.setAccessToken(accessToken);
        registrationRequestDto.setUserId(userId);
        Call<UserDto> call = client.login(registrationRequestDto);
        call.enqueue(new Callback<UserDto>() {

            @Override
            public void onResponse(Call<UserDto> call, Response<UserDto> response) {
                if (response.isSuccessful() && !TextUtils.isEmpty(response.body().getToken())) {
                    SharedPrefHelper.saveToken(response.body().getToken());
                    view.onSuccessfulLogin();
                } else {
                    view.showMessageDialog("code: " + String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<UserDto> call, Throwable t) {
                view.showMessageDialog(t.getMessage());
            }
        });
    }
}
