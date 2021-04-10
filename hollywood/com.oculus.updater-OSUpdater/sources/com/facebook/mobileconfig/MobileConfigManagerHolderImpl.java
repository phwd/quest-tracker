package com.facebook.mobileconfig;

import android.content.res.AssetManager;
import com.facebook.jni.HybridData;
import com.facebook.mobileconfig.factory.MobileConfigOverridesTable;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.soloader.nativeloader.NativeLoader;
import java.util.Map;

public class MobileConfigManagerHolderImpl implements MobileConfigManagerHolder {
    private String mDataDirPath;
    private boolean mHasSessionId;
    @DoNotStrip
    private final HybridData mHybridData;

    public static native String getParamMapContentFromAsset(AssetManager assetManager);

    public native void clearCurrentUserData();

    public native void clearEmergencyPushChannel();

    public native void clearOverrides();

    public native void deleteOldUserData(int i);

    public native String getConsistencyLoggingFlagsJSON();

    public native String getFrameworkStatus();

    public native MobileConfigMmapHandleHolder getLatestHandleHolder();

    public native MobileConfigOverridesTableHolder getNewOverridesTableHolder();

    public native MobileConfigOverridesTableHolder getNewOverridesTableHolderIfExists();

    public native String getSchemaString();

    public native boolean isConsistencyLoggingNeeded(int i);

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public native boolean isFetchNeeded();

    public native boolean isNetworkServiceSet();

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public native boolean isValid();

    public native void logConfigs(String str, int i, Map<String, String> map);

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public native void logExposure(String str, String str2, String str3);

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public native void logShadowResult(String str, String str2, String str3, String str4, String str5, String str6);

    public native void logStorageConsistency();

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public native boolean registerConfigChangeListener(MobileConfigCxxChangeListener mobileConfigCxxChangeListener);

    public native boolean saveCurrentParamsMapToDisk();

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public native boolean setEpHandler(MobileConfigEmergencyPushChangeListener mobileConfigEmergencyPushChangeListener);

    public native boolean setSandboxURL(String str);

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public native String syncFetchReason();

    public native boolean tryUpdateConfigs();

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public native boolean tryUpdateConfigsSynchronously(int i);

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public native boolean updateConfigs();

    public native boolean updateConfigsSynchronouslyWithDefaultUpdater(int i);

    public native boolean updateEmergencyPushConfigs();

    public native boolean updateEmergencyPushConfigsSynchronously(int i);

    static {
        NativeLoader.loadLibrary("mobileconfig-jni");
    }

    public MobileConfigManagerHolderImpl(HybridData hybridData) {
        this.mHybridData = hybridData;
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public MobileConfigMmapHandle getLatestHandle() {
        return getLatestHandleHolder();
    }

    @Override // com.facebook.mobileconfig.MobileConfigManagerHolder
    public MobileConfigOverridesTable getNewOverridesTableIfExists() {
        return getNewOverridesTableHolderIfExists();
    }

    public void setHasSessionId(boolean z) {
        this.mHasSessionId = z;
    }

    public void setDataDirPath(String str) {
        this.mDataDirPath = str;
    }
}
