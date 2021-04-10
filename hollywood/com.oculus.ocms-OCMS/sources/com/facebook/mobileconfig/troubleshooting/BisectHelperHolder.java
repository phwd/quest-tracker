package com.facebook.mobileconfig.troubleshooting;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.soloader.SoLoader;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class BisectHelperHolder implements BisectHelper {
    @DoNotStrip
    private final HybridData mHybridData;

    @Override // com.facebook.mobileconfig.troubleshooting.BisectHelper
    public native BisectStateHolder getCurrentState();

    @Override // com.facebook.mobileconfig.troubleshooting.BisectHelper
    public native void startBisection(String str, BisectCallback bisectCallback);

    @Override // com.facebook.mobileconfig.troubleshooting.BisectHelper
    public native boolean stopBisection();

    @Override // com.facebook.mobileconfig.troubleshooting.BisectHelper
    public native boolean userDidNotReproduceBug();

    @Override // com.facebook.mobileconfig.troubleshooting.BisectHelper
    public native boolean userDidReproduceBug();

    static {
        SoLoader.loadLibrary("mobileconfigtroubleshooting-jni");
    }

    private BisectHelperHolder(HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}
