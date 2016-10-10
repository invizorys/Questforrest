package com.mediahack.ui.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.mediahack.R;
import com.mediahack.ui.activity.MapsActivity;
import com.mediahack.ui.activity.QuestActivity;
import com.questforrest.dto.TaskProgressDto;
import com.squareup.picasso.Picasso;

public class FragmentLocationQuest extends Fragment implements View.OnClickListener {
    private static final String TASK = "task";
    private TaskProgressDto task;

    public FragmentLocationQuest() {
        // Required empty public constructor
    }

    public static FragmentLocationQuest newInstance(TaskProgressDto taskDto) {
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
            task = (TaskProgressDto) getArguments().getSerializable(TASK);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location_quest, container, false);
        ((TextView) view.findViewById(R.id.text_view_description)).setText(task.getDescription());
        view.findViewById(R.id.button_check_location).setOnClickListener(this);
        ImageView imageView = (ImageView) view.findViewById(R.id.image_view);
        String pictureUrl = task.getPictureUrl();
        if (!TextUtils.isEmpty(pictureUrl)) {
            Picasso.with(getActivity()).load(pictureUrl).into(imageView);
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_check_location:
                startActivityForResult(new Intent(getActivity(), MapsActivity.class), MapsActivity.RESULT_CODE);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MapsActivity.RESULT_CODE) {
            answerDialogShow(true);
        }
    }

    private void answerDialogShow(final boolean isRightAnswer) {
        final Dialog dialog = new Dialog(getActivity(), R.style.CustomDialogTheme);
        dialog.setContentView(R.layout.dialog_task_answer);
        dialog.setCancelable(false);
        dialog.findViewById(R.id.iTouchEveryWhere).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        if (isRightAnswer) {
                            task.setSolved(true);
                            ((QuestActivity) getActivity()).showNextTask();
                        }
                    }
                });

        ImageView imageView = (ImageView) dialog.findViewById(R.id.image_view);
        TextView textView = (TextView) dialog.findViewById(R.id.text_view);
        if (isRightAnswer) {
            imageView.setImageResource(R.drawable.good_smile);
            textView.setText("Success");
        } else {
            imageView.setImageResource(R.drawable.bad_smile);
            textView.setText("Sorry, try again");
        }

        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dialog.show();
    }
}
