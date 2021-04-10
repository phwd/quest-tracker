package com.oculus.deviceconfigclient;

import android.content.Context;
import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.deviceconfigclient.ValueInfo;
import com.oculus.deviceconfigclient.shared.StorageInternalRepresentation;
import com.oculus.deviceconfigclient.shared.StorageParam;
import java.util.Map;

@Nullsafe(Nullsafe.Mode.LOCAL)
class ConfigStorageAdapter {
    private static final String TAG = "ConfigStorageAdapter";

    ConfigStorageAdapter() {
    }

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
            char c = 65535;
            try {
                switch (str3.hashCode()) {
                    case -1325958191:
                        if (str3.equals("double")) {
                            c = 1;
                            break;
                        }
                        break;
                    case -891985903:
                        if (str3.equals("string")) {
                            c = 3;
                            break;
                        }
                        break;
                    case 3327612:
                        if (str3.equals("long")) {
                            c = 2;
                            break;
                        }
                        break;
                    case 64711720:
                        if (str3.equals("boolean")) {
                            c = 0;
                            break;
                        }
                        break;
                }
                if (c == 0) {
                    ValueInfo<Boolean> valueInfo = new ValueInfo<>(Boolean.valueOf(Boolean.parseBoolean(str4)), ValueInfo.ValueSetFlags.FromCache);
                    valueInfo.setLoggingId(str5, z);
                    configMemoryState.mBooleanValues.put(str2, valueInfo);
                } else if (c == 1) {
                    ValueInfo<Double> valueInfo2 = new ValueInfo<>(Double.valueOf(Double.parseDouble(str4)), ValueInfo.ValueSetFlags.FromCache);
                    valueInfo2.setLoggingId(str5, z);
                    configMemoryState.mDoubleValues.put(str2, valueInfo2);
                } else if (c == 2) {
                    ValueInfo<Long> valueInfo3 = new ValueInfo<>(Long.valueOf(Long.parseLong(str4)), ValueInfo.ValueSetFlags.FromCache);
                    valueInfo3.setLoggingId(str5, z);
                    configMemoryState.mLongValues.put(str2, valueInfo3);
                } else if (c != 3) {
                    BLog.e(TAG, "incorrect type '%s' in cached Config Param '%s' with value '%s'", str3, str2, str4);
                } else {
                    ValueInfo<String> valueInfo4 = new ValueInfo<>(str4, ValueInfo.ValueSetFlags.FromCache);
                    valueInfo4.setLoggingId(str5, z);
                    configMemoryState.mStringValues.put(str2, valueInfo4);
                }
            } catch (Exception e) {
                BLog.e(TAG, e, "Could not convert value '%s' to type '%s' in cached Config Param '%s'", str4, str3, str2);
            }
        }
        return configMemoryState;
    }

    public static void writeMemoryStateToStorageCache(Context context, ConfigMemoryState configMemoryState) {
        StorageInternalRepresentation storageInternalRepresentation = new StorageInternalRepresentation();
        storageInternalRepresentation.ParamsMapVersion = configMemoryState.mParamsMapVersion;
        writeValues(storageInternalRepresentation, "boolean", configMemoryState.mBooleanValues);
        writeValues(storageInternalRepresentation, "double", configMemoryState.mDoubleValues);
        writeValues(storageInternalRepresentation, "long", configMemoryState.mLongValues);
        writeValues(storageInternalRepresentation, "string", configMemoryState.mStringValues);
        ConfigStorageCache.writeRepresentationToStorageCache(context, storageInternalRepresentation);
    }

    private static <Type> void writeValues(StorageInternalRepresentation storageInternalRepresentation, String str, Map<String, ValueInfo<Type>> map) {
        for (Map.Entry<String, ValueInfo<Type>> entry : map.entrySet()) {
            String key = entry.getKey();
            ValueInfo<Type> value = entry.getValue();
            Type type = value.mValueForSerialization;
            if (type == null) {
                BLog.e(TAG, "No value set for param name %s", key);
            } else {
                storageInternalRepresentation.Params.add(new StorageParam(key, str, type.toString(), value.mLoggingId, value.mSessionless));
            }
        }
    }
}
