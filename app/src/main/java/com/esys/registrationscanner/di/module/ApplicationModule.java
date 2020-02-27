package com.esys.registrationscanner.di.module;

import android.app.Application;
import android.content.Context;

import com.esys.registrationscanner.data.AppDataManager;
import com.esys.registrationscanner.data.DataManager;
import com.esys.registrationscanner.data.network.ApiHelper;
import com.esys.registrationscanner.data.prefs.PreferenceHelper;
import com.esys.registrationscanner.di.qualifier.ApplicationContext;
import com.esys.registrationscanner.di.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module(includes = {ContextModule.class, PreferencesModule.class, RetrofitModule.class, DatabaseModule.class})
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }

    @Provides
    @ApplicationScope
    static AppDataManager provideAppDataManager(@ApplicationContext Context context,
            PreferenceHelper preferenceHelper, ApiHelper apiHelper) {
        return new AppDataManager(context, preferenceHelper, apiHelper);
    }

    @Provides
    @ApplicationScope
    static DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }
}
