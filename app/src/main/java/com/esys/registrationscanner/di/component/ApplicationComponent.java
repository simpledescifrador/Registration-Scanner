package com.esys.registrationscanner.di.component;

import android.content.Context;

import com.esys.registrationscanner.MainApp;
import com.esys.registrationscanner.data.DataManager;
import com.esys.registrationscanner.di.module.ApplicationModule;
import com.esys.registrationscanner.di.qualifier.ApplicationContext;
import com.esys.registrationscanner.di.scope.ApplicationScope;

import dagger.Component;

@ApplicationScope
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    @ApplicationContext
    Context getContext();

    DataManager getDataManager();

    void inject(MainApp application);
}
