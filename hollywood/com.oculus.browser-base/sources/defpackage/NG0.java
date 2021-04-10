package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.AppHooks;

/* renamed from: NG0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NG0 implements Runnable {
    public NG0(OG0 og0) {
    }

    public void run() {
        AbstractC2022cW0.a().c();
        if (C1653aN0.f9427a == null) {
            Objects.requireNonNull(AppHooks.get());
            C1653aN0.f9427a = new C1653aN0();
        }
    }
}
