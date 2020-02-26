package com.esys.registrationscanner.data.network;

import com.esys.registrationscanner.pojo.response.ResponseWrapper;
import com.esys.registrationscanner.pojo.RegistrationDetails;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST(ApiConstants.POST_REGISTER_REQUEST_URL)
    Single<ResponseWrapper<RegistrationDetails>> register(
            @Field("id_number") String idNumber
    );

    @GET(ApiConstants.GET_TOTAL_PARTICIPANTS_REQUEST_URL)
    Single<ResponseWrapper<Integer>> getTotalParticipants();

    @GET(ApiConstants.GET_TOTAL_REGISTERED_REQUEST_URL)
    Single<ResponseWrapper<Integer>> getTotalRegistered();
}
