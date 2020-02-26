package com.esys.registrationscanner.data.network;

import com.esys.registrationscanner.pojo.response.ResponseWrapper;
import com.esys.registrationscanner.pojo.RegistrationDetails;

import javax.inject.Inject;

import io.reactivex.Single;

public class AppApiHelper implements ApiHelper {
    private ApiInterface mApiInterface;

    @Inject
    public AppApiHelper(ApiInterface mApiInterface) {
        this.mApiInterface = mApiInterface;
    }

    @Override
    public Single<ResponseWrapper<Integer>> totalRegistered() {
        return mApiInterface.getTotalRegistered();
    }

    @Override
    public Single<ResponseWrapper<Integer>> totalParticipants() {
        return mApiInterface.getTotalParticipants();
    }

    @Override
    public Single<ResponseWrapper<RegistrationDetails>> register(String idNumber) {
        return mApiInterface.register(idNumber);
    }
}
