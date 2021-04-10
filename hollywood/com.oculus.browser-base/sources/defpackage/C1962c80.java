package defpackage;

import J.N;
import java.util.Map;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.profiles.ProfileManager;

/* renamed from: c80  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1962c80 implements AbstractC1818bH0 {
    public C1962c80(C2133d80 d80) {
    }

    @Override // defpackage.AbstractC1818bH0
    public void f(Profile profile) {
    }

    @Override // defpackage.AbstractC1818bH0
    public void i(Profile profile) {
        Map map = C2133d80.f9751a;
        C1791b80 b80 = (C1791b80) map.get(profile);
        if (b80 != null) {
            N.MJWrJ5q6(b80.f9515a.f10776a);
            map.remove(profile);
        }
        if (map.isEmpty()) {
            ProfileManager.f10754a.c(C2133d80.b);
            C2133d80.b = null;
        }
    }
}
