package com.oculus.deviceconfigservice;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public enum DeviceConfigSharedPreferencesNames {
    OVERRIDDEN_VALUE("overridden_value_pref"),
    VALUE_SOURCE("value_source_pref");
    
    public final String mKey;

    /* access modifiers changed from: public */
    DeviceConfigSharedPreferencesNames(String str) {
        this.mKey = str;
    }

    public String toString() {
        return this.mKey;
    }
}
