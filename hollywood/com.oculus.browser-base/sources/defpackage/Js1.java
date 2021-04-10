package defpackage;

import java.util.Locale;

/* renamed from: Js1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Js1 {

    /* renamed from: a  reason: collision with root package name */
    public final int[] f8320a = {0, 0, 0, 0};

    public static Js1 a(String str) {
        if (str == null) {
            return null;
        }
        String[] split = str.split("\\.");
        if (split.length != 4) {
            return null;
        }
        Js1 js1 = new Js1();
        for (int i = 0; i < 4; i++) {
            try {
                js1.f8320a[i] = Integer.parseInt(split[i]);
            } catch (NumberFormatException unused) {
                return null;
            }
        }
        return js1;
    }

    public boolean b(Js1 js1) {
        for (int i = 0; i < 4; i++) {
            int[] iArr = this.f8320a;
            int i2 = iArr[i];
            int[] iArr2 = js1.f8320a;
            if (i2 < iArr2[i]) {
                return true;
            }
            if (iArr[i] > iArr2[i]) {
                return false;
            }
        }
        return false;
    }

    public String toString() {
        return String.format(Locale.US, "%d.%d.%d.%d", Integer.valueOf(this.f8320a[0]), Integer.valueOf(this.f8320a[1]), Integer.valueOf(this.f8320a[2]), Integer.valueOf(this.f8320a[3]));
    }
}
