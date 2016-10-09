package com.mediahack.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mediahack.R;
import com.mediahack.presentation.presenter.SignInPresenter;
import com.mediahack.presentation.view.SignInView;
import com.mediahack.util.SharedPrefHelper;
import com.mediahack.util.Util;
import com.mediahack.util.VkSocialHelper;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

public class SignInActivity extends AppCompatActivity implements SignInView, View.OnClickListener {
    private SignInPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        if (!TextUtils.isEmpty(SharedPrefHelper.getToken())) {
            QuestListActivity.startActivity(this);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter = new SignInPresenter(this);

        findViewById(R.id.button_login_vk).setOnClickListener(this);
    }

    private void vkLogin() {
//        if (Util.isInternetAvailable()) {
            VkSocialHelper vkSocialNetwork = new VkSocialHelper(this);
            vkSocialNetwork.login();
//        } else {
//            Toast.makeText(this, R.string.internet_is_unavailable, Toast.LENGTH_SHORT).show();
//        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                presenter.login(res.userId, res.accessToken);

                // User passed Authorization
//                new VkSocialNetwork(LoginActivity.this).getUserData(LoginActivity.this);
            }

            @Override
            public void onError(VKError error) {
                // User didn't pass Authorization
                Log.e("LoginActivity", "VKError: " + error.toString());
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_login_vk:
                vkLogin();
                break;
        }
    }

    @Override
    public void showMessageDialog(String text) {
        Util.getStandardDialog(this, text).show();
    }

    @Override
    public void onSuccessfulLogin() {
        QuestListActivity.startActivity(this);
    }
}
