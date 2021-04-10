package com.oculus.deviceconfigclient;

import android.annotation.TargetApi;
import com.oculus.deviceconfigclient.shared.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public void debugOnlySetBooleanOverriddenValue(String paramName, Object overriddenValue) {
        ValueInfo<Boolean> booleanValueInfo = this.mBooleanValues.get(paramName);
        if (booleanValueInfo != null) {
            booleanValueInfo.setValueToOverride(overriddenValue != null ? (Boolean) overriddenValue : null);
        }
    }

    public void debugOnlySetLongOverriddenValue(String paramName, Object overriddenValue) {
        ValueInfo<Long> longValueInfo = this.mLongValues.get(paramName);
        if (longValueInfo != null) {
            longValueInfo.setValueToOverride(overriddenValue != null ? (Long) overriddenValue : null);
        }
    }

    public void debugOnlySetDoubleOverriddenValue(String paramName, Object overriddenValue) {
        ValueInfo<Double> doubleValueInfo = this.mDoubleValues.get(paramName);
        if (doubleValueInfo != null) {
            doubleValueInfo.setValueToOverride(overriddenValue != null ? (Double) overriddenValue : null);
        }
    }

    public void debugOnlySetStringOverriddenValue(String paramName, Object overriddenValue) {
        ValueInfo<String> stringValueInfo = this.mStringValues.get(paramName);
        if (stringValueInfo != null) {
            stringValueInfo.setValueToOverride(overriddenValue != null ? (String) overriddenValue : null);
        }
    }

    @Deprecated
    public String debugOnlyGetMemorySnapshot(String debugText) {
        SnapshotQuery query = SnapshotQuery.createWithAllEnabled();
        query.SummaryPerType = false;
        query.Type = false;
        query.DebugText = debugText;
        return String.join(System.lineSeparator(), debugOnlyGetMemorySnapshot(query));
    }

    public List<String> debugOnlyGetMemorySnapshot(SnapshotQuery query) {
        ArrayList<String> results = new ArrayList<>();
        if (query.Header) {
            results.add(new StringBuilder().append("Start MemorySnapshot: ").append(query.DebugText).toString() != null ? query.DebugText : "");
        }
        if (query.ParamsMapVersion) {
            results.add("Version: " + this.mParamsMapVersion);
        }
        addMemorySnapshotPerType(results, query, Constants.BOOLEAN_TYPE_JSON_VALUE, this.mBooleanValues);
        addMemorySnapshotPerType(results, query, Constants.DOUBLE_TYPE_JSON_VALUE, this.mDoubleValues);
        addMemorySnapshotPerType(results, query, Constants.LONG_TYPE_JSON_VALUE, this.mLongValues);
        addMemorySnapshotPerType(results, query, Constants.STRING_TYPE_JSON_VALUE, this.mStringValues);
        if (query.Footer) {
            results.add("End MemorySnapshot");
        }
        return results;
    }

    private <Type> void addMemorySnapshotPerType(ArrayList<String> results, SnapshotQuery query, String type, Map<String, ValueInfo<Type>> values) {
        if (query.SummaryPerType) {
            results.add(values.size() + " " + type);
        }
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, ValueInfo<Type>> valueInfo : values.entrySet()) {
            builder.setLength(0);
            if (query.Type) {
                builder.append(type).append(' ');
            }
            if (query.ParamName) {
                builder.append(valueInfo.getKey()).append(':');
            }
            builder.append(valueInfo.getValue().debugOnlyMemorySnapshot(query));
            results.add(builder.toString());
        }
    }
}
