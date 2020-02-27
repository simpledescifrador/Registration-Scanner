package com.esys.registrationscanner.data.prefs;

public interface BasePreferenceHelper {

    boolean checkIfContainsValue(String key);

    void clearPrefs();

    boolean getBooleanFromPrefs(String key);

    int getIntFromPrefs(String key);

    long getLongFromPrefs(String key);

    String getStringFromPrefs(String key);

    String getStringFromPrefs(String key, String defaultValue);

    void setBooleanToPrefs(String key, boolean value);

    void setIntToPrefs(String key, int value);

    void setLongToPrefs(String key, long value);

    void setStringToPrefs(String key, String value);
}
