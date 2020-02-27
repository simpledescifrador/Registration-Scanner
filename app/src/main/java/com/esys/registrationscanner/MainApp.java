package com.esys.registrationscanner;

import android.app.Application;

import com.esys.registrationscanner.data.network.HostSelectionInterceptor;
import com.esys.registrationscanner.di.component.ApplicationComponent;
import com.esys.registrationscanner.di.component.DaggerApplicationComponent;
import com.esys.registrationscanner.di.module.ApplicationModule;
import com.esys.registrationscanner.di.module.ContextModule;
import com.esys.registrationscanner.di.module.RetrofitModule;

public class MainApp extends Application {

    public static ApplicationComponent mApplicationComponent;

    private static HostSelectionInterceptor mHostSelectionInterceptor;

    @Override
    public void onCreate() {
        super.onCreate();
        initApplicationDaggerComponent();
    }

    public static ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    /**
     *  Set Retrofit Dynamic Host/Base Url
     * @param host
     */
    public static void setHostSelectionInterceptorHost(String host) {
        String hostPrefix = "http://";
        mHostSelectionInterceptor.setHost(hostPrefix + host + "/");
    }

    private void initApplicationDaggerComponent() {
        mHostSelectionInterceptor = new HostSelectionInterceptor();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .contextModule(new ContextModule(this))
                .retrofitModule(new RetrofitModule(mHostSelectionInterceptor))
                .build();

        mApplicationComponent.inject(this);
    }

}
