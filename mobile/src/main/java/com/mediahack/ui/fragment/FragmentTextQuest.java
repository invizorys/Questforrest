package com.mediahack.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        if (resolved) {
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
        } else {
            Util.getStandardDialog(getActivity(),
                    getString(R.string.incorrect_solution_message)).show();
        }
    }

    @Override
    public void showMessageDialog(String text) {
        Util.getStandardDialog(getActivity(), text).show();
    }
}
