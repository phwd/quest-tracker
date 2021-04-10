package com.oculus.deviceconfigclient;

import X.AnonymousClass006;
import android.annotation.TargetApi;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.deviceconfigclient.shared.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import org.apache.commons.cli.HelpFormatter;

@TargetApi(25)
@Nullsafe(Nullsafe.Mode.LOCAL)
public class ConfigMemoryState {
    public final Map<String, ValueInfo<Boolean>> mBooleanValues = new HashMap();
    public final Map<String, ValueInfo<Double>> mDoubleValues = new HashMap();
    public final Map<String, ValueInfo<Long>> mLongValues = new HashMap();
    public String mParamsMapVersion = "##UNINITIALIZED_VERSION##";
    public final Map<String, ValueInfo<String>> mStringValues = new HashMap();

    private <Type> void addMemorySnapshotPerType(ArrayList<String> arrayList, SnapshotQuery snapshotQuery, String str, Map<String, ValueInfo<Type>> map) {
        if (snapshotQuery.SummaryPerType) {
            StringBuilder sb = new StringBuilder();
            sb.append(map.size());
            sb.append(HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR);
            sb.append(str);
            arrayList.add(sb.toString());
        }
        StringBuilder sb2 = new StringBuilder();
        for (Map.Entry<String, ValueInfo<Type>> entry : map.entrySet()) {
            sb2.setLength(0);
            if (snapshotQuery.Type) {
                sb2.append(str);
                sb2.append(' ');
            }
            if (snapshotQuery.ParamName) {
                sb2.append(entry.getKey());
                sb2.append(':');
            }
            sb2.append(entry.getValue().debugOnlyMemorySnapshot(snapshotQuery));
            arrayList.add(sb2.toString());
        }
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
            hashMap.put(entry.getKey(), entry.getValue().mValueToReturn);
        }
        for (Map.Entry<String, ValueInfo<Double>> entry2 : this.mDoubleValues.entrySet()) {
            hashMap.put(entry2.getKey(), entry2.getValue().mValueToReturn);
        }
        for (Map.Entry<String, ValueInfo<Long>> entry3 : this.mLongValues.entrySet()) {
            hashMap.put(entry3.getKey(), entry3.getValue().mValueToReturn);
        }
        for (Map.Entry<String, ValueInfo<String>> entry4 : this.mStringValues.entrySet()) {
            hashMap.put(entry4.getKey(), entry4.getValue().mValueToReturn);
        }
        return hashMap;
    }

    public void debugOnlySetBooleanOverriddenValue(String str, @Nullable Object obj) {
        ValueInfo<Boolean> valueInfo = this.mBooleanValues.get(str);
        if (valueInfo != null) {
            if (obj == null) {
                obj = (Type) null;
            }
            valueInfo.mValueToOverride = (Type) obj;
        }
    }

    public void debugOnlySetDoubleOverriddenValue(String str, @Nullable Object obj) {
        ValueInfo<Double> valueInfo = this.mDoubleValues.get(str);
        if (valueInfo != null) {
            if (obj == null) {
                obj = (Type) null;
            }
            valueInfo.mValueToOverride = (Type) obj;
        }
    }

    public void debugOnlySetLongOverriddenValue(String str, @Nullable Object obj) {
        ValueInfo<Long> valueInfo = this.mLongValues.get(str);
        if (valueInfo != null) {
            if (obj == null) {
                obj = (Type) null;
            }
            valueInfo.mValueToOverride = (Type) obj;
        }
    }

    public void debugOnlySetStringOverriddenValue(String str, @Nullable Object obj) {
        ValueInfo<String> valueInfo = this.mStringValues.get(str);
        if (valueInfo != null) {
            if (obj == null) {
                obj = (Type) null;
            }
            valueInfo.mValueToOverride = (Type) obj;
        }
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

    public String getParamsMapVersion() {
        return this.mParamsMapVersion;
    }

    public Map<String, ValueInfo<String>> getStringValues() {
        return this.mStringValues;
    }

    public void setParamsMapVersion(String str) {
        this.mParamsMapVersion = str;
    }

    @Deprecated
    public String debugOnlyGetMemorySnapshot(String str) {
        SnapshotQuery createWithAllEnabled = SnapshotQuery.createWithAllEnabled();
        createWithAllEnabled.SummaryPerType = false;
        createWithAllEnabled.Type = false;
        createWithAllEnabled.DebugText = str;
        return String.join(System.lineSeparator(), debugOnlyGetMemorySnapshot(createWithAllEnabled));
    }

    public List<String> debugOnlyGetMemorySnapshot(SnapshotQuery snapshotQuery) {
        ArrayList<String> arrayList = new ArrayList<>();
        if (snapshotQuery.Header) {
            String str = snapshotQuery.DebugText;
            if (AnonymousClass006.A07("Start MemorySnapshot: ", str) == null) {
                str = "";
            }
            arrayList.add(str);
        }
        if (snapshotQuery.ParamsMapVersion) {
            arrayList.add(AnonymousClass006.A07("Version: ", this.mParamsMapVersion));
        }
        addMemorySnapshotPerType(arrayList, snapshotQuery, Constants.BOOLEAN_TYPE_JSON_VALUE, this.mBooleanValues);
        addMemorySnapshotPerType(arrayList, snapshotQuery, Constants.DOUBLE_TYPE_JSON_VALUE, this.mDoubleValues);
        addMemorySnapshotPerType(arrayList, snapshotQuery, Constants.LONG_TYPE_JSON_VALUE, this.mLongValues);
        addMemorySnapshotPerType(arrayList, snapshotQuery, Constants.STRING_TYPE_JSON_VALUE, this.mStringValues);
        if (snapshotQuery.Footer) {
            arrayList.add("End MemorySnapshot");
        }
        return arrayList;
    }
}
