package com.oculus.deviceconfigclient;

import X.AnonymousClass0NO;
import android.content.Context;
import android.content.SharedPreferences;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.deviceconfigclient.shared.StorageInternalRepresentation;
import com.oculus.deviceconfigclient.shared.StorageParam;
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

    public static void A00(Context context, StorageInternalRepresentation storageInternalRepresentation) {
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
                    AnonymousClass0NO.A0K(TAG, e, "Failure to add JSON object for config param '%s'", storageParam.mName);
                }
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(VALUES_JSON_KEY, jSONArray);
            jSONObject2.put("version", VERSION_JSON_VALUE);
            jSONObject2.put(PARAMS_MAP_VERSION_JSON_KEY, storageInternalRepresentation.ParamsMapVersion);
            String obj = jSONObject2.toString();
            SharedPreferences.Editor edit = context.getSharedPreferences(PREF_FILE_KEY, 0).edit();
            edit.putString(PREF_CACHE_KEY, obj);
            edit.apply();
        } catch (Exception e2) {
            AnonymousClass0NO.A0H(TAG, e2, "Failure to write cache");
        }
    }
}
