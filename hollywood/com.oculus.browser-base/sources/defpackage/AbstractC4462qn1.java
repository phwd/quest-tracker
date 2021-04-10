package defpackage;

import android.os.Build;

/* renamed from: qn1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4462qn1 {

    /* renamed from: a  reason: collision with root package name */
    public static final boolean f11164a = true;
    public static final boolean b = true;
    public static final boolean c;

    static {
        int i = Build.VERSION.SDK_INT;
        boolean z = true;
        if (i < 28) {
            z = false;
        }
        c = z;
    }
}
