package com.oculus.deviceconfigclient;

import android.annotation.TargetApi;
import com.facebook.infer.annotation.Nullsafe;
import java.util.HashMap;
import java.util.Map;

@TargetApi(25)
@Nullsafe(Nullsafe.Mode.LOCAL)
public class ConfigMemoryState {
    public final Map<String, ValueInfo<Boolean>> mBooleanValues = new HashMap();
    public final Map<String, ValueInfo<Double>> mDoubleValues = new HashMap();
    public final Map<String, ValueInfo<Long>> mLongValues = new HashMap();
    public String mParamsMapVersion = "##UNINITIALIZED_VERSION##";
    public final Map<String, ValueInfo<String>> mStringValues = new HashMap();

    public final String A00() {
        return this.mParamsMapVersion;
    }

    public final Map<String, ValueInfo<Boolean>> A01() {
        return this.mBooleanValues;
    }

    public final Map<String, ValueInfo<Double>> A02() {
        return this.mDoubleValues;
    }

    public final Map<String, ValueInfo<Long>> A03() {
        return this.mLongValues;
    }

    public final Map<String, ValueInfo<String>> A04() {
        return this.mStringValues;
    }

    public final void A05(String str) {
        this.mParamsMapVersion = str;
    }
}
