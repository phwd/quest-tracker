package com.facebook.mobileconfig;

import X.AnonymousClass0RO;
import X.AnonymousClass0RX;
import X.AnonymousClass0RZ;
import X.AnonymousClass0Ri;
import X.C03250cX;
import android.content.res.AssetManager;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import java.util.Map;

public class MobileConfigManagerHolderImpl implements AnonymousClass0RX {
    public String mDataDirPath;
    public boolean mHasSessionId;
    @DoNotStrip
    public final HybridData mHybridData;

    public static native String getParamMapContentFromAsset(AssetManager assetManager);

    public native void clearCurrentUserData();

    public native void clearEmergencyPushChannel();

    public native void clearOverrides();

    @Override // X.AnonymousClass0RX
    public native void deleteOldUserData(int i);

    public native String getConsistencyLoggingFlagsJSON();

    public native String getFrameworkStatus();

    public native MobileConfigMmapHandleHolder getLatestHandleHolder();

    public native MobileConfigOverridesTableHolder getNewOverridesTableHolder();

    public native MobileConfigOverridesTableHolder getNewOverridesTableHolderIfExists();

    public native String getSchemaString();

    public native boolean isConsistencyLoggingNeeded(int i);

    @Override // X.AnonymousClass0RX
    public native boolean isFetchNeeded();

    public native boolean isNetworkServiceSet();

    @Override // X.AnonymousClass0RX
    public native boolean isValid();

    public native void logConfigs(String str, int i, Map<String, String> map);

    @Override // X.AnonymousClass0RX
    public native void logExposure(String str, String str2, String str3);

    @Override // X.AnonymousClass0RX
    public native void logShadowResult(String str, String str2, String str3, String str4, String str5, String str6);

    public native void logStorageConsistency();

    @Override // X.AnonymousClass0RX
    public native boolean registerConfigChangeListener(MobileConfigCxxChangeListener mobileConfigCxxChangeListener);

    @Override // X.AnonymousClass0RX
    public native boolean saveCurrentParamsMapToDisk();

    public native boolean setEpHandler(MobileConfigEmergencyPushChangeListener mobileConfigEmergencyPushChangeListener);

    public native boolean setSandboxURL(String str);

    @Override // X.AnonymousClass0RX
    public native String syncFetchReason();

    public native boolean tryUpdateConfigs();

    @Override // X.AnonymousClass0RX
    public native boolean tryUpdateConfigsSynchronously(int i);

    @Override // X.AnonymousClass0RX
    public native boolean updateConfigs();

    @Override // X.AnonymousClass0RX
    public native boolean updateConfigsSynchronouslyWithDefaultUpdater(int i);

    public native boolean updateEmergencyPushConfigs();

    public native boolean updateEmergencyPushConfigsSynchronously(int i);

    static {
        C03250cX.A01("mobileconfig-jni");
    }

    public MobileConfigManagerHolderImpl(HybridData hybridData) {
        this.mHybridData = hybridData;
    }

    @Override // X.AnonymousClass0RX
    public AnonymousClass0RZ getLatestHandle() {
        return getLatestHandleHolder();
    }

    @Override // X.AnonymousClass0RX
    public AnonymousClass0Ri getNewOverridesTableIfExists() {
        return getNewOverridesTableHolderIfExists();
    }

    @Override // X.AnonymousClass0RX
    public boolean isConsistencyLoggingNeeded(AnonymousClass0RO r2) {
        return isConsistencyLoggingNeeded(r2.getValue());
    }

    @Override // X.AnonymousClass0RX
    public void logConfigs(String str, AnonymousClass0RO r3, Map<String, String> map) {
        logConfigs(str, r3.getValue(), map);
    }
}
