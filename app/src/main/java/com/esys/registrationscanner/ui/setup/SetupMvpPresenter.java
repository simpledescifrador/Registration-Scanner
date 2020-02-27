package com.esys.registrationscanner.ui.setup;

import com.esys.registrationscanner.ui.base.BaseMvpView;
import com.esys.registrationscanner.ui.base.Presenter;

public interface SetupMvpPresenter<V extends SetupMvpView & BaseMvpView> extends Presenter<V> {
    void onSetupIpAddress(String ipAddress);

    void checkSetupStatus();
}
