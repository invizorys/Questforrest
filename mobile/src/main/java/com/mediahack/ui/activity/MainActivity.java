package com.mediahack.ui.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.mediahack.R;
import com.mediahack.databinding.ActivityMainBinding;
import com.mediahack.presentation.presenter.MainPresenter;
import com.mediahack.presentation.view.MainView;

public class MainActivity extends AppCompatActivity implements MainView {
    private ActivityMainBinding binding;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter = new MainPresenter(this);
    }
}
