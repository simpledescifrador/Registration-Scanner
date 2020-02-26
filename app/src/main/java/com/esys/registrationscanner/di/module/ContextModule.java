package com.esys.registrationscanner.di.module;

import android.content.Context;

import com.esys.registrationscanner.di.qualifier.ApplicationContext;
import com.esys.registrationscanner.di.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationScope
    @ApplicationContext
    Context provideContext() {
        return context;
    }
}
