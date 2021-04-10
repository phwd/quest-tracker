package com.facebook.mobileconfig;

import X.AbstractC0162Fh;
import X.AbstractC0163Fj;
import X.AbstractC0168Ft;
import X.KV;
import android.content.res.AssetManager;
import com.facebook.jni.HybridData;
import java.util.Map;

public class MobileConfigManagerHolderImpl implements AbstractC0162Fh {
    public String mDataDirPath;
    public boolean mHasSessionId;
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

    @Override // X.AbstractC0162Fh
    public native boolean isFetchNeeded();

    public native boolean isNetworkServiceSet();

    @Override // X.AbstractC0162Fh
    public native boolean isValid();

    public native void logConfigs(String str, int i, Map map);

    @Override // X.AbstractC0162Fh
    public native void logExposure(String str, String str2, String str3);

    @Override // X.AbstractC0162Fh
    public native void logShadowResult(String str, String str2, String str3, String str4, String str5, String str6);

    public native void logStorageConsistency();

    public native boolean registerConfigChangeListener(MobileConfigCxxChangeListener mobileConfigCxxChangeListener);

    public native boolean saveCurrentParamsMapToDisk();

    public native boolean setEpHandler(MobileConfigEmergencyPushChangeListener mobileConfigEmergencyPushChangeListener);

    public native boolean setSandboxURL(String str);

    @Override // X.AbstractC0162Fh
    public native String syncFetchReason();

    public native boolean tryUpdateConfigs();

    @Override // X.AbstractC0162Fh
    public native boolean tryUpdateConfigsSynchronously(int i);

    public native boolean updateConfigs();

    public native boolean updateConfigsSynchronouslyWithDefaultUpdater(int i);

    public native boolean updateEmergencyPushConfigs();

    public native boolean updateEmergencyPushConfigsSynchronously(int i);

    static {
        KV.A01("mobileconfig-jni");
    }

    public MobileConfigManagerHolderImpl(HybridData hybridData) {
        this.mHybridData = hybridData;
    }

    @Override // X.AbstractC0162Fh
    public AbstractC0163Fj getLatestHandle() {
        return getLatestHandleHolder();
    }

    @Override // X.AbstractC0162Fh
    public AbstractC0168Ft getNewOverridesTable() {
        return getNewOverridesTableHolder();
    }

    @Override // X.AbstractC0162Fh
    public AbstractC0168Ft getNewOverridesTableIfExists() {
        return getNewOverridesTableHolderIfExists();
    }
}
