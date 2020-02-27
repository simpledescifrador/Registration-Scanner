package com.esys.registrationscanner.data.prefs;

import javax.inject.Inject;

public class AppPreferenceHelper implements PreferenceHelper {

    private CommonPreferenceHelper mCommonPreferenceHelper;

    private static final String PREF_KEY_IP_ADDRESS = "PREF_KEY_IP_ADDRESS";

    private static final String PREF_KEY_SETUP_IP_ADDRESS = "PREF_KEY_SETUP_IP_ADDRESS";

    @Inject
    public AppPreferenceHelper(final CommonPreferenceHelper commonPreferenceHelper) {
        mCommonPreferenceHelper = commonPreferenceHelper;
    }

    @Override
    public String getIpAddress() {
        return mCommonPreferenceHelper.getStringFromPrefs(PREF_KEY_IP_ADDRESS);
    }

    @Override
    public void setIpAddress(String ipAddress) {
        mCommonPreferenceHelper.setStringToPrefs(PREF_KEY_IP_ADDRESS, ipAddress);
    }

    @Override
    public Boolean isSetupCompleted() {
        return mCommonPreferenceHelper.getBooleanFromPrefs(PREF_KEY_SETUP_IP_ADDRESS);
    }

    @Override
    public void setSetupStatus(final boolean setupStatus) {
        mCommonPreferenceHelper.setBooleanToPrefs(PREF_KEY_SETUP_IP_ADDRESS, setupStatus);
    }
}
