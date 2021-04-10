package com.facebook.mobileconfig;

import X.AbstractC0168Ft;
import X.KV;
import com.facebook.jni.HybridData;

public class MobileConfigOverridesTableHolder implements AbstractC0168Ft {
    public final HybridData mHybridData;

    @Override // X.AbstractC0168Ft
    public native boolean boolOverrideForParam(long j, boolean z);

    public native double doubleOverrideForParam(long j, double d);

    public native String experimentOverrideForUniverse(String str);

    public native String groupOverrideForUniverse(String str);

    @Override // X.AbstractC0168Ft
    public native boolean hasBoolOverrideForParam(long j);

    public native boolean hasDoubleOverrideForParam(long j);

    @Override // X.AbstractC0168Ft
    public native boolean hasIntOverrideForParam(long j);

    public native boolean hasNullOverrideForParam(long j);

    public native boolean hasOverrideForUniverse(String str);

    @Override // X.AbstractC0168Ft
    public native boolean hasStringOverrideForParam(long j);

    @Override // X.AbstractC0168Ft
    public native long intOverrideForParam(long j, long j2);

    public native void removeAllOverrides();

    public native void removeOverrideForParam(long j);

    public native void removeOverridesForQEUniverse(String str);

    @Override // X.AbstractC0168Ft
    public native String stringOverrideForParam(long j, String str);

    public native void updateOverrideForBool(long j, boolean z);

    public native void updateOverrideForDouble(long j, double d);

    public native void updateOverrideForInt(long j, long j2);

    public native void updateOverrideForQE(String str, String str2, String str3);

    public native void updateOverrideForString(long j, String str);

    static {
        KV.A01("mobileconfig-jni");
    }

    public MobileConfigOverridesTableHolder(HybridData hybridData) {
        this.mHybridData = hybridData;
    }

    @Override // X.AbstractC0168Ft
    public void updateOverrideForParam(long j, boolean z) {
        updateOverrideForBool(j, z);
    }
}
