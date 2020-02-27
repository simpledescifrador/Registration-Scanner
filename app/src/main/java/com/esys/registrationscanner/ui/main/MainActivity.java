package com.esys.registrationscanner.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.esys.registrationscanner.R;
import com.esys.registrationscanner.ui.base.BaseActivity;
import com.esys.registrationscanner.ui.scanner.ScannerActivity;
import com.esys.registrationscanner.ui.setup.SetupActivity;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;
import java.sql.ResultSet;
import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainMvpView {

    @BindView(R.id.main_tv_ipAddress)
    TextView mMainTvIpAddress;

    @BindView(R.id.main_tv_ipStatus)
    MaterialTextView mMainTvIpStatus;

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

    @OnClick({R.id.main_cv_scan, R.id.main_tv_ipStatus})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_cv_scan:
                mPresenter.onScanMode();
                break;
            case R.id.main_tv_ipStatus:
                mPresenter.disconnect();
                break;
        }
    }

    @Override
    protected void init() {
        mPresenter.getTotalParticipants();
        mPresenter.getTotalRegistered();
        mPresenter.getIpDetails();
    }

    @Override
    public void openScanner() {
        Intent intent = new Intent(this, ScannerActivity.class);
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            mPresenter.getTotalParticipants();
            mPresenter.getTotalRegistered();
        }
    }

    @Override
    public void setTotalParticipants(String totalParticipants) {
        mMainTvNoParticipants.setText("out of " + totalParticipants);
    }

    @Override
    public void setTotalRegistered(String totalRegistered) {
        mMainTvTotalRegistered.setText(totalRegistered);
    }

    @Override
    public void setIpDetails(final String ipAddress, final String ipStatus) {
        mMainTvIpAddress.setText(ipAddress);
        mMainTvIpStatus.setText(ipStatus);
    }

    @Override
    public void toSetupActivity() {
        Intent intent = new Intent(this, SetupActivity.class);
        startActivity(intent);
        finish();
    }
}
