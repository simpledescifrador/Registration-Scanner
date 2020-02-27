package com.esys.registrationscanner.di.component;

import com.esys.registrationscanner.di.module.ActivityFragmentModule;
import com.esys.registrationscanner.di.scope.ActivityScope;
import com.esys.registrationscanner.ui.main.MainActivity;
import com.esys.registrationscanner.ui.scanner.ScannerActivity;
import com.esys.registrationscanner.ui.setup.SetupActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = ActivityFragmentModule.class)
public interface ActivityFragmentComponent {
    void inject(MainActivity mainActivity);
    void inject(ScannerActivity scannerActivity);
    void inject(SetupActivity setupActivity);
}
