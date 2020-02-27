package com.esys.registrationscanner.data.network;

public class ApiConstants {
    /*------- API HEADERS -------------*/
    //Base Urls
    public static final String BASE_URL = "http://172.25.211.62/EventRegistration/";

    public static final String CONTENT_TYPE = "application/json";

    /*------- END OF API HEADERS -------------*/

    static final String POST_REGISTER_REQUEST_URL = "ws/eventregistration.asmx/Register";

    static final String GET_TOTAL_REGISTERED_REQUEST_URL = "ws/eventregistration.asmx/SELECT_TotalRegistered";

    static final String GET_TOTAL_PARTICIPANTS_REQUEST_URL = "ws/eventregistration.asmx/SELECT_TotalParticipants";
}
