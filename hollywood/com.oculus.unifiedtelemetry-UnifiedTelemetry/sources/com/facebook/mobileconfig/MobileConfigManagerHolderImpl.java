package com.facebook.mobileconfig;

import X.C0431hn;
import X.RU;
import X.RW;
import X.Re;
import android.content.res.AssetManager;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import java.util.Map;

public class MobileConfigManagerHolderImpl implements RU {
    public String mDataDirPath;
    public boolean mHasSessionId;
    @DoNotStrip
    public final HybridData mHybridData;

    public static native String getParamMapContentFromAsset(AssetManager assetManager);

    public native void clearCurrentUserData();

    public native void clearEmergencyPushChannel();

    public native void clearOverrides();

    @Override // X.RU
    public native void deleteOldUserData(int i);

    public native String getConsistencyLoggingFlagsJSON();

    public native String getFrameworkStatus();

    public native MobileConfigMmapHandleHolder getLatestHandleHolder();

    public native MobileConfigOverridesTableHolder getNewOverridesTableHolder();

    public native MobileConfigOverridesTableHolder getNewOverridesTableHolderIfExists();

    public native String getSchemaString();

    public native boolean isConsistencyLoggingNeeded(int i);

    @Override // X.RU
    public native boolean isFetchNeeded();

    public native boolean isNetworkServiceSet();

    @Override // X.RU
    public native boolean isValid();

    public native void logConfigs(String str, int i, Map<String, String> map);

    @Override // X.RU
    public native void logExposure(String str, String str2, String str3);

    @Override // X.RU
    public native void logShadowResult(String str, String str2, String str3, String str4, String str5, String str6);

    public native void logStorageConsistency();

    public native boolean registerConfigChangeListener(MobileConfigCxxChangeListener mobileConfigCxxChangeListener);

    public native boolean saveCurrentParamsMapToDisk();

    public native boolean setEpHandler(MobileConfigEmergencyPushChangeListener mobileConfigEmergencyPushChangeListener);

    public native boolean setSandboxURL(String str);

    @Override // X.RU
    public native String syncFetchReason();

    public native boolean tryUpdateConfigs();

    @Override // X.RU
    public native boolean tryUpdateConfigsSynchronously(int i);

    @Override // X.RU
    public native boolean updateConfigs();

    public native boolean updateConfigsSynchronouslyWithDefaultUpdater(int i);

    public native boolean updateEmergencyPushConfigs();

    public native boolean updateEmergencyPushConfigsSynchronously(int i);

    static {
        C0431hn.A00("mobileconfig-jni");
    }

    public MobileConfigManagerHolderImpl(HybridData hybridData) {
        this.mHybridData = hybridData;
    }

    @Override // X.RU
    public RW getLatestHandle() {
        return getLatestHandleHolder();
    }

    @Override // X.RU
    public Re getNewOverridesTableIfExists() {
        return getNewOverridesTableHolderIfExists();
    }
}
