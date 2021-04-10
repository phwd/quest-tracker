package X;

import android.content.Context;
import android.preference.PreferenceManager;

/* renamed from: X.8p  reason: invalid class name and case insensitive filesystem */
public final class C00638p {
    public static void A00(Context context, String str) {
        PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()).edit().remove(str).apply();
    }

    public static void A01(Context context, String str, long j) {
        PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()).edit().putLong(str, j).apply();
    }

    public static void A02(Context context, String str, boolean z) {
        PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()).edit().putBoolean(str, z).apply();
    }
}
