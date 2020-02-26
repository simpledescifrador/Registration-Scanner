package com.esys.registrationscanner.data.network;

import com.esys.registrationscanner.pojo.response.ResponseWrapper;
import com.esys.registrationscanner.pojo.RegistrationDetails;

import io.reactivex.Single;

public interface ApiHelper {
    Single<ResponseWrapper<Integer>> totalRegistered();
    Single<ResponseWrapper<Integer>> totalParticipants();
    Single<ResponseWrapper<RegistrationDetails>> register(String idNumber);
}
