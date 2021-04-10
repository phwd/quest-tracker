package com.facebook.mobileconfig;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.soloader.nativeloader.NativeLoader;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class MobileConfigManagerParamsHolder {
    public static final String SERVICE_MANAGER_NAME = "service_manager";
    @DoNotStrip
    private final HybridData mHybridData = initHybrid();

    private static native HybridData initHybrid();

    public native void setBufferPathPostfix(String str);

    public native void setConsistencyLoggingEnabled(boolean z);

    public native void setConsistencyLoggingEveryNSec(long j);

    public native void setEnableBlnSchema(boolean z);

    public native void setManagerName(String str);

    public native void setOTAAddedParamsPath(String str);

    public native void setOTAOneQueryHashPath(String str);

    public native void setOTAParamsMapPath(String str);

    public native void setQueryHashOptimization(boolean z);

    public native void setResponseCompressionEnabled(boolean z);

    public native void setShouldParseAdditionalParamsMaps(boolean z);

    public native void setShouldUseOTAAddedParamsResource(boolean z);

    public native void setShouldUseOTAResource(boolean z);

    public native boolean setUniverseType(int i);

    public native void setUsePartialAndFullSyncFetch(boolean z);

    public native void setUsePlatformEpHandler(boolean z);

    static {
        NativeLoader.loadLibrary("mobileconfig-jni");
    }
}
