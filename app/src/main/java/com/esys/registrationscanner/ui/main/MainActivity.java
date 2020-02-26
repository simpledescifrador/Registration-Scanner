package com.esys.registrationscanner.ui.main;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.esys.registrationscanner.R;
import com.esys.registrationscanner.ui.base.BaseActivity;
import com.esys.registrationscanner.ui.scanner.ScannerActivity;
import com.google.android.material.card.MaterialCardView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainMvpView {

    @BindView(R.id.main_tv_totalRegistered)
    TextView mMainTvTotalRegistered;
    @BindView(R.id.main_tv_noParticipants)
    TextView mMainTvNoParticipants;
    @BindView(R.id.main_cv_scan)
    MaterialCardView mMainCvScan;

    @Inject
    MainMvpPresenter<MainMvpView> mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityFragmentComponent().inject(this);
        mPresenter.attachView(this);
        setContentView(R.layout.activity_main);
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
    protected void init() {
        mPresenter.getTotalParticipants();
        mPresenter.getTotalRegistered();
    }

    /**
     *  Open Scan Activity
     */
    @OnClick(R.id.main_cv_scan)
    public void onViewClicked() {
        mPresenter.onScanMode();
    }

    @Override
    public void openScanner() {
        Intent intent = new Intent(this, ScannerActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    @Override
    public void setTotalParticipants(String totalParticipants) {
        mMainTvNoParticipants.setText("out of " + totalParticipants);
    }

    @Override
    public void setTotalRegistered(String totalRegistered) {
        mMainTvTotalRegistered.setText(totalRegistered);
    }
}
