package com.esys.registrationscanner.di.module;

import android.app.Activity;
import android.content.Context;

import com.esys.registrationscanner.di.qualifier.ActivityContext;
import com.esys.registrationscanner.di.scope.ActivityScope;
import com.esys.registrationscanner.ui.main.MainMvpPresenter;
import com.esys.registrationscanner.ui.main.MainMvpView;
import com.esys.registrationscanner.ui.main.MainPresenter;
import com.esys.registrationscanner.ui.scanner.ScannerMvpPresenter;
import com.esys.registrationscanner.ui.scanner.ScannerMvpView;
import com.esys.registrationscanner.ui.scanner.ScannerPresenter;
import com.esys.registrationscanner.ui.setup.SetupMvpPresenter;
import com.esys.registrationscanner.ui.setup.SetupMvpView;
import com.esys.registrationscanner.ui.setup.SetupPresenter;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import dagger.Module;
import dagger.Provides;

@Module(includes = {RxModule.class, UtilityModule.class})
public class ActivityFragmentModule {
    private Activity activity;

    public ActivityFragmentModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    Activity provideActivity() {
        return activity;
    }

    @Provides
    @ActivityContext
    Context provideActivityContext() {
        return activity;
    }

    @Provides
    @ActivityScope
    BottomSheetDialog provideBottomSheetDialog() {
        return new BottomSheetDialog(activity);
    }

    @Provides
    @ActivityScope
    static MainMvpPresenter<MainMvpView> provideMainPresenter(MainPresenter<MainMvpView> presenter) {
        return presenter;
    }

    @Provides
    @ActivityScope
    static ScannerMvpPresenter<ScannerMvpView> provideScannerPresenter(ScannerPresenter<ScannerMvpView> presenter) {
        return presenter;
    }

    @Provides
    @ActivityScope
    static SetupMvpPresenter<SetupMvpView> provideSetupPresenter(SetupPresenter<SetupMvpView> presenter) {
        return presenter;
    }
}