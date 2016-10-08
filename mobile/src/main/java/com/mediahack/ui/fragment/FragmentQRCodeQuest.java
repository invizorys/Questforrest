package com.mediahack.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mediahack.R;
import com.mediahack.ui.activity.QRCodeActivity;
import com.mediahack.ui.activity.QuestActivity;
import com.questforrest.dto.TaskDto;

public class FragmentQRCodeQuest extends Fragment implements View.OnClickListener {
    private static final String TASK = "task";
    private TaskDto task;

    public FragmentQRCodeQuest() {
        // Required empty public constructor
    }

    public static FragmentQRCodeQuest newInstance(TaskDto taskDto) {
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
            task = (TaskDto) getArguments().getSerializable(TASK);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qrcode_quest, container, false);
        ((TextView) view.findViewById(R.id.text_view_description)).setText(task.getDescription());
        view.findViewById(R.id.button_scan_qr_code).setOnClickListener(this);
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
            // show the scanner result into dialog box.
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            String resultQr = data.getStringExtra(QRCodeActivity.RESULT);
            final boolean success = task.getSolution().equals(resultQr);
            if (success) {
                builder.setTitle("Scan Result Success");
            } else {
                builder.setTitle("Scan Result Failure");
            }
            builder.setMessage(resultQr);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (success) {
                        task.setSolved(true);
                        ((QuestActivity) getActivity()).showNextTask();
                    }
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }
}
