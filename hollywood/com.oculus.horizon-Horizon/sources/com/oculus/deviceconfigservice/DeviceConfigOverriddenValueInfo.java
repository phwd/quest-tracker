package com.oculus.deviceconfigservice;

import X.AnonymousClass006;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class DeviceConfigOverriddenValueInfo<T> {
    public static final String TAG = "DeviceConfigOverriddenValueInfo";
    public final String mConfigParamName;
    public final T mValue;
    public final DeviceConfigValueSource mValueSource;

    public final String toString() {
        return AnonymousClass006.A0B(TAG, " | config param name: ", this.mConfigParamName, ", value: ", String.valueOf(this.mValue), ", source: ", this.mValueSource.toString());
    }

    public DeviceConfigOverriddenValueInfo(String str, T t, DeviceConfigValueSource deviceConfigValueSource) {
        this.mConfigParamName = str;
        this.mValue = t;
        this.mValueSource = deviceConfigValueSource;
    }
}
