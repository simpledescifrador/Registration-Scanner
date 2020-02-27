package com.esys.registrationscanner.data.network;

public class ApiConstants {
    /*------- API HEADERS -------------*/
    //Base Urls
    public static final String BASE_URL = "http://192.168.254.108/EventRegistration/";
    /*------- END OF API HEADERS -------------*/

    static final String POST_REGISTER_REQUEST_URL = "ws/EventRegistration.asmx/Register";

    static final String GET_TOTAL_REGISTERED_REQUEST_URL = "ws/EventRegistration.asmx/SELECT_TotalRegistered?";

    static final String GET_TOTAL_PARTICIPANTS_REQUEST_URL = "ws/EventRegistration.asmx/SELECT_TotalParticipants?";
}
