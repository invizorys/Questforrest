package com.mediahack.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.zxing.Result;
import com.mediahack.R;

import me.dm7.barcodescanner.zbar.ZBarScannerView;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QRCodeActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    public static final String RESULT = "result";
    public static final int RESULT_CODE = 101;
    private ZBarScannerView mZBarScannerView;
    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);

        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void handleResult(Result result) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(RESULT, result.getText());
        setResult(RESULT_CODE, returnIntent);
        finish();
    }
}
