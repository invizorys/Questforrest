package com.mediahack.presentation.presenter;

import com.mediahack.presentation.view.SignInView;
import com.mediahack.repository.rest.MediaHackClient;
import com.mediahack.repository.rest.ServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Roma on 05.10.2016.
 */

public class SignInPresenter {
    private SignInView view;

    public SignInPresenter(SignInView view) {
        this.view = view;
    }

    public void login() {

    }
}
