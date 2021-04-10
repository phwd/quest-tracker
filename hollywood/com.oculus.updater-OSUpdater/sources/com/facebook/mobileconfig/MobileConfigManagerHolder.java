package com.facebook.mobileconfig;

import com.facebook.mobileconfig.factory.MobileConfigOverridesTable;

public interface MobileConfigManagerHolder {
    MobileConfigMmapHandle getLatestHandle();

    MobileConfigOverridesTable getNewOverridesTableIfExists();

    boolean isFetchNeeded();

    boolean isValid();

    void logExposure(String str, String str2, String str3);

    void logShadowResult(String str, String str2, String str3, String str4, String str5, String str6);

    boolean registerConfigChangeListener(MobileConfigCxxChangeListener mobileConfigCxxChangeListener);

    boolean setEpHandler(MobileConfigEmergencyPushChangeListener mobileConfigEmergencyPushChangeListener);

    String syncFetchReason();

    boolean tryUpdateConfigsSynchronously(int i);

    boolean updateConfigs();
}
