package com.oculus.deviceconfigclient;

import android.content.Context;
import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.deviceconfigclient.ConfigStorageCache;
import com.oculus.deviceconfigclient.ValueInfo;
import java.util.Map;

/* access modifiers changed from: package-private */
@Nullsafe(Nullsafe.Mode.LOCAL)
public class ConfigStorageAdapter {
    private static final String BOOLEAN_TYPE_JSON_VALUE = "boolean";
    private static final String DOUBLE_TYPE_JSON_VALUE = "double";
    private static final String LONG_TYPE_JSON_VALUE = "long";
    private static final String STRING_TYPE_JSON_VALUE = "string";
    private static final String TAG = "ConfigStorageAdapter";

    ConfigStorageAdapter() {
    }

    public static ConfigMemoryState createMemoryStateFromStorageCache(Context context) {
        ConfigMemoryState configMemoryState = new ConfigMemoryState();
        ConfigStorageCache.InternalRepresentation readRepresentationFromStorageCache = ConfigStorageCache.readRepresentationFromStorageCache(context);
        String str = readRepresentationFromStorageCache.ParamsMapVersion;
        if (str != null) {
            configMemoryState.setParamsMapVersion(str);
        }
        for (ConfigStorageParam configStorageParam : readRepresentationFromStorageCache.Params) {
            String name = configStorageParam.getName();
            String type = configStorageParam.getType();
            String value = configStorageParam.getValue();
            String loggingId = configStorageParam.getLoggingId();
            boolean isSessionless = configStorageParam.isSessionless();
            char c = 65535;
            try {
                switch (type.hashCode()) {
                    case -1325958191:
                        if (type.equals(DOUBLE_TYPE_JSON_VALUE)) {
                            c = 1;
                            break;
                        }
                        break;
                    case -891985903:
                        if (type.equals(STRING_TYPE_JSON_VALUE)) {
                            c = 3;
                            break;
                        }
                        break;
                    case 3327612:
                        if (type.equals(LONG_TYPE_JSON_VALUE)) {
                            c = 2;
                            break;
                        }
                        break;
                    case 64711720:
                        if (type.equals(BOOLEAN_TYPE_JSON_VALUE)) {
                            c = 0;
                            break;
                        }
                        break;
                }
                if (c == 0) {
                    ValueInfo<Boolean> valueInfo = new ValueInfo<>(Boolean.valueOf(Boolean.parseBoolean(value)), ValueInfo.ValueSetFlags.FromCache);
                    valueInfo.setLoggingId(loggingId, isSessionless);
                    configMemoryState.getBooleanValues().put(name, valueInfo);
                } else if (c == 1) {
                    ValueInfo<Double> valueInfo2 = new ValueInfo<>(Double.valueOf(Double.parseDouble(value)), ValueInfo.ValueSetFlags.FromCache);
                    valueInfo2.setLoggingId(loggingId, isSessionless);
                    configMemoryState.getDoubleValues().put(name, valueInfo2);
                } else if (c == 2) {
                    ValueInfo<Long> valueInfo3 = new ValueInfo<>(Long.valueOf(Long.parseLong(value)), ValueInfo.ValueSetFlags.FromCache);
                    valueInfo3.setLoggingId(loggingId, isSessionless);
                    configMemoryState.getLongValues().put(name, valueInfo3);
                } else if (c != 3) {
                    BLog.e(TAG, "incorrect type '%s' in cached Config Param '%s' with value '%s'", type, name, value);
                } else {
                    ValueInfo<String> valueInfo4 = new ValueInfo<>(value, ValueInfo.ValueSetFlags.FromCache);
                    valueInfo4.setLoggingId(loggingId, isSessionless);
                    configMemoryState.getStringValues().put(name, valueInfo4);
                }
            } catch (Exception e) {
                BLog.e(TAG, e, "Could not convert value '%s' to type '%s' in cached Config Param '%s'", value, type, name);
            }
        }
        return configMemoryState;
    }

    public static void writeMemoryStateToStorageCache(Context context, ConfigMemoryState configMemoryState) {
        ConfigStorageCache.InternalRepresentation internalRepresentation = new ConfigStorageCache.InternalRepresentation();
        internalRepresentation.ParamsMapVersion = configMemoryState.getParamsMapVersion();
        writeValues(internalRepresentation, BOOLEAN_TYPE_JSON_VALUE, configMemoryState.getBooleanValues());
        writeValues(internalRepresentation, DOUBLE_TYPE_JSON_VALUE, configMemoryState.getDoubleValues());
        writeValues(internalRepresentation, LONG_TYPE_JSON_VALUE, configMemoryState.getLongValues());
        writeValues(internalRepresentation, STRING_TYPE_JSON_VALUE, configMemoryState.getStringValues());
        ConfigStorageCache.writeRepresentationToStorageCache(context, internalRepresentation);
    }

    private static <Type> void writeValues(ConfigStorageCache.InternalRepresentation internalRepresentation, String str, Map<String, ValueInfo<Type>> map) {
        for (Map.Entry<String, ValueInfo<Type>> entry : map.entrySet()) {
            String key = entry.getKey();
            ValueInfo<Type> value = entry.getValue();
            Type valueForSerialization = value.getValueForSerialization();
            if (valueForSerialization == null) {
                BLog.e(TAG, "No value set for param name %s", key);
            } else {
                internalRepresentation.Params.add(new ConfigStorageParam(key, str, valueForSerialization.toString(), value.getLoggingId(), value.isSessionless()));
            }
        }
    }
}
