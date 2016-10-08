package com.mediahack.ui.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mediahack.R;
import com.mediahack.ui.activity.QRCodeActivity;

public class FragmentQRCodeQuest extends Fragment implements View.OnClickListener {
    private View view;

    public FragmentQRCodeQuest() {
        // Required empty public constructor
    }

    public static FragmentQRCodeQuest newInstance() {
        return new FragmentQRCodeQuest();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_qrcode_quest, container, false);
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
            builder.setTitle("Scan Result");
            builder.setMessage(data.getStringExtra(QRCodeActivity.RESULT));
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }
}
