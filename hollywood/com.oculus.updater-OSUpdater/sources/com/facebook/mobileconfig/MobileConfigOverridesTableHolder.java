package com.facebook.mobileconfig;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.jni.HybridData;
import com.facebook.mobileconfig.factory.MobileConfigOverridesTable;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.soloader.nativeloader.NativeLoader;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class MobileConfigOverridesTableHolder implements MobileConfigOverridesTable {
    @DoNotStrip
    private final HybridData mHybridData;

    @Override // com.facebook.mobileconfig.factory.MobileConfigOverridesTable
    public native boolean boolOverrideForParam(long j, boolean z);

    @Override // com.facebook.mobileconfig.factory.MobileConfigOverridesTable
    public native double doubleOverrideForParam(long j, double d);

    public native String experimentOverrideForUniverse(String str);

    public native String groupOverrideForUniverse(String str);

    @Override // com.facebook.mobileconfig.factory.MobileConfigOverridesTable
    public native boolean hasBoolOverrideForParam(long j);

    @Override // com.facebook.mobileconfig.factory.MobileConfigOverridesTable
    public native boolean hasDoubleOverrideForParam(long j);

    @Override // com.facebook.mobileconfig.factory.MobileConfigOverridesTable
    public native boolean hasIntOverrideForParam(long j);

    public native boolean hasNullOverrideForParam(long j);

    public native boolean hasOverrideForUniverse(String str);

    @Override // com.facebook.mobileconfig.factory.MobileConfigOverridesTable
    public native boolean hasStringOverrideForParam(long j);

    @Override // com.facebook.mobileconfig.factory.MobileConfigOverridesTable
    public native long intOverrideForParam(long j, long j2);

    public native void removeAllOverrides();

    public native void removeOverrideForParam(long j);

    public native void removeOverridesForQEUniverse(String str);

    @Override // com.facebook.mobileconfig.factory.MobileConfigOverridesTable
    public native String stringOverrideForParam(long j, String str);

    /* access modifiers changed from: protected */
    public native void updateOverrideForBool(long j, boolean z);

    /* access modifiers changed from: protected */
    public native void updateOverrideForDouble(long j, double d);

    /* access modifiers changed from: protected */
    public native void updateOverrideForInt(long j, long j2);

    public native void updateOverrideForQE(String str, String str2, String str3);

    /* access modifiers changed from: protected */
    public native void updateOverrideForString(long j, String str);

    static {
        NativeLoader.loadLibrary("mobileconfig-jni");
    }

    private MobileConfigOverridesTableHolder(HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}
