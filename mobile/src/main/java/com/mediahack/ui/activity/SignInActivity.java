package com.mediahack.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mediahack.R;
import com.mediahack.presentation.presenter.SignInPresenter;
import com.mediahack.presentation.view.SignInView;

public class SignInActivity extends AppCompatActivity implements SignInView, View.OnClickListener {
    private SignInPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter = new SignInPresenter(this);

        findViewById(R.id.button_login_vk).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_login_vk:
                QuestListActivity.startActivity(this);
                break;
        }
    }
}
