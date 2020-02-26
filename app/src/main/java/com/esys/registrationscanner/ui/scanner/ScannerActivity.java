package com.esys.registrationscanner.ui.scanner;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.esys.registrationscanner.R;
import com.esys.registrationscanner.ui.base.BaseActivity;
import com.esys.registrationscanner.utils.AppAlertDialog;
import com.esys.registrationscanner.utils.PermissionUtil;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import javax.inject.Inject;

import butterknife.ButterKnife;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScannerActivity extends BaseActivity implements ScannerMvpView, ZXingScannerView.ResultHandler {

    @Inject
    ScannerMvpPresenter<ScannerMvpView> mPresenter;
    private ZXingScannerView mScannerView;

    @Inject
    PermissionUtil mPermissionUtil;
    @Inject
    AppAlertDialog mAlertDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityFragmentComponent().inject(this);
        mPresenter.attachView(this);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        setUnBinder(ButterKnife.bind(this));
        init();
        //No UI Related Codes Here.. Proceed to init
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();//Remove Views to activity
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();

        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    protected void init() {
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionDenied(final PermissionDeniedResponse response) {
                        // check for permanent denial of permission
                        if (response.isPermanentlyDenied()) {
                            // navigate user to app settings
                            mPermissionUtil.showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionGranted(
                            final PermissionGrantedResponse response) {
                        mScannerView.startCamera();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(
                            final PermissionRequest permission, final PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    @Override
    public void handleResult(Result rawResult) {
        String idNumber = rawResult.getText();
        mPresenter.onRegister(idNumber);
    }

    @Override
    public void showErrorDialog(String message) {
        mAlertDialog.showMaterialDialog("Error", message)
                .setNegativeButton("CANCEL", (dialog, which) -> {
                    dialog.cancel();
                    finish();
                })
                .setPositiveButton("TRY AGAIN", (dialog, which) -> {
                    mScannerView.resumeCameraPreview(this);
                }).create().show();
    }

    @Override
    public void showRegistrationSuccessDialog(String message) {
        mAlertDialog.showMaterialDialog("Registration Successful", message)
                .setNegativeButton("CANCEL", (dialog, which) -> {
                    dialog.cancel();
                    finish();
                })
                .setPositiveButton("SCAN", (dialog, which) -> {
                    mScannerView.resumeCameraPreview(this);
                }).create().show();
    }

    @Override
    public void showInfoDialog(String title, String message) {
        mAlertDialog.showMaterialDialog(title, message)
                .setNegativeButton("CANCEL", (dialog, which) -> {
                    dialog.cancel();
                    finish();
                })
                .setPositiveButton("TRY AGAIN", (dialog, which) -> {
                    mScannerView.resumeCameraPreview(this);
                }).create().show();
    }
}
