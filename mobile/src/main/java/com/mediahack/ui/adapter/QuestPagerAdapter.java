package com.mediahack.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mediahack.ui.fragment.FragmentLocationQuest;
import com.mediahack.ui.fragment.FragmentQRCodeQuest;
import com.mediahack.ui.fragment.FragmentTextQuest;
import com.questforrest.dto.TaskDto;
import com.questforrest.dto.TaskProgressDto;

import java.util.List;

/**
 * Created by Roma on 08.10.2016.
 */

public class QuestPagerAdapter extends FragmentPagerAdapter {
    private static int PAGE_COUNT;
    private List<TaskProgressDto> tasks;

    public QuestPagerAdapter(FragmentManager fm, List<TaskProgressDto> tasks) {
        super(fm);
        if (tasks != null) {
            PAGE_COUNT = tasks.size();
            this.tasks = tasks;
        } else {
            PAGE_COUNT = 0;
        }
    }

    @Override
    public Fragment getItem(int position) {
        switch (tasks.get(position).getType()) {
            case TEXT:
                return FragmentTextQuest.newInstance(tasks.get(position));
            case QR:
                return FragmentQRCodeQuest.newInstance(tasks.get(position));
            case LOCATION:
                return FragmentLocationQuest.newInstance(tasks.get(position));
        }
        return null;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
