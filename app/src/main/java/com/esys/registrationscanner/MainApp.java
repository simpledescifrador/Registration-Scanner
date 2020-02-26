package com.esys.registrationscanner;

import android.app.Application;

import com.esys.registrationscanner.di.component.ApplicationComponent;
import com.esys.registrationscanner.di.component.DaggerApplicationComponent;
import com.esys.registrationscanner.di.module.ApplicationModule;
import com.esys.registrationscanner.di.module.ContextModule;

public class MainApp extends Application {
    public static ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initApplicationDaggerComponent();
    }

    public static ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    private void initApplicationDaggerComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .contextModule(new ContextModule(this))
                .build();

        mApplicationComponent.inject(this);
    }

}
