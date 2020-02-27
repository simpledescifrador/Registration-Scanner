package com.esys.registrationscanner.ui.scanner;

import android.os.Handler;
import android.text.TextUtils;

import com.esys.registrationscanner.R;
import com.esys.registrationscanner.data.DataManager;
import com.esys.registrationscanner.pojo.RegistrationDetails;
import com.esys.registrationscanner.pojo.response.ResponseWrapper;
import com.esys.registrationscanner.ui.base.BasePresenter;


import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class ScannerPresenter<V extends ScannerMvpView> extends BasePresenter<V> implements ScannerMvpPresenter<V> {

    @Inject
    ScannerPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onRegister(final String idNumber) {
        if (TextUtils.isEmpty(idNumber)) {
            getMvpView().showErrorDialog("ID Number is invalid.");
        } else {
            //Continue to registration
            getMvpView().showLoading();

            getDataManager().register(idNumber)
                    .observeOn(getSchedulerProvider().ui())
                    .subscribeOn(getSchedulerProvider().io())
                    .delaySubscription(2000, TimeUnit.MILLISECONDS)
                    .doFinally(() -> {
                        getMvpView().hideLoading();
                    })
                    .subscribe(new SingleObserver<ResponseWrapper<RegistrationDetails>>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            getCompositeDisposable().add(d);
                        }

                        @Override
                        public void onSuccess(ResponseWrapper<RegistrationDetails> response) {
                            if (!isViewAttached()) {
                                return;
                            }

                            try {
                                RegistrationDetails details = response.getData();

                                switch (response.getStatusCode()) {
                                    case 0: //Error
                                        getMvpView().showErrorDialog("Registration Failed");
                                        break;
                                    case 1: //Success Registered
                                        getMvpView().showRegistrationSuccessDialog("Scan Again?");
                                        break;
                                    case 2: //Already Registered
                                        getMvpView().showInfoDialog("Message",
                                                details.getName() + " is already registered.");
                                        break;
                                }
                            } catch (Exception e) {
                                getMvpView().onError("Opps... Error Occurred.");
                            }


                        }

                        @Override
                        public void onError(Throwable e) {
                            if (!isViewAttached()) {
                                return;
                            }
                            if (e instanceof SocketTimeoutException || e instanceof SocketException) {
                                getMvpView().showErrorDialog("Network Failed. Please Try Again");
                            }
                        }
                    });
        }
    }
}
