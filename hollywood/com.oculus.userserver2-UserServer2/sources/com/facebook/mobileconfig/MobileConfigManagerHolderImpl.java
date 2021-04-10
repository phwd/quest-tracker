package com.facebook.mobileconfig;

import X.g6;
import android.content.res.AssetManager;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import java.util.Map;

public class MobileConfigManagerHolderImpl {
    @DoNotStrip
    public final HybridData mHybridData;

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

    public native boolean isFetchNeeded();

    public native boolean isNetworkServiceSet();

    public native boolean isValid();

    public native void logConfigs(String str, int i, Map<String, String> map);

    public native void logExposure(String str, String str2, String str3);

    public native void logShadowResult(String str, String str2, String str3, String str4, String str5, String str6);

    public native void logStorageConsistency();

    public native boolean registerConfigChangeListener(MobileConfigCxxChangeListener mobileConfigCxxChangeListener);

    public native boolean saveCurrentParamsMapToDisk();

    public native boolean setEpHandler(MobileConfigEmergencyPushChangeListener mobileConfigEmergencyPushChangeListener);

    public native boolean setSandboxURL(String str);

    public native String syncFetchReason();

    public native boolean tryUpdateConfigs();

    public native boolean tryUpdateConfigsSynchronously(int i);

    public native boolean updateConfigs();

    public native boolean updateConfigsSynchronouslyWithDefaultUpdater(int i);

    public native boolean updateEmergencyPushConfigs();

    public native boolean updateEmergencyPushConfigsSynchronously(int i);

    static {
        g6.A00();
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    public MobileConfigManagerHolderImpl(HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}
