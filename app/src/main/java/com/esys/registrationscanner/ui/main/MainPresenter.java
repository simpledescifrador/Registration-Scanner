package com.esys.registrationscanner.ui.main;

import android.os.Handler;

import com.esys.registrationscanner.R;
import com.esys.registrationscanner.data.DataManager;
import com.esys.registrationscanner.pojo.response.ResponseWrapper;
import com.esys.registrationscanner.ui.base.BasePresenter;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter<V> {

    @Inject
    MainPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onScanMode() {
        getMvpView().showLoading();

        new Handler().postDelayed(() -> {
            getMvpView().openScanner();
            getMvpView().hideLoading();
        }, 1000);
    }

    @Override
    public void getTotalRegistered() {
//        getMvpView().setTotalRegistered("15");
        getDataManager().totalParticipants()
                .observeOn(getSchedulerProvider().ui())
                .subscribeOn(getSchedulerProvider().io())
                .delaySubscription(1000, TimeUnit.MILLISECONDS)
                .subscribe(new SingleObserver<ResponseWrapper<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onSuccess(ResponseWrapper<Integer> response) {
                        if (!isViewAttached()) {
                            return;
                        }

                        if (response.getStatusCode() != 0) {
                            //Successful
                            try {
                                getMvpView().setTotalRegistered(response.getData().toString());
                            }catch (Exception e) {
                                getMvpView().onError("Opps... Error Occurred.");
                            }
                        } else {
                            //Error
                            getMvpView().setTotalParticipants("0");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (!isViewAttached()) {
                            return;
                        }
                        if (e instanceof SocketTimeoutException || e instanceof SocketException) {
                            getMvpView().onError(R.string.error_network_failed);
                        }
                    }
                });
    }

    @Override
    public void getTotalParticipants() {
//        getMvpView().setTotalParticipants("49");
        getDataManager().totalRegistered()
                .observeOn(getSchedulerProvider().ui())
                .subscribeOn(getSchedulerProvider().io())
                .delaySubscription(1000, TimeUnit.MILLISECONDS)
                .subscribe(new SingleObserver<ResponseWrapper<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onSuccess(ResponseWrapper<Integer> response) {
                        if (!isViewAttached()) {
                            return;
                        }

                        if (response.getStatusCode() != 0) {
                            //Successful
                            try {
                                getMvpView().setTotalParticipants(response.getData().toString());
                            }catch (Exception e) {
                                getMvpView().onError("Opps... Error Occurred.");
                            }
                        } else {
                            //Error
                            getMvpView().setTotalParticipants("0");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (!isViewAttached()) {
                            return;
                        }
                        if (e instanceof SocketTimeoutException || e instanceof SocketException) {
                            getMvpView().onError(R.string.error_network_failed);
                        }
                    }
                });
    }
}
