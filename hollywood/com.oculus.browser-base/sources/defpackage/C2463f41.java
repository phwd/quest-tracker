package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.AppHooks;

/* renamed from: f41  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2463f41 {

    /* renamed from: a  reason: collision with root package name */
    public static C2463f41 f9895a;

    public static C2463f41 a() {
        if (f9895a == null) {
            Objects.requireNonNull(AppHooks.get());
            f9895a = new C2463f41();
        }
        return f9895a;
    }
}
