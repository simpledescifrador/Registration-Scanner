package com.esys.registrationscanner.data.prefs;

public interface PreferenceHelper {

    String getIpAddress();

    void setIpAddress(String ipAddress);

    Boolean isSetupCompleted();

    void setSetupStatus(boolean setupStatus);
}
