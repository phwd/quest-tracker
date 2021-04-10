package com.facebook.mobileconfig.troubleshooting;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.jni.HybridData;
import com.facebook.mobileconfig.MobileConfigManagerHolderImpl;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.soloader.SoLoader;
import java.util.Set;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class MobileConfigValueExplainerHolder {
    @DoNotStrip
    private final HybridData mHybridData;

    private static native HybridData initHybrid(MobileConfigManagerHolderImpl mobileConfigManagerHolderImpl);

    public native String getClientDrivenInfo(String str, Set<String> set, boolean z);

    static {
        SoLoader.loadLibrary("mobileconfigtroubleshooting-jni");
    }

    public MobileConfigValueExplainerHolder(MobileConfigManagerHolderImpl mobileConfigManagerHolderImpl) {
        this.mHybridData = initHybrid(mobileConfigManagerHolderImpl);
    }
}
