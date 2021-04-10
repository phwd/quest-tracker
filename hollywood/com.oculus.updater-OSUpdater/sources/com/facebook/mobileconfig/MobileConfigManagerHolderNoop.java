package com.facebook.mobileconfig;

import com.facebook.debug.log.BLog;
import com.facebook.mobileconfig.factory.MobileConfigOverridesTable;
import com.oculus.common.build.BuildConfig;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;

public class MobileConfigManagerHolderNoop implements MobileConfigManagerHolder {
    AtomicReference<String> mStartType = new AtomicReference<>(BuildConfig.PROVIDER_SUFFIX);

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    @Nullable
    public MobileConfigMmapHandle getLatestHandle() {
        return null;
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    @Nullable
    public MobileConfigOverridesTable getNewOverridesTableIfExists() {
        return null;
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
    public void logExposure(String str, String str2, String str3) {
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public void logShadowResult(String str, String str2, String str3, String str4, String str5, String str6) {
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public boolean registerConfigChangeListener(MobileConfigCxxChangeListener mobileConfigCxxChangeListener) {
        return false;
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public boolean setEpHandler(MobileConfigEmergencyPushChangeListener mobileConfigEmergencyPushChangeListener) {
        return false;
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public boolean tryUpdateConfigsSynchronously(int i) {
        return false;
    }

    public void setStartType(String str) {
        this.mStartType.set(str);
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public boolean updateConfigs() {
        BLog.i("MobileConfigManagerHolderNoop", "updateConfigs");
        return false;
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public String syncFetchReason() {
        return "MobileConfigManagerHolderNoop: " + this.mStartType.get();
    }
}
