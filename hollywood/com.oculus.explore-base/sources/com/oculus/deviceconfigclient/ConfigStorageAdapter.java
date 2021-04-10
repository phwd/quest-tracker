package com.oculus.deviceconfigclient;

import android.content.Context;
import com.facebook.debug.log.BLog;
import com.oculus.deviceconfigclient.ValueInfo;
import com.oculus.deviceconfigclient.shared.StorageInternalRepresentation;
import com.oculus.deviceconfigclient.shared.StorageParam;
import com.oculus.panellib.Qpl;
import java.util.Map;

class ConfigStorageAdapter {
    private static final String TAG = ConfigStorageAdapter.class.getSimpleName();

    ConfigStorageAdapter() {
    }

    public static ConfigMemoryState createMemoryStateFromStorageCache(Context context) {
        ConfigMemoryState memoryState = new ConfigMemoryState();
        StorageInternalRepresentation internalRepresentation = ConfigStorageCache.readRepresentationFromStorageCache(context);
        String paramsMapVersion = internalRepresentation.ParamsMapVersion;
        if (paramsMapVersion != null) {
            memoryState.setParamsMapVersion(paramsMapVersion);
        }
        for (StorageParam param : internalRepresentation.Params) {
            String paramName = param.getName();
            String paramType = param.getType();
            String paramValue = param.getValue();
            String logginId = param.getLoggingId();
            boolean isSessionless = param.isSessionless();
            char c = 65535;
            try {
                switch (paramType.hashCode()) {
                    case -1325958191:
                        if (paramType.equals("double")) {
                            c = 1;
                            break;
                        }
                        break;
                    case -891985903:
                        if (paramType.equals("string")) {
                            c = 3;
                            break;
                        }
                        break;
                    case 3327612:
                        if (paramType.equals("long")) {
                            c = 2;
                            break;
                        }
                        break;
                    case 64711720:
                        if (paramType.equals("boolean")) {
                            c = 0;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        ValueInfo<Boolean> valueInfo = new ValueInfo<>(Boolean.valueOf(Boolean.parseBoolean(paramValue)), ValueInfo.ValueSetFlags.FromCache);
                        valueInfo.setLoggingId(logginId, isSessionless);
                        memoryState.getBooleanValues().put(paramName, valueInfo);
                        continue;
                    case 1:
                        ValueInfo<Double> valueInfo2 = new ValueInfo<>(Double.valueOf(Double.parseDouble(paramValue)), ValueInfo.ValueSetFlags.FromCache);
                        valueInfo2.setLoggingId(logginId, isSessionless);
                        memoryState.getDoubleValues().put(paramName, valueInfo2);
                        continue;
                    case Qpl.ACTION_SUCCESS /*{ENCODED_INT: 2}*/:
                        ValueInfo<Long> valueInfo3 = new ValueInfo<>(Long.valueOf(Long.parseLong(paramValue)), ValueInfo.ValueSetFlags.FromCache);
                        valueInfo3.setLoggingId(logginId, isSessionless);
                        memoryState.getLongValues().put(paramName, valueInfo3);
                        continue;
                    case 3:
                        ValueInfo<String> valueInfo4 = new ValueInfo<>(paramValue, ValueInfo.ValueSetFlags.FromCache);
                        valueInfo4.setLoggingId(logginId, isSessionless);
                        memoryState.getStringValues().put(paramName, valueInfo4);
                        continue;
                    default:
                        BLog.e(TAG, "incorrect type '%s' in cached Config Param '%s' with value '%s'", paramType, paramName, paramValue);
                        continue;
                }
            } catch (Exception e) {
                BLog.e(TAG, e, "Could not convert value '%s' to type '%s' in cached Config Param '%s'", paramValue, paramType, paramName);
            }
        }
        return memoryState;
    }

    public static void writeMemoryStateToStorageCache(Context context, ConfigMemoryState memoryState) {
        StorageInternalRepresentation internalRepresentation = new StorageInternalRepresentation();
        internalRepresentation.ParamsMapVersion = memoryState.getParamsMapVersion();
        writeValues(internalRepresentation, "boolean", memoryState.getBooleanValues());
        writeValues(internalRepresentation, "double", memoryState.getDoubleValues());
        writeValues(internalRepresentation, "long", memoryState.getLongValues());
        writeValues(internalRepresentation, "string", memoryState.getStringValues());
        ConfigStorageCache.writeRepresentationToStorageCache(context, internalRepresentation);
    }

    private static <Type> void writeValues(StorageInternalRepresentation internalRepresentation, String paramType, Map<String, ValueInfo<Type>> allValues) {
        for (Map.Entry<String, ValueInfo<Type>> valueEntry : allValues.entrySet()) {
            String paramName = valueEntry.getKey();
            ValueInfo<Type> valueInfo = valueEntry.getValue();
            Type paramValue = valueInfo.getValueForSerialization();
            if (paramValue == null) {
                BLog.e(TAG, "No value set for param name %s", paramName);
            } else {
                internalRepresentation.Params.add(new StorageParam(paramName, paramType, paramValue.toString(), valueInfo.getLoggingId(), valueInfo.isSessionless()));
            }
        }
    }
}
