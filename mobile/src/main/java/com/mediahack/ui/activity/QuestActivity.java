package com.mediahack.ui.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.mediahack.R;
import com.mediahack.presentation.presenter.QuestPresenter;
import com.mediahack.presentation.view.QuestView;
import com.mediahack.ui.adapter.QuestPagerAdapter;
import com.mediahack.util.Util;
import com.questforrest.dto.QuestProgressResponseDto;
import com.questforrest.dto.TaskProgressDto;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.List;

public class QuestActivity extends AppCompatActivity implements View.OnClickListener, QuestView {
    private final static String QUEST_PROGRESS = "questProgress";
    private final static String QUEST_NAME = "questName";
    private ViewPager pager;
    private List<TaskProgressDto> tasks;
    private QuestProgressResponseDto questProgressResponseDto;
    private QuestPresenter presenter;

    public static void startActivity(Context context, QuestProgressResponseDto quest,
                                     String questName) {
        Intent intent = new Intent(context, QuestActivity.class);
        intent.putExtra(QUEST_PROGRESS, quest);
        intent.putExtra(QUEST_NAME, questName);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest);

        //avoiding keyboard appearance when activity starts
        //avoiding move layout up when soft keyboard is shown
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN |
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter = new QuestPresenter(this);

        questProgressResponseDto = (QuestProgressResponseDto) getIntent().getSerializableExtra(QUEST_PROGRESS);
        String questName = getIntent().getStringExtra(QUEST_NAME);

        tasks = questProgressResponseDto.getTaskProgresses();
        getSupportActionBar().setTitle(questName);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_quest, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_info:
                showInfoDialog("Team name: " + questProgressResponseDto.getTeamName() + "\n" +
                        "Code: " + questProgressResponseDto.getCode());
                break;
            case R.id.action_share:
                showVkShareDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showInfoDialog(String text) {
        Util.getStandardDialog(this, text).show();
    }

    private void showVkShareDialog() {
        AlertDialog.Builder builder = Util.getStandardDialog(this, "Do you want share this quest to Vk?");
        builder.setPositiveButton("Share", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.shareVk();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
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
        for (TaskProgressDto task : tasks) {
            if (!task.isSolved()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onSuccessfulVkShare() {
        Toast.makeText(this, "Successful Vk Share", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailureVkShare() {
        Toast.makeText(this, "Failure Vk Share", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessageDialog(String text) {
        Util.getStandardDialog(this, text).show();
    }
}
