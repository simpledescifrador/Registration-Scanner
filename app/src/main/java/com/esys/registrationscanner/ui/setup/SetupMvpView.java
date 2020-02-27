package com.esys.registrationscanner.ui.setup;

import com.esys.registrationscanner.ui.base.BaseMvpView;

public interface SetupMvpView extends BaseMvpView {
    void toMainActivity();
    void invalidIpAddress(String errorMessage);
    void resetErrors();
    void setRetrofitHost(String ipAddress);
}
