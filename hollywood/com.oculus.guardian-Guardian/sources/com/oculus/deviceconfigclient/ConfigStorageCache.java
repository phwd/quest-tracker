package com.oculus.deviceconfigclient;

import android.content.Context;
import android.content.SharedPreferences;
import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.Nullsafe;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Nullsafe(Nullsafe.Mode.LOCAL)
class ConfigStorageCache {
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

    public static class InternalRepresentation {
        public final List<ConfigStorageParam> Params = new ArrayList();
        @Nullable
        public String ParamsMapVersion;
    }

    ConfigStorageCache() {
    }

    public static InternalRepresentation readRepresentationFromStorageCache(Context context) {
        BLog.d(TAG, "Create Representation from StorageCache");
        InternalRepresentation internalRepresentation = new InternalRepresentation();
        try {
            String str = null;
            String input = context.getSharedPreferences(PREF_FILE_KEY, 0).getString(PREF_CACHE_KEY, null);
            if (input == null) {
                BLog.d(TAG, "No existing StorageCache");
                return internalRepresentation;
            }
            JSONObject topJsonObject = new JSONObject(input);
            if (!VERSION_JSON_VALUE.equals(getString(topJsonObject, VERSION_JSON_KEY, null))) {
                BLog.d(TAG, "StorageCache pref file version changed - Skip.");
                return internalRepresentation;
            }
            String paramsMapVersion = getString(topJsonObject, PARAMS_MAP_VERSION_JSON_KEY, null);
            if (paramsMapVersion != null) {
                internalRepresentation.ParamsMapVersion = paramsMapVersion;
            }
            JSONArray allJsonValues = topJsonObject.getJSONArray(VALUES_JSON_KEY);
            int arrayLength = allJsonValues.length();
            int i = 0;
            while (i < arrayLength) {
                try {
                    JSONObject jsonValue = allJsonValues.getJSONObject(i);
                    String paramName = getString(jsonValue, PARAM_NAME_JSON_KEY, str);
                    String paramValue = getString(jsonValue, PARAM_VALUE_JSON_KEY, str);
                    String paramType = getString(jsonValue, PARAM_TYPE_JSON_KEY, str);
                    if (paramName != null) {
                        if (!paramName.isEmpty()) {
                            if (paramValue != null) {
                                if (paramType != null) {
                                    internalRepresentation.Params.add(new ConfigStorageParam(paramName, paramType, paramValue, getString(jsonValue, PARAM_LOGGINGID_JSON_KEY, str), getBoolean(jsonValue, PARAM_SESSIONLESS_JSON_KEY, false)));
                                    i++;
                                    str = null;
                                }
                            }
                            BLog.e(TAG, "Value or type field missing in cached Config Param '%s'", new Object[]{paramName});
                            i++;
                            str = null;
                        }
                    }
                    BLog.e(TAG, "Field missing or empty in cached Config Param");
                } catch (Exception e) {
                    BLog.e(TAG, e, "Failed parsing a config param");
                }
                i++;
                str = null;
            }
            BLog.d(TAG, "Finished reading StorageCache.");
            return internalRepresentation;
        } catch (Exception e2) {
            BLog.e(TAG, e2, "Failure to read cache");
        }
    }

    @Nullable
    private static String getString(JSONObject object, String key, @Nullable String defaultValue) {
        try {
            if (object.has(key)) {
                return object.getString(key);
            }
        } catch (JSONException e) {
        }
        return defaultValue;
    }

    private static boolean getBoolean(JSONObject object, String key, boolean defaultValue) {
        try {
            if (object.has(key)) {
                return object.getBoolean(key);
            }
        } catch (JSONException e) {
        }
        return defaultValue;
    }

    public static void writeRepresentationToStorageCache(Context context, InternalRepresentation internalRepresentation) {
        try {
            JSONArray allJsonValues = new JSONArray();
            for (ConfigStorageParam param : internalRepresentation.Params) {
                try {
                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put(PARAM_NAME_JSON_KEY, param.getName());
                    jsonParam.put(PARAM_VALUE_JSON_KEY, param.getValue());
                    jsonParam.put(PARAM_TYPE_JSON_KEY, param.getType());
                    jsonParam.put(PARAM_LOGGINGID_JSON_KEY, param.getLoggingId());
                    jsonParam.put(PARAM_SESSIONLESS_JSON_KEY, param.isSessionless());
                    allJsonValues.put(jsonParam);
                } catch (JSONException e) {
                    BLog.e(TAG, e, "Failure to add JSON object for config param '%s'", new Object[]{param.getName()});
                }
            }
            JSONObject topJsonObject = new JSONObject();
            topJsonObject.put(VALUES_JSON_KEY, allJsonValues);
            topJsonObject.put(VERSION_JSON_KEY, VERSION_JSON_VALUE);
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
