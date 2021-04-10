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

/* access modifiers changed from: package-private */
@Nullsafe(Nullsafe.Mode.LOCAL)
public class ConfigStorageCache {
    private static final String PARAMS_MAP_VERSION_JSON_KEY = "params_map_version";
    private static final String PARAM_LOGGINGID_JSON_KEY = "logging_id";
    private static final String PARAM_NAME_JSON_KEY = "param_name";
    private static final String PARAM_SESSIONLESS_JSON_KEY = "sessionless";
    private static final String PARAM_TYPE_JSON_KEY = "type";
    private static final String PARAM_VALUE_JSON_KEY = "value";
    private static final String PREF_CACHE_KEY = "Cache";
    private static final String PREF_FILE_KEY = "DeviceConfigCache";
    private static final String TAG = "ConfigStorageCache";
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
            String string = context.getSharedPreferences(PREF_FILE_KEY, 0).getString(PREF_CACHE_KEY, null);
            if (string == null) {
                BLog.d(TAG, "No existing StorageCache");
                return internalRepresentation;
            }
            JSONObject jSONObject = new JSONObject(string);
            if (!"1.0".equals(getString(jSONObject, VERSION_JSON_KEY, null))) {
                BLog.d(TAG, "StorageCache pref file version changed - Skip.");
                return internalRepresentation;
            }
            String string2 = getString(jSONObject, PARAMS_MAP_VERSION_JSON_KEY, null);
            if (string2 != null) {
                internalRepresentation.ParamsMapVersion = string2;
            }
            JSONArray jSONArray = jSONObject.getJSONArray(VALUES_JSON_KEY);
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                try {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    String string3 = getString(jSONObject2, PARAM_NAME_JSON_KEY, null);
                    String string4 = getString(jSONObject2, "value", null);
                    String string5 = getString(jSONObject2, "type", null);
                    if (string3 != null) {
                        if (!string3.isEmpty()) {
                            if (string4 != null) {
                                if (string5 != null) {
                                    internalRepresentation.Params.add(new ConfigStorageParam(string3, string5, string4, getString(jSONObject2, PARAM_LOGGINGID_JSON_KEY, null), getBoolean(jSONObject2, PARAM_SESSIONLESS_JSON_KEY, false)));
                                }
                            }
                            BLog.e(TAG, "Value or type field missing in cached Config Param '%s'", string3);
                        }
                    }
                    BLog.e(TAG, "Field missing or empty in cached Config Param");
                } catch (Exception e) {
                    BLog.e(TAG, e, "Failed parsing a config param");
                }
            }
            BLog.d(TAG, "Finished reading StorageCache.");
            return internalRepresentation;
        } catch (Exception e2) {
            BLog.e(TAG, e2, "Failure to read cache");
        }
    }

    @Nullable
    private static String getString(JSONObject jSONObject, String str, @Nullable String str2) {
        try {
            if (jSONObject.has(str)) {
                return jSONObject.getString(str);
            }
        } catch (JSONException unused) {
        }
        return str2;
    }

    private static boolean getBoolean(JSONObject jSONObject, String str, boolean z) {
        try {
            if (jSONObject.has(str)) {
                return jSONObject.getBoolean(str);
            }
        } catch (JSONException unused) {
        }
        return z;
    }

    public static void writeRepresentationToStorageCache(Context context, InternalRepresentation internalRepresentation) {
        try {
            JSONArray jSONArray = new JSONArray();
            for (ConfigStorageParam configStorageParam : internalRepresentation.Params) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(PARAM_NAME_JSON_KEY, configStorageParam.getName());
                    jSONObject.put("value", configStorageParam.getValue());
                    jSONObject.put("type", configStorageParam.getType());
                    jSONObject.put(PARAM_LOGGINGID_JSON_KEY, configStorageParam.getLoggingId());
                    jSONObject.put(PARAM_SESSIONLESS_JSON_KEY, configStorageParam.isSessionless());
                    jSONArray.put(jSONObject);
                } catch (JSONException e) {
                    BLog.e(TAG, e, "Failure to add JSON object for config param '%s'", configStorageParam.getName());
                }
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(VALUES_JSON_KEY, jSONArray);
            jSONObject2.put(VERSION_JSON_KEY, "1.0");
            jSONObject2.put(PARAMS_MAP_VERSION_JSON_KEY, internalRepresentation.ParamsMapVersion);
            String jSONObject3 = jSONObject2.toString();
            BLog.d(TAG, "Write this MemoryState to StorageCache");
            BLog.d(TAG, jSONObject3);
            SharedPreferences.Editor edit = context.getSharedPreferences(PREF_FILE_KEY, 0).edit();
            edit.putString(PREF_CACHE_KEY, jSONObject3);
            edit.apply();
        } catch (Exception e2) {
            BLog.e(TAG, e2, "Failure to write cache");
        }
    }
}
