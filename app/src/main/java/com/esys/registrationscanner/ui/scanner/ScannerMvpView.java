package com.esys.registrationscanner.ui.scanner;

import com.esys.registrationscanner.ui.base.BaseMvpView;

public interface ScannerMvpView extends BaseMvpView {
    void showErrorDialog(String message);
    void showRegistrationSuccessDialog(String message);
    void showInfoDialog(String title, String message);
}
