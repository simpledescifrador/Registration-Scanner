package com.esys.registrationscanner.ui.scanner;

import com.esys.registrationscanner.ui.base.BaseMvpView;
import com.esys.registrationscanner.ui.base.Presenter;

public interface ScannerMvpPresenter<V extends ScannerMvpView & BaseMvpView> extends Presenter<V> {
    void onRegister(String idNumber);
}
