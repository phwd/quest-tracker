package com.oculus.deviceconfigclient;

import X.AnonymousClass0MD;
import android.content.Context;
import android.content.SharedPreferences;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.deviceconfigclient.shared.StorageInternalRepresentation;
import com.oculus.deviceconfigclient.shared.StorageParam;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ConfigStorageCache {
    public static final String PARAMS_MAP_VERSION_JSON_KEY = "params_map_version";
    public static final String PARAM_LOGGINGID_JSON_KEY = "logging_id";
    public static final String PARAM_NAME_JSON_KEY = "param_name";
    public static final String PARAM_SESSIONLESS_JSON_KEY = "sessionless";
    public static final String PARAM_TYPE_JSON_KEY = "type";
    public static final String PARAM_VALUE_JSON_KEY = "value";
    public static final String PREF_CACHE_KEY = "Cache";
    public static final String PREF_FILE_KEY = "DeviceConfigCache";
    public static final String TAG = "ConfigStorageCache";
    public static final String VALUES_JSON_KEY = "values";
    public static final String VERSION_JSON_KEY = "version";
    public static final String VERSION_JSON_VALUE = "1.0";
    public static final boolean mDebugLog = true;

    public static StorageInternalRepresentation readRepresentationFromStorageCache(Context context) {
        StorageInternalRepresentation storageInternalRepresentation = new StorageInternalRepresentation();
        try {
            String string = context.getSharedPreferences(PREF_FILE_KEY, 0).getString(PREF_CACHE_KEY, null);
            if (string != null) {
                JSONObject jSONObject = new JSONObject(string);
                if ("1.0".equals(getString(jSONObject, "version", null))) {
                    String string2 = getString(jSONObject, PARAMS_MAP_VERSION_JSON_KEY, null);
                    if (string2 != null) {
                        storageInternalRepresentation.ParamsMapVersion = string2;
                    }
                    JSONArray jSONArray = jSONObject.getJSONArray(VALUES_JSON_KEY);
                    int length = jSONArray.length();
                    for (int i = 0; i < length; i++) {
                        try {
                            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                            String string3 = getString(jSONObject2, PARAM_NAME_JSON_KEY, null);
                            String string4 = getString(jSONObject2, "value", null);
                            String string5 = getString(jSONObject2, "type", null);
                            if (string3 == null || string3.isEmpty()) {
                                AnonymousClass0MD.A04(TAG, "Field missing or empty in cached Config Param");
                            } else if (string4 == null || string5 == null) {
                                AnonymousClass0MD.A09(TAG, "Value or type field missing in cached Config Param '%s'", string3);
                            } else {
                                storageInternalRepresentation.Params.add(new StorageParam(string3, string5, string4, getString(jSONObject2, PARAM_LOGGINGID_JSON_KEY, null), getBoolean(jSONObject2, PARAM_SESSIONLESS_JSON_KEY, false)));
                            }
                        } catch (Exception e) {
                            AnonymousClass0MD.A0C(TAG, e, "Failed parsing a config param");
                        }
                    }
                }
            }
        } catch (Exception e2) {
            AnonymousClass0MD.A0C(TAG, e2, "Failure to read cache");
        }
        return storageInternalRepresentation;
    }

    public static void writeRepresentationToStorageCache(Context context, StorageInternalRepresentation storageInternalRepresentation) {
        try {
            JSONArray jSONArray = new JSONArray();
            for (StorageParam storageParam : storageInternalRepresentation.Params) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(PARAM_NAME_JSON_KEY, storageParam.mName);
                    jSONObject.put("value", storageParam.mValue);
                    jSONObject.put("type", storageParam.mType);
                    jSONObject.put(PARAM_LOGGINGID_JSON_KEY, storageParam.mLoggingId);
                    jSONObject.put(PARAM_SESSIONLESS_JSON_KEY, storageParam.mIsSessionless);
                    jSONArray.put(jSONObject);
                } catch (JSONException e) {
                    AnonymousClass0MD.A0E(TAG, e, "Failure to add JSON object for config param '%s'", storageParam.mName);
                }
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(VALUES_JSON_KEY, jSONArray);
            jSONObject2.put("version", "1.0");
            jSONObject2.put(PARAMS_MAP_VERSION_JSON_KEY, storageInternalRepresentation.ParamsMapVersion);
            String obj = jSONObject2.toString();
            SharedPreferences.Editor edit = context.getSharedPreferences(PREF_FILE_KEY, 0).edit();
            edit.putString(PREF_CACHE_KEY, obj);
            edit.apply();
        } catch (Exception e2) {
            AnonymousClass0MD.A0C(TAG, e2, "Failure to write cache");
        }
    }

    public static boolean getBoolean(JSONObject jSONObject, String str, boolean z) {
        try {
            if (jSONObject.has(str)) {
                return jSONObject.getBoolean(str);
            }
        } catch (JSONException unused) {
        }
        return z;
    }

    @Nullable
    public static String getString(JSONObject jSONObject, String str, @Nullable String str2) {
        try {
            if (jSONObject.has(str)) {
                return jSONObject.getString(str);
            }
        } catch (JSONException unused) {
        }
        return str2;
    }
}
