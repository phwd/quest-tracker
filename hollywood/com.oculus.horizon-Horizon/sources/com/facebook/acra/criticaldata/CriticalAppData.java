package com.facebook.acra.criticaldata;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

@SuppressLint({"SharedPreferencesUse"})
public final class CriticalAppData {
    public static final String ACRA_CRITICAL_DATA_STORE = "acra_criticaldata_store";
    public static final String ADDITIONAL_PARAMS = "ADDITIONAL_PARAMS";
    public static final int ADDITIONAL_PARAM_LIMIT = 5;
    public static final String CLIENT_USER_ID = "CLIENT_USER_ID";
    public static final String DEVICE_ID = "DEVICE_ID";
    public static final String IS_EMPLOYEE = "IS_EMPLOYEE";
    public static final String USER_ID = "USER_ID";

    @Nullable
    public static Set<String> getAdditionalParamKeySet(Context context) {
        return getSharedPreferences(context).getStringSet(ADDITIONAL_PARAMS, null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x005e  */
    /* JADX WARNING: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void setCriticalDataParam(android.content.Context r6, java.lang.String r7, java.lang.String r8) {
        /*
        // Method dump skipped, instructions count: 124
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.criticaldata.CriticalAppData.setCriticalDataParam(android.content.Context, java.lang.String, java.lang.String):void");
    }

    public static Map<String, String> getAdditionalParamValues(Context context) {
        HashMap hashMap = new HashMap();
        Set<String> additionalParamKeySet = getAdditionalParamKeySet(context);
        if (additionalParamKeySet != null && !additionalParamKeySet.isEmpty()) {
            SharedPreferences sharedPreferences = getSharedPreferences(context);
            for (String str : additionalParamKeySet) {
                String string = sharedPreferences.getString(str, "");
                if (!TextUtils.isEmpty(string)) {
                    hashMap.put(str, string);
                }
            }
        }
        return hashMap;
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(ACRA_CRITICAL_DATA_STORE, 0);
    }

    public static void setClientUserId(Context context, @Nullable String str) {
        if (str != null && !str.equals(getUserId(context))) {
            getSharedPreferences(context).edit().putString(CLIENT_USER_ID, str).commit();
        }
    }

    public static void setDeviceId(Context context, @Nullable String str) {
        if (str != null && !str.equals(getDeviceId(context))) {
            getSharedPreferences(context).edit().putString(DEVICE_ID, str).commit();
        }
    }

    public static void setUserId(Context context, @Nullable String str) {
        if (str != null && !str.equals(getUserId(context))) {
            getSharedPreferences(context).edit().putString(USER_ID, str).commit();
        }
    }

    public static String getClientUserId(Context context) {
        return getSharedPreferences(context).getString(CLIENT_USER_ID, "");
    }

    public static String getDeviceId(Context context) {
        return getSharedPreferences(context).getString(DEVICE_ID, "");
    }

    public static String getUserId(Context context) {
        return getSharedPreferences(context).getString(USER_ID, "");
    }

    public static boolean isEmployee(Context context) {
        return getSharedPreferences(context).getBoolean(IS_EMPLOYEE, false);
    }

    public static void setUserAndDeviceId(Context context, String str, String str2) {
        if (str != null && str2 != null) {
            if (!str.equals(getUserId(context)) || !str2.equals(getDeviceId(context))) {
                getSharedPreferences(context).edit().putString(USER_ID, str).putString(DEVICE_ID, str2).commit();
            }
        }
    }

    public static void setUserAndDeviceId(Context context, String str, String str2, boolean z) {
        if (str != null && str2 != null) {
            if (!str.equals(getUserId(context)) || !str2.equals(getDeviceId(context)) || z != isEmployee(context)) {
                getSharedPreferences(context).edit().putString(USER_ID, str).putString(DEVICE_ID, str2).putBoolean(IS_EMPLOYEE, z).commit();
            }
        }
    }
}
