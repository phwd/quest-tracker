package defpackage;

import android.text.TextUtils;

/* renamed from: U70  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class U70 {
    public static C0788My a(boolean z, String str) {
        return new C0788My(z ? "LEGACY_OFFLINE_PAGE" : "LEGACY_DOWNLOAD", str);
    }

    public static boolean b(C0788My my) {
        String str;
        return (my == null || (str = my.f8514a) == null || !str.startsWith("LEGACY_DOWNLOAD")) ? false : true;
    }

    public static boolean c(C0788My my) {
        return my != null && TextUtils.equals("LEGACY_OFFLINE_PAGE", my.f8514a);
    }
}
