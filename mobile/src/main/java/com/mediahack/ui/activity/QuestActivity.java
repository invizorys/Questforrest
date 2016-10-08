package com.mediahack.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mediahack.R;
import com.mediahack.ui.adapter.QuestPagerAdapter;
import com.questforrest.dto.QuestDto;
import com.questforrest.dto.TaskDto;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.List;

public class QuestActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String QUEST = "quest";
    private ViewPager pager;
    private List<TaskDto> tasks;

    public static void startActivity(Context context, QuestDto quest) {
        Intent intent = new Intent(context, QuestActivity.class);
        intent.putExtra(QUEST, quest);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        QuestDto quest = (QuestDto) getIntent().getSerializableExtra(QUEST);
        tasks = quest.getTasks();
        getSupportActionBar().setTitle(quest.getName());

        findViewById(R.id.button_next).setOnClickListener(this);

        pager = (ViewPager) findViewById(R.id.pager);
        QuestPagerAdapter pagerAdapter = new QuestPagerAdapter(getSupportFragmentManager(), tasks);
        pager.setAdapter(pagerAdapter);

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

    public void showNextTask() {
        if (isAllTaskSolved()) {
            CompleteQuestActivity.startActivity(this);
        } else {
            pager.setCurrentItem(pager.getCurrentItem() + 1);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_next:
                pager.setCurrentItem(pager.getCurrentItem() + 1);
                break;
        }
    }

    private boolean isAllTaskSolved() {
        for (TaskDto task : tasks) {
            if (!task.isSolved()) {
                return false;
            }
        }
        return true;
    }
}
