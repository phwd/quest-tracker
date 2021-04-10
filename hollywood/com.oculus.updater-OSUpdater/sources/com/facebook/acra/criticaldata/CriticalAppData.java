package com.facebook.acra.criticaldata;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import com.facebook.debug.log.BLog;
import com.oculus.common.build.BuildConfig;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

@SuppressLint({"SharedPreferencesUse"})
public final class CriticalAppData {
    private static final String ACRA_CRITICAL_DATA_STORE = "acra_criticaldata_store";
    private static final String ADDITIONAL_PARAMS = "ADDITIONAL_PARAMS";
    private static final int ADDITIONAL_PARAM_LIMIT = 5;
    private static final String CLIENT_USER_ID = "CLIENT_USER_ID";
    private static final String DEVICE_ID = "DEVICE_ID";
    private static final String IS_EMPLOYEE = "IS_EMPLOYEE";
    private static final String USER_ID = "USER_ID";

    public static void setCriticalDataParam(Context context, String str, String str2) {
        HashSet hashSet;
        if (Build.VERSION.SDK_INT < 11) {
            BLog.w("CriticalAppData", "dropping key '%s' due to low api level", str);
        } else if (TextUtils.isEmpty(str) || str.equals(USER_ID) || str.equals(DEVICE_ID)) {
            throw new IllegalArgumentException("Invalid parameter");
        } else {
            Set<String> additionalParamKeySet = getAdditionalParamKeySet(context);
            if (additionalParamKeySet == null || additionalParamKeySet.size() <= 5) {
                SharedPreferences sharedPreferences = getSharedPreferences(context);
                if (additionalParamKeySet == null || !additionalParamKeySet.contains(str)) {
                    if (additionalParamKeySet == null) {
                        hashSet = new HashSet();
                    } else {
                        hashSet = new HashSet(additionalParamKeySet);
                    }
                    hashSet.add(str);
                    sharedPreferences.edit().putStringSet(ADDITIONAL_PARAMS, hashSet).commit();
                }
                if (!str2.equals(sharedPreferences.getString(str, BuildConfig.PROVIDER_SUFFIX))) {
                    getSharedPreferences(context).edit().putString(str, str2).commit();
                    return;
                }
                return;
            }
            BLog.w("CriticalAppData", "dropping key '%s' due to too many additional params", str);
        }
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

    public static void setUserId(Context context, @Nullable String str) {
        if (str != null && !str.equals(getUserId(context))) {
            getSharedPreferences(context).edit().putString(USER_ID, str).commit();
        }
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

    public static String getUserId(Context context) {
        return getSharedPreferences(context).getString(USER_ID, BuildConfig.PROVIDER_SUFFIX);
    }

    public static String getClientUserId(Context context) {
        return getSharedPreferences(context).getString(CLIENT_USER_ID, BuildConfig.PROVIDER_SUFFIX);
    }

    public static String getDeviceId(Context context) {
        return getSharedPreferences(context).getString(DEVICE_ID, BuildConfig.PROVIDER_SUFFIX);
    }

    public static boolean isEmployee(Context context) {
        return getSharedPreferences(context).getBoolean(IS_EMPLOYEE, false);
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(ACRA_CRITICAL_DATA_STORE, 0);
    }

    public static Map<String, String> getAdditionalParamValues(Context context) {
        HashMap hashMap = new HashMap();
        Set<String> additionalParamKeySet = getAdditionalParamKeySet(context);
        if (additionalParamKeySet != null && !additionalParamKeySet.isEmpty()) {
            SharedPreferences sharedPreferences = getSharedPreferences(context);
            for (String str : additionalParamKeySet) {
                String string = sharedPreferences.getString(str, BuildConfig.PROVIDER_SUFFIX);
                if (!TextUtils.isEmpty(string)) {
                    hashMap.put(str, string);
                }
            }
        }
        return hashMap;
    }

    @Nullable
    private static Set<String> getAdditionalParamKeySet(Context context) {
        if (Build.VERSION.SDK_INT < 11) {
            return null;
        }
        return getSharedPreferences(context).getStringSet(ADDITIONAL_PARAMS, null);
    }
}
