package com.oculus.deviceconfigclient;

import android.content.Context;
import android.content.SharedPreferences;
import com.facebook.debug.log.BLog;
import com.oculus.deviceconfigclient.shared.StorageInternalRepresentation;
import com.oculus.deviceconfigclient.shared.StorageParam;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ConfigStorageCache {
    private static final String PARAMS_MAP_VERSION_JSON_KEY = "params_map_version";
    private static final String PARAM_LOGGINGID_JSON_KEY = "logging_id";
    private static final String PARAM_NAME_JSON_KEY = "param_name";
    private static final String PARAM_SESSIONLESS_JSON_KEY = "sessionless";
    private static final String PARAM_TYPE_JSON_KEY = "type";
    private static final String PARAM_VALUE_JSON_KEY = "value";
    private static final String PREF_CACHE_KEY = "Cache";
    private static final String PREF_FILE_KEY = "DeviceConfigCache";
    private static final String TAG = ConfigStorageCache.class.getSimpleName();
    private static final String VALUES_JSON_KEY = "values";
    private static final String VERSION_JSON_KEY = "version";
    private static final String VERSION_JSON_VALUE = "1.0";
    private static final boolean mDebugLog = true;

    public static StorageInternalRepresentation readRepresentationFromStorageCache(Context context) {
        BLog.d(TAG, "Create Representation from StorageCache");
        StorageInternalRepresentation internalRepresentation = new StorageInternalRepresentation();
        try {
            String input = context.getSharedPreferences(PREF_FILE_KEY, 0).getString(PREF_CACHE_KEY, null);
            if (input == null) {
                BLog.d(TAG, "No existing StorageCache");
            } else {
                JSONObject topJsonObject = new JSONObject(input);
                if (!"1.0".equals(getString(topJsonObject, VERSION_JSON_KEY, null))) {
                    BLog.d(TAG, "StorageCache pref file version changed - Skip.");
                } else {
                    String paramsMapVersion = getString(topJsonObject, PARAMS_MAP_VERSION_JSON_KEY, null);
                    if (paramsMapVersion != null) {
                        internalRepresentation.ParamsMapVersion = paramsMapVersion;
                    }
                    JSONArray allJsonValues = topJsonObject.getJSONArray(VALUES_JSON_KEY);
                    int arrayLength = allJsonValues.length();
                    for (int i = 0; i < arrayLength; i++) {
                        try {
                            JSONObject jsonValue = allJsonValues.getJSONObject(i);
                            String paramName = getString(jsonValue, PARAM_NAME_JSON_KEY, null);
                            String paramValue = getString(jsonValue, PARAM_VALUE_JSON_KEY, null);
                            String paramType = getString(jsonValue, PARAM_TYPE_JSON_KEY, null);
                            if (paramName == null || paramName.isEmpty()) {
                                BLog.e(TAG, "Field missing or empty in cached Config Param");
                            } else if (paramValue == null || paramType == null) {
                                BLog.e(TAG, "Value or type field missing in cached Config Param '%s'", paramName);
                            } else {
                                internalRepresentation.Params.add(new StorageParam(paramName, paramType, paramValue, getString(jsonValue, PARAM_LOGGINGID_JSON_KEY, null), getBoolean(jsonValue, PARAM_SESSIONLESS_JSON_KEY, false)));
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

    @Nullable
    private static String getString(JSONObject object, String key, @Nullable String defaultValue) {
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
                    jsonParam.put(PARAM_NAME_JSON_KEY, param.getName());
                    jsonParam.put(PARAM_VALUE_JSON_KEY, param.getValue());
                    jsonParam.put(PARAM_TYPE_JSON_KEY, param.getType());
                    jsonParam.put(PARAM_LOGGINGID_JSON_KEY, param.getLoggingId());
                    jsonParam.put(PARAM_SESSIONLESS_JSON_KEY, param.isSessionless());
                    allJsonValues.put(jsonParam);
                } catch (JSONException e) {
                    BLog.e(TAG, e, "Failure to add JSON object for config param '%s'", param.getName());
                }
            }
            JSONObject topJsonObject = new JSONObject();
            topJsonObject.put(VALUES_JSON_KEY, allJsonValues);
            topJsonObject.put(VERSION_JSON_KEY, "1.0");
            topJsonObject.put(PARAMS_MAP_VERSION_JSON_KEY, internalRepresentation.ParamsMapVersion);
            String output = topJsonObject.toString();
            BLog.d(TAG, "Write this MemoryState to StorageCache");
            BLog.d(TAG, output);
            SharedPreferences.Editor editor = context.getSharedPreferences(PREF_FILE_KEY, 0).edit();
            editor.putString(PREF_CACHE_KEY, output);
            editor.apply();
        } catch (Exception e2) {
            BLog.e(TAG, e2, "Failure to write cache");
        }
    }
}
