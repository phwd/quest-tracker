package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.AppHooks;

/* renamed from: X70  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class X70 {

    /* renamed from: a  reason: collision with root package name */
    public static X70 f9196a;

    public static X70 a() {
        if (f9196a == null) {
            Objects.requireNonNull(AppHooks.get());
            f9196a = new X70();
        }
        return f9196a;
    }
}
