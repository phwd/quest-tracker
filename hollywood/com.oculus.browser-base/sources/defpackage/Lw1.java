package defpackage;

import android.os.Bundle;

/* renamed from: Lw1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class Lw1 {
    public static long a(Bundle bundle, String str, long j) {
        String string = bundle.getString(str);
        if (string != null && string.endsWith("L")) {
            try {
                return Long.parseLong(string.substring(0, string.length() - 1));
            } catch (NumberFormatException unused) {
            }
        }
        return j;
    }
}
