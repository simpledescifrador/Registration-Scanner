package com.esys.registrationscanner.ui.main;

import com.esys.registrationscanner.ui.base.BaseMvpView;
import com.esys.registrationscanner.ui.base.Presenter;

public interface MainMvpPresenter<V extends MainMvpView & BaseMvpView> extends Presenter<V> {
    void onScanMode();
    void getTotalRegistered();
    void getTotalParticipants();
    void getIpDetails();
    void disconnect();
}
