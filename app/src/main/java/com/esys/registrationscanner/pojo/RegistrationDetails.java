package com.esys.registrationscanner.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegistrationDetails {
    @SerializedName("IdNumber")
    @Expose
    private String idNumber;

    @SerializedName("Name")
    @Expose
    private String name;

    public String getIdNumber() {
        return idNumber;
    }

    public String getName() {
        return name;
    }
}
