package defpackage;

import java.util.Map;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.profiles.ProfileManager;

/* renamed from: yu0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5842yu0 implements AbstractC1818bH0 {
    public C5842yu0(C6012zu0 zu0) {
    }

    @Override // defpackage.AbstractC1818bH0
    public void f(Profile profile) {
    }

    @Override // defpackage.AbstractC1818bH0
    public void i(Profile profile) {
        Map map = C6012zu0.f11778a;
        if (((C5502wu0) map.get(profile)) != null) {
            map.remove(profile);
        }
        if (map.isEmpty()) {
            ProfileManager.f10754a.c(C6012zu0.b);
            C6012zu0.b = null;
        }
    }
}
