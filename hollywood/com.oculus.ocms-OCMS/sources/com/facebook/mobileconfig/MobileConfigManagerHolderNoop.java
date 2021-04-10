package com.facebook.mobileconfig;

import com.facebook.debug.log.BLog;
import com.facebook.mobileconfig.factory.MobileConfigOverridesTable;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;

public class MobileConfigManagerHolderNoop implements MobileConfigManagerHolder {
    private static final String TAG = "MobileConfigManagerHolderNoop";
    AtomicReference<String> mStartType = new AtomicReference<>("");

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public void clearCurrentUserData() {
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public void clearEmergencyPushChannel() {
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public void clearOverrides() {
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public void deleteOldUserData(int i) {
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public String getConsistencyLoggingFlagsJSON() {
        return "MobileConfigManagerHolderNoop: MobileConfig manager not yet initialized";
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public String getDataDirPath() {
        return "";
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public String getFrameworkStatus() {
        return "UNINITIALIZED";
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    @Nullable
    public MobileConfigMmapHandle getLatestHandle() {
        return null;
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    @Nullable
    public MobileConfigOverridesTable getNewOverridesTable() {
        return null;
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    @Nullable
    public MobileConfigOverridesTable getNewOverridesTableIfExists() {
        return null;
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public String getSchemaString() {
        return "";
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public boolean isConsistencyLoggingNeeded(ConsistencyType consistencyType) {
        return false;
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public boolean isFetchNeeded() {
        return false;
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public boolean isValid() {
        return false;
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public void logConfigs(String str, ConsistencyType consistencyType, Map<String, String> map) {
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public void logExposure(String str, String str2) {
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public void logExposure(String str, String str2, String str3) {
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public void logShadowResult(String str, String str2, String str3, String str4, String str5, String str6) {
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public void logStorageConsistency() {
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public boolean registerConfigChangeListener(MobileConfigCxxChangeListener mobileConfigCxxChangeListener) {
        return false;
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public boolean saveCurrentParamsMapToDisk() {
        return false;
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public boolean setEpHandler(MobileConfigEmergencyPushChangeListener mobileConfigEmergencyPushChangeListener) {
        return false;
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public boolean tryUpdateConfigs() {
        return false;
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public boolean tryUpdateConfigsSynchronously(int i) {
        return false;
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public boolean updateConfigsSynchronouslyWithDefaultUpdater(int i) {
        return false;
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public boolean updateEmergencyPushConfigs() {
        return false;
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public boolean updateEmergencyPushConfigsSynchronously(int i) {
        return false;
    }

    public void setStartType(String str) {
        this.mStartType.set(str);
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public boolean updateConfigs() {
        BLog.i(TAG, "updateConfigs");
        return false;
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public String syncFetchReason() {
        return "MobileConfigManagerHolderNoop: " + this.mStartType.get();
    }
}
