package com.oculus.deviceconfigclient;

import android.content.Context;
import android.content.SharedPreferences;
import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.deviceconfigclient.shared.StorageInternalRepresentation;
import com.oculus.deviceconfigclient.shared.StorageParam;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ConfigStorageCache {
    private static final String TAG = "ConfigStorageCache";

    public static StorageInternalRepresentation readRepresentationFromStorageCache(Context context) {
        BLog.d(TAG, "Create Representation from StorageCache");
        StorageInternalRepresentation storageInternalRepresentation = new StorageInternalRepresentation();
        try {
            String string = context.getSharedPreferences("DeviceConfigCache", 0).getString("Cache", null);
            if (string == null) {
                BLog.d(TAG, "No existing StorageCache");
                return storageInternalRepresentation;
            }
            JSONObject jSONObject = new JSONObject(string);
            if (!"1.0".equals(getString(jSONObject, "version", null))) {
                BLog.d(TAG, "StorageCache pref file version changed - Skip.");
                return storageInternalRepresentation;
            }
            String string2 = getString(jSONObject, "params_map_version", null);
            if (string2 != null) {
                storageInternalRepresentation.ParamsMapVersion = string2;
            }
            JSONArray jSONArray = jSONObject.getJSONArray("values");
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                try {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    String string3 = getString(jSONObject2, "param_name", null);
                    String string4 = getString(jSONObject2, "value", null);
                    String string5 = getString(jSONObject2, "type", null);
                    if (string3 != null) {
                        if (!string3.isEmpty()) {
                            if (string4 != null) {
                                if (string5 != null) {
                                    storageInternalRepresentation.Params.add(new StorageParam(string3, string5, string4, getString(jSONObject2, "logging_id", null), getBoolean(jSONObject2, "sessionless", false)));
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
            return storageInternalRepresentation;
        } catch (Exception e2) {
            BLog.e(TAG, e2, "Failure to read cache");
        }
    }

    private static String getString(JSONObject jSONObject, String str, String str2) {
        try {
            if (jSONObject.has(str)) {
                return jSONObject.getString(str);
            }
            return null;
        } catch (JSONException unused) {
            return null;
        }
    }

    private static boolean getBoolean(JSONObject jSONObject, String str, boolean z) {
        try {
            if (jSONObject.has(str)) {
                return jSONObject.getBoolean(str);
            }
            return false;
        } catch (JSONException unused) {
            return false;
        }
    }

    public static void writeRepresentationToStorageCache(Context context, StorageInternalRepresentation storageInternalRepresentation) {
        try {
            JSONArray jSONArray = new JSONArray();
            for (StorageParam storageParam : storageInternalRepresentation.Params) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("param_name", storageParam.mName);
                    jSONObject.put("value", storageParam.mValue);
                    jSONObject.put("type", storageParam.mType);
                    jSONObject.put("logging_id", storageParam.mLoggingId);
                    jSONObject.put("sessionless", storageParam.mIsSessionless);
                    jSONArray.put(jSONObject);
                } catch (JSONException e) {
                    BLog.e(TAG, e, "Failure to add JSON object for config param '%s'", storageParam.mName);
                }
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("values", jSONArray);
            jSONObject2.put("version", "1.0");
            jSONObject2.put("params_map_version", storageInternalRepresentation.ParamsMapVersion);
            String jSONObject3 = jSONObject2.toString();
            logLongMessage("[" + context.getPackageName() + "] Write this MemoryState to StorageCache: ", jSONObject3);
            SharedPreferences.Editor edit = context.getSharedPreferences("DeviceConfigCache", 0).edit();
            edit.putString("Cache", jSONObject3);
            edit.apply();
        } catch (Exception e2) {
            BLog.e(TAG, e2, "Failure to write cache");
        }
    }

    private static void logLongMessage(String str, String str2) {
        while (str2.length() > 3500) {
            String str3 = TAG;
            BLog.d(str3, str + str2.substring(0, 3500));
            str2 = str2.substring(3500);
            str = "[CONTINUED] ";
        }
        String str4 = TAG;
        BLog.d(str4, str + str2);
    }
}
