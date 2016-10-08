package com.mediahack.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mediahack.ui.fragment.FragmentLocationQuest;
import com.mediahack.ui.fragment.FragmentQRCodeQuest;
import com.mediahack.ui.fragment.FragmentTextQuest;

/**
 * Created by Roma on 08.10.2016.
 */

public class QuestPagerAdapter extends FragmentPagerAdapter {
    static final int PAGE_COUNT = 3;

    public QuestPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return FragmentTextQuest.newInstance();
            case 1:
                return FragmentQRCodeQuest.newInstance();
            case 2:
                return FragmentLocationQuest.newInstance("1", "2");
        }
        return null;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
