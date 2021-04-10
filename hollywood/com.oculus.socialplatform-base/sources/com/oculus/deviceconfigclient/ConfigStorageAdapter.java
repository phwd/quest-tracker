package com.oculus.deviceconfigclient;

import X.AnonymousClass0MD;
import android.content.Context;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.deviceconfigclient.ValueInfo;
import com.oculus.deviceconfigclient.shared.Constants;
import com.oculus.deviceconfigclient.shared.StorageInternalRepresentation;
import com.oculus.deviceconfigclient.shared.StorageParam;
import java.util.Map;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ConfigStorageAdapter {
    public static final String TAG = "ConfigStorageAdapter";

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public static ConfigMemoryState createMemoryStateFromStorageCache(Context context) {
        ConfigMemoryState configMemoryState = new ConfigMemoryState();
        StorageInternalRepresentation readRepresentationFromStorageCache = ConfigStorageCache.readRepresentationFromStorageCache(context);
        String str = readRepresentationFromStorageCache.ParamsMapVersion;
        if (str != null) {
            configMemoryState.mParamsMapVersion = str;
        }
        for (StorageParam storageParam : readRepresentationFromStorageCache.Params) {
            String str2 = storageParam.mName;
            String str3 = storageParam.mType;
            String str4 = storageParam.mValue;
            String str5 = storageParam.mLoggingId;
            boolean z = storageParam.mIsSessionless;
            try {
                switch (str3.hashCode()) {
                    case -1325958191:
                        if (str3.equals(Constants.DOUBLE_TYPE_JSON_VALUE)) {
                            ValueInfo<Double> valueInfo = new ValueInfo<>(Double.valueOf(Double.parseDouble(str4)), ValueInfo.ValueSetFlags.FromCache);
                            valueInfo.setLoggingId(str5, z);
                            configMemoryState.mDoubleValues.put(str2, valueInfo);
                            break;
                        }
                        AnonymousClass0MD.A09(TAG, "incorrect type '%s' in cached Config Param '%s' with value '%s'", str3, str2, str4);
                        break;
                    case -891985903:
                        if (str3.equals(Constants.STRING_TYPE_JSON_VALUE)) {
                            ValueInfo<String> valueInfo2 = new ValueInfo<>(str4, ValueInfo.ValueSetFlags.FromCache);
                            valueInfo2.setLoggingId(str5, z);
                            configMemoryState.mStringValues.put(str2, valueInfo2);
                            break;
                        }
                        AnonymousClass0MD.A09(TAG, "incorrect type '%s' in cached Config Param '%s' with value '%s'", str3, str2, str4);
                        break;
                    case 3327612:
                        if (str3.equals(Constants.LONG_TYPE_JSON_VALUE)) {
                            ValueInfo<Long> valueInfo3 = new ValueInfo<>(Long.valueOf(Long.parseLong(str4)), ValueInfo.ValueSetFlags.FromCache);
                            valueInfo3.setLoggingId(str5, z);
                            configMemoryState.mLongValues.put(str2, valueInfo3);
                            break;
                        }
                        AnonymousClass0MD.A09(TAG, "incorrect type '%s' in cached Config Param '%s' with value '%s'", str3, str2, str4);
                        break;
                    case 64711720:
                        if (str3.equals(Constants.BOOLEAN_TYPE_JSON_VALUE)) {
                            ValueInfo<Boolean> valueInfo4 = new ValueInfo<>(Boolean.valueOf(Boolean.parseBoolean(str4)), ValueInfo.ValueSetFlags.FromCache);
                            valueInfo4.setLoggingId(str5, z);
                            configMemoryState.mBooleanValues.put(str2, valueInfo4);
                            break;
                        }
                        AnonymousClass0MD.A09(TAG, "incorrect type '%s' in cached Config Param '%s' with value '%s'", str3, str2, str4);
                        break;
                    default:
                        AnonymousClass0MD.A09(TAG, "incorrect type '%s' in cached Config Param '%s' with value '%s'", str3, str2, str4);
                        break;
                }
            } catch (Exception e) {
                AnonymousClass0MD.A0E(TAG, e, "Could not convert value '%s' to type '%s' in cached Config Param '%s'", str4, str3, str2);
            }
        }
        return configMemoryState;
    }

    public static void writeMemoryStateToStorageCache(Context context, ConfigMemoryState configMemoryState) {
        StorageInternalRepresentation storageInternalRepresentation = new StorageInternalRepresentation();
        storageInternalRepresentation.ParamsMapVersion = configMemoryState.mParamsMapVersion;
        writeValues(storageInternalRepresentation, Constants.BOOLEAN_TYPE_JSON_VALUE, configMemoryState.mBooleanValues);
        writeValues(storageInternalRepresentation, Constants.DOUBLE_TYPE_JSON_VALUE, configMemoryState.mDoubleValues);
        writeValues(storageInternalRepresentation, Constants.LONG_TYPE_JSON_VALUE, configMemoryState.mLongValues);
        writeValues(storageInternalRepresentation, Constants.STRING_TYPE_JSON_VALUE, configMemoryState.mStringValues);
        ConfigStorageCache.writeRepresentationToStorageCache(context, storageInternalRepresentation);
    }

    public static <Type> void writeValues(StorageInternalRepresentation storageInternalRepresentation, String str, Map<String, ValueInfo<Type>> map) {
        for (Map.Entry<String, ValueInfo<Type>> entry : map.entrySet()) {
            String key = entry.getKey();
            ValueInfo<Type> value = entry.getValue();
            Type type = value.mValueForSerialization;
            if (type == null) {
                AnonymousClass0MD.A09(TAG, "No value set for param name %s", key);
            } else {
                storageInternalRepresentation.Params.add(new StorageParam(key, str, type.toString(), value.mLoggingId, value.mSessionless));
            }
        }
    }
}
