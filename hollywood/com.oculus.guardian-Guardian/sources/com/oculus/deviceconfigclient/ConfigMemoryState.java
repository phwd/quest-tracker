package com.oculus.deviceconfigclient;

import android.annotation.TargetApi;
import com.facebook.infer.annotation.Nullsafe;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

/* access modifiers changed from: package-private */
@TargetApi(25)
@Nullsafe(Nullsafe.Mode.LOCAL)
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

    public void clear() {
        this.mBooleanValues.clear();
        this.mDoubleValues.clear();
        this.mLongValues.clear();
        this.mStringValues.clear();
    }

    public Map<String, Object> debugOnlyGetValues() {
        Map<String, Object> values = new HashMap<>();
        for (Map.Entry<String, ValueInfo<Boolean>> valueInfo : this.mBooleanValues.entrySet()) {
            values.put(valueInfo.getKey(), valueInfo.getValue().debugOnlyGetValue());
        }
        for (Map.Entry<String, ValueInfo<Double>> valueInfo2 : this.mDoubleValues.entrySet()) {
            values.put(valueInfo2.getKey(), valueInfo2.getValue().debugOnlyGetValue());
        }
        for (Map.Entry<String, ValueInfo<Long>> valueInfo3 : this.mLongValues.entrySet()) {
            values.put(valueInfo3.getKey(), valueInfo3.getValue().debugOnlyGetValue());
        }
        for (Map.Entry<String, ValueInfo<String>> valueInfo4 : this.mStringValues.entrySet()) {
            values.put(valueInfo4.getKey(), valueInfo4.getValue().debugOnlyGetValue());
        }
        return values;
    }

    public void debugOnlySetBooleanOverriddenValue(String paramName, @Nullable Object overriddenValue) {
        ValueInfo<Boolean> booleanValueInfo = this.mBooleanValues.get(paramName);
        if (booleanValueInfo != null) {
            booleanValueInfo.setValueToOverride(overriddenValue != null ? (Boolean) overriddenValue : null);
        }
    }

    public void debugOnlySetLongOverriddenValue(String paramName, @Nullable Object overriddenValue) {
        ValueInfo<Long> longValueInfo = this.mLongValues.get(paramName);
        if (longValueInfo != null) {
            longValueInfo.setValueToOverride(overriddenValue != null ? (Long) overriddenValue : null);
        }
    }

    public void debugOnlySetDoubleOverriddenValue(String paramName, @Nullable Object overriddenValue) {
        ValueInfo<Double> doubleValueInfo = this.mDoubleValues.get(paramName);
        if (doubleValueInfo != null) {
            doubleValueInfo.setValueToOverride(overriddenValue != null ? (Double) overriddenValue : null);
        }
    }

    public void debugOnlySetStringOverriddenValue(String paramName, @Nullable Object overriddenValue) {
        ValueInfo<String> stringValueInfo = this.mStringValues.get(paramName);
        if (stringValueInfo != null) {
            stringValueInfo.setValueToOverride(overriddenValue != null ? (String) overriddenValue : null);
        }
    }

    public String debugOnlyGetMemorySnapshot(String debugText) {
        StringBuilder builder = new StringBuilder();
        builder.append("Start MemorySnapshot: ");
        builder.append(debugText);
        builder.append(System.lineSeparator());
        builder.append("Version: ");
        builder.append(this.mParamsMapVersion);
        builder.append(System.lineSeparator());
        for (Map.Entry<String, ValueInfo<Boolean>> valueInfo : this.mBooleanValues.entrySet()) {
            builder.append(valueInfo.getKey());
            builder.append(": ");
            builder.append(valueInfo.getValue().debugOnlyMemorySnapshot());
            builder.append(System.lineSeparator());
        }
        for (Map.Entry<String, ValueInfo<Double>> valueInfo2 : this.mDoubleValues.entrySet()) {
            builder.append(valueInfo2.getKey());
            builder.append(": ");
            builder.append(valueInfo2.getValue().debugOnlyMemorySnapshot());
            builder.append(System.lineSeparator());
        }
        for (Map.Entry<String, ValueInfo<Long>> valueInfo3 : this.mLongValues.entrySet()) {
            builder.append(valueInfo3.getKey());
            builder.append(": ");
            builder.append(valueInfo3.getValue().debugOnlyMemorySnapshot());
            builder.append(System.lineSeparator());
        }
        for (Map.Entry<String, ValueInfo<String>> valueInfo4 : this.mStringValues.entrySet()) {
            builder.append(valueInfo4.getKey());
            builder.append(": ");
            builder.append(valueInfo4.getValue().debugOnlyMemorySnapshot());
            builder.append(System.lineSeparator());
        }
        builder.append("End MemorySnapshot");
        builder.append(System.lineSeparator());
        return builder.toString();
    }
}
