package com.oculus.vrshell.sharedprefs;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Map;
import java.util.Set;

public class SharedPreferencesHelper {
    public static final String SHARED_PREFERENCES_FILE = "vrshell_prefs";
    public static final String SHARED_PREFERENCES_FLAGS_FILE = "vrshell_flags";
    public static final String SHARED_PREFERENCES_SETTINGS_FILE = "vrshell_settings";

    public static SharedPreferences getSharedPreferences(Context context, PrefKey prefKey) {
        String str;
        if (prefKey.startsWith(SharedKeys.FLAGS)) {
            str = SHARED_PREFERENCES_FLAGS_FILE;
        } else if (prefKey.startsWith(SharedKeys.SETTINGS)) {
            str = SHARED_PREFERENCES_SETTINGS_FILE;
        } else {
            str = SHARED_PREFERENCES_FILE;
        }
        return context.getSharedPreferences(str, 0);
    }

    public static void clearAllPreferencesInScope(Context context, PrefKey prefKey) {
        getEditor(context, prefKey).clear().commit();
    }

    public static void clearPreference(Context context, PrefKey prefKey) {
        getEditor(context, prefKey).remove(prefKey.getFullKey()).commit();
    }

    public static Map<String, ?> getAllUnder(Context context, PrefKey prefKey) {
        return getSharedPreferences(context, prefKey).getAll();
    }

    public static boolean getBoolean(Context context, PrefKey prefKey, boolean z) {
        return getSharedPreferences(context, prefKey).getBoolean(prefKey.getFullKey(), z);
    }

    public static SharedPreferences.Editor getEditor(Context context, PrefKey prefKey) {
        return getSharedPreferences(context, prefKey).edit();
    }

    public static int getInt(Context context, PrefKey prefKey, int i) {
        return getSharedPreferences(context, prefKey).getInt(prefKey.getFullKey(), i);
    }

    public static long getLong(Context context, PrefKey prefKey, long j) {
        return getSharedPreferences(context, prefKey).getLong(prefKey.getFullKey(), j);
    }

    public static String getString(Context context, PrefKey prefKey, String str) {
        return getSharedPreferences(context, prefKey).getString(prefKey.getFullKey(), str);
    }

    public static Set<String> getStringSet(Context context, PrefKey prefKey, Set<String> set) {
        return getSharedPreferences(context, prefKey).getStringSet(prefKey.getFullKey(), set);
    }

    public static void putBoolean(Context context, PrefKey prefKey, boolean z) {
        getEditor(context, prefKey).putBoolean(prefKey.getFullKey(), z).commit();
    }

    public static void putInt(Context context, PrefKey prefKey, int i) {
        getEditor(context, prefKey).putInt(prefKey.getFullKey(), i).commit();
    }

    public static void putLong(Context context, PrefKey prefKey, long j) {
        getEditor(context, prefKey).putLong(prefKey.getFullKey(), j).commit();
    }

    public static void putString(Context context, PrefKey prefKey, String str) {
        getEditor(context, prefKey).putString(prefKey.getFullKey(), str).commit();
    }

    public static void putStringSet(Context context, PrefKey prefKey, Set<String> set) {
        getEditor(context, prefKey).putStringSet(prefKey.getFullKey(), set).commit();
    }
}
