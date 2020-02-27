package com.esys.registrationscanner.di.module;

import android.content.Context;
import com.esys.registrationscanner.data.prefs.AppPreferenceHelper;
import com.esys.registrationscanner.data.prefs.CommonPreferenceHelper;
import com.esys.registrationscanner.data.prefs.PreferenceHelper;
import com.esys.registrationscanner.di.qualifier.ApplicationContext;
import com.esys.registrationscanner.di.scope.ApplicationScope;
import dagger.Module;
import dagger.Provides;

@Module
public class PreferencesModule {
    @Provides
    @ApplicationScope
    static PreferenceHelper provideApplicationPreference(CommonPreferenceHelper commonPreferenceHelper) {
        return new AppPreferenceHelper(commonPreferenceHelper);
    }

    @Provides
    @ApplicationScope
    static CommonPreferenceHelper providePreferencesHelper(@ApplicationContext Context context) {
        return new CommonPreferenceHelper(context);
    }
}
