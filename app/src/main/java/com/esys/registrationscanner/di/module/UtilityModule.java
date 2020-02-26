package com.esys.registrationscanner.di.module;

import android.app.Activity;

import com.esys.registrationscanner.di.scope.ActivityScope;
import com.esys.registrationscanner.utils.AppAlertDialog;
import com.esys.registrationscanner.utils.PermissionUtil;

import dagger.Module;
import dagger.Provides;

@Module
public class UtilityModule {
    private Activity activity;

    public UtilityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    AppAlertDialog provideAppDialogUtils() {
        return new AppAlertDialog(activity);
    }

    @Provides
    @ActivityScope
    PermissionUtil providePermissionUtils() {
        return new PermissionUtil(activity);
    }
}
