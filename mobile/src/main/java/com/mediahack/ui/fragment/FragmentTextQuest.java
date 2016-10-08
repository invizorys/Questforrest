package com.mediahack.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mediahack.R;

public class FragmentTextQuest extends Fragment {

    public FragmentTextQuest() {
        // Required empty public constructor
    }

    public static FragmentTextQuest newInstance() {
        return new FragmentTextQuest();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_text_quest, container, false);
    }
}
