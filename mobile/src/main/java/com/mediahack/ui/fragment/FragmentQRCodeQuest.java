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
import com.mediahack.presentation.presenter.QRCodeQuestPresenter;
import com.mediahack.presentation.view.QRCodeView;
import com.mediahack.ui.activity.QRCodeActivity;
import com.mediahack.ui.activity.QuestActivity;
import com.mediahack.util.Util;
import com.questforrest.dto.TaskProgressDto;
import com.squareup.picasso.Picasso;

public class FragmentQRCodeQuest extends Fragment implements View.OnClickListener, QRCodeView {
    private static final String TASK = "task";
    private TaskProgressDto task;
    private QRCodeQuestPresenter presenter;

    public FragmentQRCodeQuest() {
        // Required empty public constructor
    }

    public static FragmentQRCodeQuest newInstance(TaskProgressDto taskDto) {
        FragmentQRCodeQuest fragment = new FragmentQRCodeQuest();
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
        View view = inflater.inflate(R.layout.fragment_qrcode_quest, container, false);
        ((TextView) view.findViewById(R.id.text_view_description)).setText(task.getDescription());
        view.findViewById(R.id.button_scan_qr_code).setOnClickListener(this);
        ImageView imageView = (ImageView) view.findViewById(R.id.image_view);
        String pictureUrl = task.getPictureUrl();
        if (!TextUtils.isEmpty(pictureUrl)) {
            Picasso.with(getActivity()).load(pictureUrl).into(imageView);
        }

        presenter = new QRCodeQuestPresenter(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_scan_qr_code:
                startActivityForResult(new Intent(getActivity(), QRCodeActivity.class), QRCodeActivity.RESULT_CODE);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == QRCodeActivity.RESULT_CODE) {
            String resultQr = data.getStringExtra(QRCodeActivity.RESULT);

            presenter.resolveTask(task.getId(), resultQr);
        }
    }

    @Override
    public void showResolveTaskResponse(final boolean resolved) {
        answerDialogShow(resolved);
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

    @Override
    public void showMessageDialog(String text) {
        Util.getStandardDialog(getActivity(), text).show();
    }
}
