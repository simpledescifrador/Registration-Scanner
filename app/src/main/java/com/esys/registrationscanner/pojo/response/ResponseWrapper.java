package com.esys.registrationscanner.pojo.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseWrapper<T> {
    @SerializedName("StatusCode")
    @Expose
    private Integer statusCode;

    @SerializedName("Message")
    @Expose
    private String message;

    @SerializedName("Data")
    @Expose
    private T data;

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
