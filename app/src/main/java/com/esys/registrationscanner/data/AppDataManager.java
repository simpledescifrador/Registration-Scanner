package com.esys.registrationscanner.data;

import android.content.Context;

import com.esys.registrationscanner.data.network.ApiHelper;
import com.esys.registrationscanner.data.prefs.PreferenceHelper;
import com.esys.registrationscanner.pojo.response.ResponseWrapper;
import com.esys.registrationscanner.di.qualifier.ApplicationContext;
import com.esys.registrationscanner.pojo.RegistrationDetails;

import io.reactivex.Single;

public class AppDataManager implements DataManager {

    private final PreferenceHelper mPreferenceHelper;

    private final ApiHelper mApiHelper;

    private final Context context;

    public AppDataManager(@ApplicationContext Context context, PreferenceHelper preferenceHelper,
            ApiHelper apiHelper) {
        this.context = context;
        mPreferenceHelper = preferenceHelper;
        mApiHelper = apiHelper;
    }

    @Override
    public String getIpAddress() {
        return mPreferenceHelper.getIpAddress();
    }

    @Override
    public void setIpAddress(final String ipAddress) {
        mPreferenceHelper.setIpAddress(ipAddress);
    }

    @Override
    public Boolean isSetupCompleted() {
        return mPreferenceHelper.isSetupCompleted();
    }

    @Override
    public void setSetupStatus(final boolean setupStatus) {
        mPreferenceHelper.setSetupStatus(setupStatus);
    }

    @Override
    public Single<ResponseWrapper<Integer>> totalRegistered() {
        return mApiHelper.totalRegistered();
    }

    @Override
    public Single<ResponseWrapper<Integer>> totalParticipants() {
        return mApiHelper.totalParticipants();
    }

    @Override
    public Single<ResponseWrapper<RegistrationDetails>> register(String idNumber) {
        return mApiHelper.register(idNumber);
    }
}
