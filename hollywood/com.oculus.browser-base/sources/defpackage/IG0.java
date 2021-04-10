package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.AppHooks;

/* renamed from: IG0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class IG0 implements Runnable {
    public IG0(OG0 og0) {
    }

    public void run() {
        Objects.requireNonNull(AppHooks.get());
    }
}
