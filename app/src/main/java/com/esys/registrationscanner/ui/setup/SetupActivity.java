package com.esys.registrationscanner.ui.setup;

import android.content.Intent;
import android.os.Bundle;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.esys.registrationscanner.MainApp;
import com.esys.registrationscanner.R;
import com.esys.registrationscanner.ui.base.BaseActivity;
import com.esys.registrationscanner.ui.main.MainActivity;
import com.esys.registrationscanner.ui.scanner.ScannerActivity;
import com.esys.registrationscanner.utils.AppAlertDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.util.Objects;
import javax.inject.Inject;

public class SetupActivity extends BaseActivity implements SetupMvpView {

    @BindView(R.id.setup_btn_continue)
    MaterialButton mSetupBtnContinue;

    @BindView(R.id.setup_et_ipAddress)
    TextInputEditText mSetupEtIpAddress;

    @BindView(R.id.setup_il_ipAddress)
    TextInputLayout mSetupIlIpAddress;

    @Inject
    SetupMvpPresenter<SetupMvpView> mPresenter;

    @Inject
    AppAlertDialog mAlertDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityFragmentComponent().inject(this);
        mPresenter.attachView(this);
        setContentView(R.layout.activity_setup);
        setUnBinder(ButterKnife.bind(this));
        init();
        //No UI Related Codes Here.. Proceed to init
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public void toMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void invalidIpAddress(String errorMessage) {
        mSetupIlIpAddress.setError(errorMessage);
        mSetupIlIpAddress.setErrorEnabled(true);

    }

    @Override
    public void resetErrors() {
        mSetupIlIpAddress.setErrorEnabled(false);
        mSetupIlIpAddress.setError(null);
    }

    @Override
    public void setRetrofitHost(final String ipAddress) {
        MainApp.setHostSelectionInterceptorHost(ipAddress);
    }

    @OnClick(R.id.setup_btn_continue)
    public void onViewClicked() {
        String ipAddress = Objects.requireNonNull(mSetupEtIpAddress.getText()).toString().trim();
        mPresenter.onSetupIpAddress(ipAddress);
    }

    @Override
    protected void init() {
        mPresenter.checkSetupStatus();
    }
}
