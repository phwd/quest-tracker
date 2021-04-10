package com.facebook.mobileconfig.troubleshooting;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.soloader.SoLoader;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class MobileConfigQEInfoQueryParamsHolder {
    @DoNotStrip
    private final HybridData mHybridData = initHybrid();

    private static native HybridData initHybrid();

    public native void setExperimentName(String str);

    public native void setGatekeeperName(String str);

    public native void setSkipQE(boolean z);

    public native void setUniverseRegex(String str);

    public native void withCurrentExperimentGroup(boolean z);

    public native void withExperiments(boolean z);

    public native void withGatekeepers(boolean z);

    public native void withGroupParams(boolean z);

    public native void withGroups(boolean z);

    public native void withParams(boolean z);

    public native void withUniverseParams(boolean z);

    static {
        SoLoader.loadLibrary("mobileconfigtroubleshooting-jni");
    }
}
