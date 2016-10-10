package com.mediahack.ui.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mediahack.R;
import com.mediahack.presentation.presenter.TextQuestPresenter;
import com.mediahack.presentation.view.TextQuestView;
import com.mediahack.ui.activity.QuestActivity;
import com.mediahack.util.Util;
import com.questforrest.dto.TaskProgressDto;
import com.squareup.picasso.Picasso;

public class FragmentTextQuest extends Fragment implements View.OnClickListener, TextQuestView {
    private static final String TASK = "task";
    private TaskProgressDto task;
    private EditText editTextSolution;
    private TextQuestPresenter presenter;

    public FragmentTextQuest() {
        // Required empty public constructor
    }

    public static FragmentTextQuest newInstance(TaskProgressDto taskDto) {
        FragmentTextQuest fragment = new FragmentTextQuest();
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
        View view = inflater.inflate(R.layout.fragment_text_quest, container, false);
        ((TextView) view.findViewById(R.id.text_view_description)).setText(task.getDescription());
        view.findViewById(R.id.button_check).setOnClickListener(this);
        editTextSolution = (EditText) view.findViewById(R.id.edit_text);
        ImageView imageView = (ImageView) view.findViewById(R.id.image_view);
        String pictureUrl = task.getPictureUrl();
        if (!TextUtils.isEmpty(pictureUrl)) {
            Picasso.with(getActivity()).load(pictureUrl).into(imageView);
        }

        presenter = new TextQuestPresenter(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_check:
                Util.hideKeyboard(getActivity());
                String answer = editTextSolution.getText().toString();
                if (!TextUtils.isEmpty(answer)) {
                    presenter.resolveTask(task.getId(), answer);
                }
                break;
        }
    }

    @Override
    public void showResolveTaskResponse(boolean resolved) {
        answerDialogShow(getActivity(), resolved);
    }

    @Override
    public void showMessageDialog(String text) {
        Util.getStandardDialog(getActivity(), text).show();
    }

    private void answerDialogShow(Context context, final boolean isRightAnswer) {
        final Dialog dialog = new Dialog(context, R.style.CustomDialogTheme);
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
