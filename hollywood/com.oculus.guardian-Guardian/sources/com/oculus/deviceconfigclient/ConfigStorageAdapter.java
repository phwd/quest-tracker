package com.oculus.deviceconfigclient;

import android.content.Context;
import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.deviceconfigclient.ConfigStorageCache;
import com.oculus.deviceconfigclient.ValueInfo;
import java.util.Map;

@Nullsafe(Nullsafe.Mode.LOCAL)
class ConfigStorageAdapter {
    private static final String BOOLEAN_TYPE_JSON_VALUE = "boolean";
    private static final String DOUBLE_TYPE_JSON_VALUE = "double";
    private static final String LONG_TYPE_JSON_VALUE = "long";
    private static final String STRING_TYPE_JSON_VALUE = "string";
    private static final String TAG = ConfigStorageAdapter.class.getSimpleName();

    ConfigStorageAdapter() {
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public static ConfigMemoryState createMemoryStateFromStorageCache(Context context) {
        ConfigMemoryState memoryState = new ConfigMemoryState();
        ConfigStorageCache.InternalRepresentation internalRepresentation = ConfigStorageCache.readRepresentationFromStorageCache(context);
        String paramsMapVersion = internalRepresentation.ParamsMapVersion;
        if (paramsMapVersion != null) {
            memoryState.setParamsMapVersion(paramsMapVersion);
        }
        for (ConfigStorageParam param : internalRepresentation.Params) {
            String paramName = param.getName();
            String paramType = param.getType();
            String paramValue = param.getValue();
            String logginId = param.getLoggingId();
            boolean isSessionless = param.isSessionless();
            char c = 65535;
            try {
                switch (paramType.hashCode()) {
                    case -1325958191:
                        if (paramType.equals(DOUBLE_TYPE_JSON_VALUE)) {
                            c = 1;
                            break;
                        }
                        break;
                    case -891985903:
                        if (paramType.equals(STRING_TYPE_JSON_VALUE)) {
                            c = 3;
                            break;
                        }
                        break;
                    case 3327612:
                        if (paramType.equals(LONG_TYPE_JSON_VALUE)) {
                            c = 2;
                            break;
                        }
                        break;
                    case 64711720:
                        if (paramType.equals(BOOLEAN_TYPE_JSON_VALUE)) {
                            c = 0;
                            break;
                        }
                        break;
                }
                if (c == 0) {
                    ValueInfo<Boolean> valueInfo = new ValueInfo<>(Boolean.valueOf(Boolean.parseBoolean(paramValue)), ValueInfo.ValueSetFlags.FromCache);
                    valueInfo.setLoggingId(logginId, isSessionless);
                    memoryState.getBooleanValues().put(paramName, valueInfo);
                } else if (c == 1) {
                    ValueInfo<Double> valueInfo2 = new ValueInfo<>(Double.valueOf(Double.parseDouble(paramValue)), ValueInfo.ValueSetFlags.FromCache);
                    valueInfo2.setLoggingId(logginId, isSessionless);
                    memoryState.getDoubleValues().put(paramName, valueInfo2);
                } else if (c == 2) {
                    ValueInfo<Long> valueInfo3 = new ValueInfo<>(Long.valueOf(Long.parseLong(paramValue)), ValueInfo.ValueSetFlags.FromCache);
                    valueInfo3.setLoggingId(logginId, isSessionless);
                    memoryState.getLongValues().put(paramName, valueInfo3);
                } else if (c != 3) {
                    BLog.e(TAG, "incorrect type '%s' in cached Config Param '%s' with value '%s'", new Object[]{paramType, paramName, paramValue});
                } else {
                    ValueInfo<String> valueInfo4 = new ValueInfo<>(paramValue, ValueInfo.ValueSetFlags.FromCache);
                    valueInfo4.setLoggingId(logginId, isSessionless);
                    memoryState.getStringValues().put(paramName, valueInfo4);
                }
            } catch (Exception e) {
                BLog.e(TAG, e, "Could not convert value '%s' to type '%s' in cached Config Param '%s'", new Object[]{paramValue, paramType, paramName});
            }
        }
        return memoryState;
    }

    public static void writeMemoryStateToStorageCache(Context context, ConfigMemoryState memoryState) {
        ConfigStorageCache.InternalRepresentation internalRepresentation = new ConfigStorageCache.InternalRepresentation();
        internalRepresentation.ParamsMapVersion = memoryState.getParamsMapVersion();
        writeValues(internalRepresentation, BOOLEAN_TYPE_JSON_VALUE, memoryState.getBooleanValues());
        writeValues(internalRepresentation, DOUBLE_TYPE_JSON_VALUE, memoryState.getDoubleValues());
        writeValues(internalRepresentation, LONG_TYPE_JSON_VALUE, memoryState.getLongValues());
        writeValues(internalRepresentation, STRING_TYPE_JSON_VALUE, memoryState.getStringValues());
        ConfigStorageCache.writeRepresentationToStorageCache(context, internalRepresentation);
    }

    private static <Type> void writeValues(ConfigStorageCache.InternalRepresentation internalRepresentation, String paramType, Map<String, ValueInfo<Type>> allValues) {
        for (Map.Entry<String, ValueInfo<Type>> valueEntry : allValues.entrySet()) {
            String paramName = valueEntry.getKey();
            ValueInfo<Type> valueInfo = valueEntry.getValue();
            Type paramValue = valueInfo.getValueForSerialization();
            if (paramValue == null) {
                BLog.e(TAG, "No value set for param name %s", new Object[]{paramName});
            } else {
                internalRepresentation.Params.add(new ConfigStorageParam(paramName, paramType, paramValue.toString(), valueInfo.getLoggingId(), valueInfo.isSessionless()));
            }
        }
    }
}
