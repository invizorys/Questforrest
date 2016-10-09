package com.mediahack.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        if (resolved) {
            builder.setTitle("Success");
        } else {
            builder.setTitle("Failure");
        }
//        builder.setMessage(resultQr);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (resolved) {
                    task.setSolved(true);
                    ((QuestActivity) getActivity()).showNextTask();
                }
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void showMessageDialog(String text) {
        Util.getStandardDialog(getActivity(), text).show();
    }
}
