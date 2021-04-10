package com.facebook.mobileconfig.troubleshooting;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.soloader.SoLoader;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class BisectStateHolder implements BisectState {
    @DoNotStrip
    private final HybridData mHybridData;

    @Override // com.facebook.mobileconfig.troubleshooting.BisectState
    public native boolean canContinue();

    @Override // com.facebook.mobileconfig.troubleshooting.BisectState
    public native String getCulprit();

    @Override // com.facebook.mobileconfig.troubleshooting.BisectState
    public native int getLeft();

    @Override // com.facebook.mobileconfig.troubleshooting.BisectState
    public native int getMiddle();

    @Override // com.facebook.mobileconfig.troubleshooting.BisectState
    public native int getNumberOfStepsMade();

    @Override // com.facebook.mobileconfig.troubleshooting.BisectState
    public native int getNumberOfStepsRemaining();

    @Override // com.facebook.mobileconfig.troubleshooting.BisectState
    public native int getRight();

    @Override // com.facebook.mobileconfig.troubleshooting.BisectState
    public native int getSize();

    @Override // com.facebook.mobileconfig.troubleshooting.BisectState
    public native String getTaskNumber();

    @Override // com.facebook.mobileconfig.troubleshooting.BisectState
    public native String getUniqueId();

    @Override // com.facebook.mobileconfig.troubleshooting.BisectState
    public native boolean isRunning();

    static {
        SoLoader.loadLibrary("mobileconfigtroubleshooting-jni");
    }

    private BisectStateHolder(HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}
