package com.facebook.mobileconfig;

import X.C0431hn;
import X.Re;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class MobileConfigOverridesTableHolder implements Re {
    @DoNotStrip
    public final HybridData mHybridData;

    @Override // X.Re
    public native boolean boolOverrideForParam(long j, boolean z);

    public native double doubleOverrideForParam(long j, double d);

    public native String experimentOverrideForUniverse(String str);

    public native String groupOverrideForUniverse(String str);

    @Override // X.Re
    public native boolean hasBoolOverrideForParam(long j);

    public native boolean hasDoubleOverrideForParam(long j);

    @Override // X.Re
    public native boolean hasIntOverrideForParam(long j);

    public native boolean hasNullOverrideForParam(long j);

    public native boolean hasOverrideForUniverse(String str);

    @Override // X.Re
    public native boolean hasStringOverrideForParam(long j);

    @Override // X.Re
    public native long intOverrideForParam(long j, long j2);

    public native void removeAllOverrides();

    public native void removeOverrideForParam(long j);

    public native void removeOverridesForQEUniverse(String str);

    @Override // X.Re
    public native String stringOverrideForParam(long j, String str);

    public native void updateOverrideForBool(long j, boolean z);

    public native void updateOverrideForDouble(long j, double d);

    public native void updateOverrideForInt(long j, long j2);

    public native void updateOverrideForQE(String str, String str2, String str3);

    public native void updateOverrideForString(long j, String str);

    static {
        C0431hn.A00("mobileconfig-jni");
    }

    public MobileConfigOverridesTableHolder(HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}
