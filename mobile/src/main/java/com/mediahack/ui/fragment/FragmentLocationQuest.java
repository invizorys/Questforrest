package com.mediahack.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mediahack.R;
import com.mediahack.util.Util;
import com.mediahack.ui.activity.QuestActivity;
import com.questforrest.dto.TaskDto;

public class FragmentLocationQuest extends Fragment implements View.OnClickListener {
    private static final String TASK = "task";
    private TaskDto task;

    public FragmentLocationQuest() {
        // Required empty public constructor
    }

    public static FragmentLocationQuest newInstance(TaskDto taskDto) {
        FragmentLocationQuest fragment = new FragmentLocationQuest();
        Bundle args = new Bundle();
        args.putSerializable(TASK, taskDto);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            task = (TaskDto) getArguments().getSerializable(TASK);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location_quest, container, false);
        ((TextView) view.findViewById(R.id.text_view_description)).setText(task.getDescription());
        view.findViewById(R.id.button_check_location).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_check_location:
                AlertDialog.Builder builder = Util.getStandardDialog(getActivity(),
                        getString(R.string.correct_solution_message));
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        task.setSolved(true);
                        ((QuestActivity) getActivity()).showNextTask();
                    }
                });
                builder.create().show();
                break;
        }
    }
}
