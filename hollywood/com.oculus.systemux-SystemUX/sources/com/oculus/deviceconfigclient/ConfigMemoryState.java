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

    public void setParamsMapVersion(String str) {
        this.mParamsMapVersion = str;
    }

    public void clear() {
        this.mBooleanValues.clear();
        this.mDoubleValues.clear();
        this.mLongValues.clear();
        this.mStringValues.clear();
    }

    public Map<String, Object> debugOnlyGetValues() {
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, ValueInfo<Boolean>> entry : this.mBooleanValues.entrySet()) {
            hashMap.put(entry.getKey(), entry.getValue().debugOnlyGetValue());
        }
        for (Map.Entry<String, ValueInfo<Double>> entry2 : this.mDoubleValues.entrySet()) {
            hashMap.put(entry2.getKey(), entry2.getValue().debugOnlyGetValue());
        }
        for (Map.Entry<String, ValueInfo<Long>> entry3 : this.mLongValues.entrySet()) {
            hashMap.put(entry3.getKey(), entry3.getValue().debugOnlyGetValue());
        }
        for (Map.Entry<String, ValueInfo<String>> entry4 : this.mStringValues.entrySet()) {
            hashMap.put(entry4.getKey(), entry4.getValue().debugOnlyGetValue());
        }
        return hashMap;
    }

    public void debugOnlySetBooleanOverriddenValue(String str, @Nullable Object obj) {
        ValueInfo<Boolean> valueInfo = this.mBooleanValues.get(str);
        if (valueInfo != null) {
            valueInfo.setValueToOverride(obj != null ? (Boolean) obj : null);
        }
    }

    public void debugOnlySetLongOverriddenValue(String str, @Nullable Object obj) {
        ValueInfo<Long> valueInfo = this.mLongValues.get(str);
        if (valueInfo != null) {
            valueInfo.setValueToOverride(obj != null ? (Long) obj : null);
        }
    }

    public void debugOnlySetDoubleOverriddenValue(String str, @Nullable Object obj) {
        ValueInfo<Double> valueInfo = this.mDoubleValues.get(str);
        if (valueInfo != null) {
            valueInfo.setValueToOverride(obj != null ? (Double) obj : null);
        }
    }

    public void debugOnlySetStringOverriddenValue(String str, @Nullable Object obj) {
        ValueInfo<String> valueInfo = this.mStringValues.get(str);
        if (valueInfo != null) {
            valueInfo.setValueToOverride(obj != null ? (String) obj : null);
        }
    }

    public String debugOnlyGetMemorySnapshot(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("Start MemorySnapshot: ");
        sb.append(str);
        sb.append(System.lineSeparator());
        sb.append("Version: ");
        sb.append(this.mParamsMapVersion);
        sb.append(System.lineSeparator());
        for (Map.Entry<String, ValueInfo<Boolean>> entry : this.mBooleanValues.entrySet()) {
            sb.append(entry.getKey());
            sb.append(": ");
            sb.append(entry.getValue().debugOnlyMemorySnapshot());
            sb.append(System.lineSeparator());
        }
        for (Map.Entry<String, ValueInfo<Double>> entry2 : this.mDoubleValues.entrySet()) {
            sb.append(entry2.getKey());
            sb.append(": ");
            sb.append(entry2.getValue().debugOnlyMemorySnapshot());
            sb.append(System.lineSeparator());
        }
        for (Map.Entry<String, ValueInfo<Long>> entry3 : this.mLongValues.entrySet()) {
            sb.append(entry3.getKey());
            sb.append(": ");
            sb.append(entry3.getValue().debugOnlyMemorySnapshot());
            sb.append(System.lineSeparator());
        }
        for (Map.Entry<String, ValueInfo<String>> entry4 : this.mStringValues.entrySet()) {
            sb.append(entry4.getKey());
            sb.append(": ");
            sb.append(entry4.getValue().debugOnlyMemorySnapshot());
            sb.append(System.lineSeparator());
        }
        sb.append("End MemorySnapshot");
        sb.append(System.lineSeparator());
        return sb.toString();
    }
}
