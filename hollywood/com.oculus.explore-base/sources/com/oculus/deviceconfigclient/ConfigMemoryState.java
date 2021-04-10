package com.oculus.deviceconfigclient;

import android.annotation.TargetApi;
import java.util.HashMap;
import java.util.Map;

/* access modifiers changed from: package-private */
@TargetApi(25)
public class ConfigMemoryState {
    private final Map<String, ValueInfo<Boolean>> mBooleanValues = new HashMap();
    private final Map<String, ValueInfo<Double>> mDoubleValues = new HashMap();
    private final Map<String, ValueInfo<Long>> mLongValues = new HashMap();
    private String mParamsMapVersion = "##UNINITIALIZED_VERSION##";
    private final Map<String, ValueInfo<String>> mStringValues = new HashMap();

    ConfigMemoryState() {
    }

    public Map<String, ValueInfo<Boolean>> getBooleanValues() {
        return this.mBooleanValues;
    }

    public Map<String, ValueInfo<Double>> getDoubleValues() {
        return this.mDoubleValues;
    }

    public Map<String, ValueInfo<Long>> getLongValues() {
        return this.mLongValues;
    }

    public Map<String, ValueInfo<String>> getStringValues() {
        return this.mStringValues;
    }

    public String getParamsMapVersion() {
        return this.mParamsMapVersion;
    }

    public void setParamsMapVersion(String paramsMapVersion) {
        this.mParamsMapVersion = paramsMapVersion;
    }
}
