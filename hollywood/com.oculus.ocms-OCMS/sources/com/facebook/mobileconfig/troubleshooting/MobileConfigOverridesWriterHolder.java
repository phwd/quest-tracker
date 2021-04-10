package com.facebook.mobileconfig.troubleshooting;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.jni.HybridData;
import com.facebook.mobileconfig.MobileConfigManagerHolderImpl;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.soloader.SoLoader;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class MobileConfigOverridesWriterHolder {
    @DoNotStrip
    private final HybridData mHybridData;

    private static native HybridData initHybrid(MobileConfigManagerHolderImpl mobileConfigManagerHolderImpl);

    public native BisectHelperHolder getNewMCBisectHelper(BisectDefaultValuesProvider bisectDefaultValuesProvider);

    public native String importOverridesFromTask(String str);

    public native String importOverridesFromUser(String str);

    public native String loadOverridesFromTaskAndSaveResponse(String str);

    static {
        SoLoader.loadLibrary("mobileconfigtroubleshooting-jni");
    }

    public MobileConfigOverridesWriterHolder(MobileConfigManagerHolderImpl mobileConfigManagerHolderImpl) {
        this.mHybridData = initHybrid(mobileConfigManagerHolderImpl);
    }
}
