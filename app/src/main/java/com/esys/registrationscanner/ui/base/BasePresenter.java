package com.esys.registrationscanner.ui.base;

import androidx.annotation.NonNull;

import com.esys.registrationscanner.data.DataManager;
import com.esys.registrationscanner.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 */
public class BasePresenter<V extends BaseMvpView> implements Presenter<V> {
    public static class MvpViewNotAttachedException extends RuntimeException {

        MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(BaseMvpView) before"
                    + " requesting data to the Presenter");
        }
    }

    @Inject
    CompositeDisposable mCompositeDisposable;

    @Inject
    SchedulerProvider mSchedulerProvider;

    private DataManager mDataManager;

    private V mvpView;

    @Inject
    public BasePresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(@NonNull V mvpView) {
        this.mvpView = mvpView;
    }

    @Override
    public void detachView() {
        mvpView = null;
        mCompositeDisposable.dispose();
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    public V getMvpView() {
        return mvpView;
    }

    public boolean isViewAttached() {
        return mvpView != null;
    }

    protected void checkViewAttached() {
        if (!isViewAttached()) {
            throw new MvpViewNotAttachedException();
        }
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    public SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }
}
