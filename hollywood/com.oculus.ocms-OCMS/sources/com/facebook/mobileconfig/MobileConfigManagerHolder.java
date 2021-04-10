package com.facebook.mobileconfig;

import com.facebook.mobileconfig.factory.MobileConfigOverridesTable;
import java.util.Map;

public interface MobileConfigManagerHolder {
    void clearCurrentUserData();

    void clearEmergencyPushChannel();

    void clearOverrides();

    void deleteOldUserData(int i);

    String getConsistencyLoggingFlagsJSON();

    String getDataDirPath();

    String getFrameworkStatus();

    MobileConfigMmapHandle getLatestHandle();

    MobileConfigOverridesTable getNewOverridesTable();

    MobileConfigOverridesTable getNewOverridesTableIfExists();

    String getSchemaString();

    boolean isConsistencyLoggingNeeded(ConsistencyType consistencyType);

    boolean isFetchNeeded();

    boolean isValid();

    void logConfigs(String str, ConsistencyType consistencyType, Map<String, String> map);

    void logExposure(String str, String str2);

    void logExposure(String str, String str2, String str3);

    void logShadowResult(String str, String str2, String str3, String str4, String str5, String str6);

    void logStorageConsistency();

    boolean registerConfigChangeListener(MobileConfigCxxChangeListener mobileConfigCxxChangeListener);

    boolean saveCurrentParamsMapToDisk();

    boolean setEpHandler(MobileConfigEmergencyPushChangeListener mobileConfigEmergencyPushChangeListener);

    String syncFetchReason();

    boolean tryUpdateConfigs();

    boolean tryUpdateConfigsSynchronously(int i);

    boolean updateConfigs();

    boolean updateConfigsSynchronouslyWithDefaultUpdater(int i);

    boolean updateEmergencyPushConfigs();

    boolean updateEmergencyPushConfigsSynchronously(int i);
}
