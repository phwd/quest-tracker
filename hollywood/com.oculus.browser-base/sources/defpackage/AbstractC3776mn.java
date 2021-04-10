package defpackage;

import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.media.router.ChromeMediaRouterClient;

/* renamed from: mn  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3776mn {
    public static C1557Zm a() {
        Objects.requireNonNull(ChromeMediaRouterClient.f10694a);
        return C1557Zm.c(ContextUtils.getApplicationContext());
    }

    public static final boolean b(String str, String str2) {
        if (str == null || str.isEmpty() || str2 == null || str2.isEmpty()) {
            return false;
        }
        return str.equals(str2);
    }
}
