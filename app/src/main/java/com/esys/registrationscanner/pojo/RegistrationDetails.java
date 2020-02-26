package com.esys.registrationscanner.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegistrationDetails {
    @SerializedName("id_number")
    @Expose
    private String idNumber;

    @SerializedName("name")
    @Expose
    private String name;

    public String getIdNumber() {
        return idNumber;
    }

    public String getName() {
        return name;
    }
}
