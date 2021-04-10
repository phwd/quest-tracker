package defpackage;

import android.os.Bundle;
import android.text.TextUtils;

/* renamed from: Mx1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class Mx1 {
    public static Gx1 a(C2617fz0 fz0) {
        if (fz0 == null) {
            return null;
        }
        return new Gx1(fz0.d, fz0.e);
    }

    public static Ix1 b(C1035Qz0 qz0) {
        if (qz0 == null) {
            return null;
        }
        return new Ix1(a(qz0.e));
    }

    public static void c(String str, String str2, Bundle bundle) {
        if (!TextUtils.isEmpty(str2)) {
            bundle.putString(str, str2);
        }
    }
}
