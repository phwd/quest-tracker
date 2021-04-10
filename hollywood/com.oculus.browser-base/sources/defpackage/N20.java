package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.AppHooks;

/* renamed from: N20  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class N20 {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f8522a = new Object();
    public static N20 b;
    public static final String[] c = {"com.google.android.instantapps.START", "com.google.android.instantapps.nmr1.INSTALL", "com.google.android.instantapps.nmr1.VIEW"};

    public static N20 a() {
        synchronized (f8522a) {
            if (b == null) {
                Objects.requireNonNull(AppHooks.get());
                b = new N20();
            }
        }
        return b;
    }
}
