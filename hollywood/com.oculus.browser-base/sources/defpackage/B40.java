package defpackage;

import android.util.Log;
import com.oculus.vrapi.SystemProps;

/* renamed from: B40  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class B40 {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f7714a;

    static {
        Log.i("JavaHelper", "initializing JavaHelper");
        boolean z = true;
        if (!SystemProps.a("debug.oculus.browser.jchecks", true)) {
            z = false;
        }
        f7714a = z;
    }

    public static void a(boolean z, String str) {
        if (z) {
            return;
        }
        if (!f7714a) {
            Log.e("JavaHelper", str);
            return;
        }
        throw new AssertionError(str);
    }
}
