package com.oculus.vrshell.util;

import android.content.Context;
import com.oculus.vrshell.sharedprefs.PrefKey;
import com.oculus.vrshell.sharedprefs.SharedKeys;
import com.oculus.vrshell.sharedprefs.SharedPreferencesHelper;
import java.util.HashMap;
import java.util.Map;

public class PreferenceStringHelper {

    public static class MainActivityPrefKeys {
        public static final PrefKey MAIN_ACTIVITY_ROOT = SharedKeys.ROOT.extend("main_activity");
        public static final PrefKey NATIVE = MAIN_ACTIVITY_ROOT.extend("native");
    }

    public static String getPreferenceString(Context context, String str) {
        return SharedPreferencesHelper.getString(context, MainActivityPrefKeys.NATIVE.extend(str), "");
    }

    public static void setPreferenceString(Context context, String str, String str2) {
        SharedPreferencesHelper.putString(context, MainActivityPrefKeys.NATIVE.extend(str), str2);
    }

    public static Map<String, String> getAllStringPreferencesWithPrefix(Context context, String str) {
        PrefKey extend = MainActivityPrefKeys.NATIVE.extend(str);
        Map<String, ?> allUnder = SharedPreferencesHelper.getAllUnder(context, extend);
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, ?> entry : allUnder.entrySet()) {
            String key = entry.getKey();
            if (key.startsWith(extend.getFullKey())) {
                hashMap.put(key, (String) entry.getValue());
            }
        }
        return hashMap;
    }
}
