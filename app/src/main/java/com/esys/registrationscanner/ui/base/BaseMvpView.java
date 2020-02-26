package com.esys.registrationscanner.ui.base;

import androidx.annotation.StringRes;

public interface BaseMvpView {
    void showLoading();

    void hideLoading();

    void onError(@StringRes int resId);

    void onError(String message);

    boolean isNetworkConnected();

    void hideKeyboard();
}
