package com.facebook.acra.criticaldata;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

@SuppressLint({"SharedPreferencesUse"})
public final class CriticalAppData {
    public static String getUserId(Context ctx) {
        return getSharedPreferences(ctx).getString("USER_ID", "");
    }

    public static String getClientUserId(Context ctx) {
        return getSharedPreferences(ctx).getString("CLIENT_USER_ID", "");
    }

    public static String getDeviceId(Context ctx) {
        return getSharedPreferences(ctx).getString("DEVICE_ID", "");
    }

    private static SharedPreferences getSharedPreferences(Context ctx) {
        return ctx.getSharedPreferences("acra_criticaldata_store", 0);
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
        return getSharedPreferences(ctx).getStringSet("ADDITIONAL_PARAMS", null);
    }
}
