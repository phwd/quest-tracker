package com.oculus.deviceconfigclient;

import android.content.Context;
import android.content.SharedPreferences;
import com.facebook.debug.log.BLog;
import com.oculus.deviceconfigclient.shared.StorageInternalRepresentation;
import com.oculus.deviceconfigclient.shared.StorageParam;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ConfigStorageCache {
    private static final String TAG = ConfigStorageCache.class.getSimpleName();

    public static StorageInternalRepresentation readRepresentationFromStorageCache(Context context) {
        BLog.d(TAG, "Create Representation from StorageCache");
        StorageInternalRepresentation internalRepresentation = new StorageInternalRepresentation();
        try {
            String input = context.getSharedPreferences("DeviceConfigCache", 0).getString("Cache", null);
            if (input == null) {
                BLog.d(TAG, "No existing StorageCache");
            } else {
                JSONObject topJsonObject = new JSONObject(input);
                if (!"1.0".equals(getString(topJsonObject, "version", null))) {
                    BLog.d(TAG, "StorageCache pref file version changed - Skip.");
                } else {
                    String paramsMapVersion = getString(topJsonObject, "params_map_version", null);
                    if (paramsMapVersion != null) {
                        internalRepresentation.ParamsMapVersion = paramsMapVersion;
                    }
                    JSONArray allJsonValues = topJsonObject.getJSONArray("values");
                    int arrayLength = allJsonValues.length();
                    for (int i = 0; i < arrayLength; i++) {
                        try {
                            JSONObject jsonValue = allJsonValues.getJSONObject(i);
                            String paramName = getString(jsonValue, "param_name", null);
                            String paramValue = getString(jsonValue, "value", null);
                            String paramType = getString(jsonValue, "type", null);
                            if (paramName == null || paramName.isEmpty()) {
                                BLog.e(TAG, "Field missing or empty in cached Config Param");
                            } else if (paramValue == null || paramType == null) {
                                BLog.e(TAG, "Value or type field missing in cached Config Param '%s'", paramName);
                            } else {
                                internalRepresentation.Params.add(new StorageParam(paramName, paramType, paramValue, getString(jsonValue, "logging_id", null), getBoolean(jsonValue, "sessionless", false)));
                            }
                        } catch (Exception e) {
                            BLog.e(TAG, e, "Failed parsing a config param");
                        }
                    }
                    BLog.d(TAG, "Finished reading StorageCache.");
                }
            }
        } catch (Exception e2) {
            BLog.e(TAG, e2, "Failure to read cache");
        }
        return internalRepresentation;
    }

    private static String getString(JSONObject object, String key, String defaultValue) {
        try {
            if (object.has(key)) {
                return object.getString(key);
            }
            return defaultValue;
        } catch (JSONException e) {
            return defaultValue;
        }
    }

    private static boolean getBoolean(JSONObject object, String key, boolean defaultValue) {
        try {
            if (object.has(key)) {
                return object.getBoolean(key);
            }
            return defaultValue;
        } catch (JSONException e) {
            return defaultValue;
        }
    }

    public static void writeRepresentationToStorageCache(Context context, StorageInternalRepresentation internalRepresentation) {
        try {
            JSONArray allJsonValues = new JSONArray();
            for (StorageParam param : internalRepresentation.Params) {
                try {
                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("param_name", param.getName());
                    jsonParam.put("value", param.getValue());
                    jsonParam.put("type", param.getType());
                    jsonParam.put("logging_id", param.getLoggingId());
                    jsonParam.put("sessionless", param.isSessionless());
                    allJsonValues.put(jsonParam);
                } catch (JSONException e) {
                    BLog.e(TAG, e, "Failure to add JSON object for config param '%s'", param.getName());
                }
            }
            JSONObject topJsonObject = new JSONObject();
            topJsonObject.put("values", allJsonValues);
            topJsonObject.put("version", "1.0");
            topJsonObject.put("params_map_version", internalRepresentation.ParamsMapVersion);
            String output = topJsonObject.toString();
            BLog.d(TAG, "Write this MemoryState to StorageCache");
            BLog.d(TAG, output);
            SharedPreferences.Editor editor = context.getSharedPreferences("DeviceConfigCache", 0).edit();
            editor.putString("Cache", output);
            editor.apply();
        } catch (Exception e2) {
            BLog.e(TAG, e2, "Failure to write cache");
        }
    }
}
