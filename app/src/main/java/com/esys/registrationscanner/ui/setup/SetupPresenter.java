package com.esys.registrationscanner.ui.setup;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Patterns;
import com.esys.registrationscanner.data.DataManager;
import com.esys.registrationscanner.ui.base.BasePresenter;

import javax.inject.Inject;

public class SetupPresenter<V extends SetupMvpView> extends BasePresenter<V> implements SetupMvpPresenter<V> {

    @Inject
    SetupPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onSetupIpAddress(final String ipAddress) {
        getMvpView().resetErrors();
        getMvpView().hideKeyboard();
        getMvpView().showLoading();

        if (TextUtils.isEmpty(ipAddress)) {
            getMvpView().hideLoading();
            getMvpView().invalidIpAddress("Enter the ip address");
        } else {

            if (!Patterns.IP_ADDRESS.matcher(ipAddress).matches()) {
                getMvpView().hideLoading();
                getMvpView().invalidIpAddress("Invalid IP Address");
                return;
            }

            //Save IP Address
            getDataManager().setIpAddress(ipAddress);
            getDataManager().setSetupStatus(true);
            getMvpView().setRetrofitHost(ipAddress);

            new Handler().postDelayed(() -> {
                getMvpView().hideLoading();
                getMvpView().toMainActivity();
            }, 1000);
        }
    }

    @Override
    public void checkSetupStatus() {
        boolean isSetupCompleted = getDataManager().isSetupCompleted();
        if (isSetupCompleted) {
            getMvpView().setRetrofitHost(getDataManager().getIpAddress());
            getMvpView().toMainActivity();
        }
        //Continue to Setup
    }


}
