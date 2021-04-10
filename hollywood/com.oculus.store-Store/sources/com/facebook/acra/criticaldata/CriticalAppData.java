package com.facebook.acra.criticaldata;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import com.facebook.debug.log.BLog;
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

    public static void setCriticalDataParam(Context ctx, String paramKey, String paramValue) {
        Set<String> newParamSet;
        if (Build.VERSION.SDK_INT < 11) {
            BLog.w("CriticalAppData", "dropping key '%s' due to low api level", paramKey);
        } else if (TextUtils.isEmpty(paramKey) || paramKey.equals(USER_ID) || paramKey.equals(DEVICE_ID)) {
            throw new IllegalArgumentException("Invalid parameter");
        } else {
            Set<String> paramKeys = getAdditionalParamKeySet(ctx);
            if (paramKeys == null || paramKeys.size() <= 5) {
                SharedPreferences sharedPreferences = getSharedPreferences(ctx);
                if (paramKeys == null || !paramKeys.contains(paramKey)) {
                    if (paramKeys == null) {
                        newParamSet = new HashSet<>();
                    } else {
                        newParamSet = new HashSet<>(paramKeys);
                    }
                    newParamSet.add(paramKey);
                    sharedPreferences.edit().putStringSet(ADDITIONAL_PARAMS, newParamSet).commit();
                }
                if (!paramValue.equals(sharedPreferences.getString(paramKey, ""))) {
                    getSharedPreferences(ctx).edit().putString(paramKey, paramValue).commit();
                    return;
                }
                return;
            }
            BLog.w("CriticalAppData", "dropping key '%s' due to too many additional params", paramKey);
        }
    }

    public static void setUserAndDeviceId(Context ctx, String userId, String deviceId) {
        if (userId != null && deviceId != null) {
            if (!userId.equals(getUserId(ctx)) || !deviceId.equals(getDeviceId(ctx))) {
                getSharedPreferences(ctx).edit().putString(USER_ID, userId).putString(DEVICE_ID, deviceId).commit();
            }
        }
    }

    public static void setUserAndDeviceId(Context ctx, String userId, String deviceId, boolean isEmployee) {
        if (userId != null && deviceId != null) {
            if (!userId.equals(getUserId(ctx)) || !deviceId.equals(getDeviceId(ctx)) || isEmployee != isEmployee(ctx)) {
                getSharedPreferences(ctx).edit().putString(USER_ID, userId).putString(DEVICE_ID, deviceId).putBoolean(IS_EMPLOYEE, isEmployee).commit();
            }
        }
    }

    public static void setUserId(Context ctx, @Nullable String userId) {
        if (userId != null && !userId.equals(getUserId(ctx))) {
            getSharedPreferences(ctx).edit().putString(USER_ID, userId).commit();
        }
    }

    public static void setClientUserId(Context ctx, @Nullable String userId) {
        if (userId != null && !userId.equals(getUserId(ctx))) {
            getSharedPreferences(ctx).edit().putString(CLIENT_USER_ID, userId).commit();
        }
    }

    public static void setDeviceId(Context ctx, @Nullable String deviceId) {
        if (deviceId != null && !deviceId.equals(getDeviceId(ctx))) {
            getSharedPreferences(ctx).edit().putString(DEVICE_ID, deviceId).commit();
        }
    }

    public static String getUserId(Context ctx) {
        return getSharedPreferences(ctx).getString(USER_ID, "");
    }

    public static String getClientUserId(Context ctx) {
        return getSharedPreferences(ctx).getString(CLIENT_USER_ID, "");
    }

    public static String getDeviceId(Context ctx) {
        return getSharedPreferences(ctx).getString(DEVICE_ID, "");
    }

    public static boolean isEmployee(Context ctx) {
        return getSharedPreferences(ctx).getBoolean(IS_EMPLOYEE, false);
    }

    private static SharedPreferences getSharedPreferences(Context ctx) {
        return ctx.getSharedPreferences(ACRA_CRITICAL_DATA_STORE, 0);
    }

    public static Map<String, String> getAdditionalParamValues(Context ctx) {
        Map<String, String> map = new HashMap<>();
        Set<String> paramKeys = getAdditionalParamKeySet(ctx);
        if (paramKeys != null && !paramKeys.isEmpty()) {
            SharedPreferences sharedPreferences = getSharedPreferences(ctx);
            for (String eachParam : paramKeys) {
                String value = sharedPreferences.getString(eachParam, "");
                if (!TextUtils.isEmpty(value)) {
                    map.put(eachParam, value);
                }
            }
        }
        return map;
    }

    @Nullable
    private static Set<String> getAdditionalParamKeySet(Context ctx) {
        if (Build.VERSION.SDK_INT < 11) {
            return null;
        }
        return getSharedPreferences(ctx).getStringSet(ADDITIONAL_PARAMS, null);
    }
}
