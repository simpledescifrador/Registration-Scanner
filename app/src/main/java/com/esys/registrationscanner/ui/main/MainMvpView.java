package com.esys.registrationscanner.ui.main;

import com.esys.registrationscanner.ui.base.BaseMvpView;

public interface MainMvpView extends BaseMvpView {
    void openScanner();
    void setTotalParticipants(String totalParticipants);
    void setTotalRegistered(String totalRegistered);
    void setIpDetails(String ipAddress, String ipStatus);
    void toSetupActivity();
}
