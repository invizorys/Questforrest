package com.mediahack.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mediahack.R;
import com.mediahack.model.Quest;
import com.mediahack.ui.adapter.QuestPagerAdapter;
import com.viewpagerindicator.CirclePageIndicator;

public class QuestActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String QUEST = "quest";
    private ViewPager pager;

    public static void startActivity(Context context, Quest quest) {
        Intent intent = new Intent(context, QuestActivity.class);
        intent.putExtra(QUEST, quest);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest);

        Quest quest = (Quest) getIntent().getSerializableExtra(QUEST);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(quest.getTitle());

        findViewById(R.id.button_next).setOnClickListener(this);

        pager = (ViewPager) findViewById(R.id.pager);
        QuestPagerAdapter pagerAdapter = new QuestPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
//                Log.d(TAG, "onPageSelected, position = " + position);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        CirclePageIndicator pageIndicator = (CirclePageIndicator) findViewById(R.id.titles);
        pageIndicator.setViewPager(pager);
    }

    private void initDots() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_next:
                pager.setCurrentItem(pager.getCurrentItem() + 1);
                break;
        }
    }
}
