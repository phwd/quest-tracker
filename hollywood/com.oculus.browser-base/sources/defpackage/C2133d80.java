package defpackage;

import java.util.HashMap;
import java.util.Map;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.profiles.ProfileManager;

/* renamed from: d80  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2133d80 implements AbstractC3682mC0 {

    /* renamed from: a  reason: collision with root package name */
    public static final Map f9751a = new HashMap();
    public static AbstractC1818bH0 b;

    public C2133d80() {
        if (b == null) {
            C1962c80 c80 = new C1962c80(this);
            b = c80;
            ProfileManager.f10754a.b(c80);
        }
    }

    @Override // defpackage.AbstractC3682mC0
    public AbstractC3511lC0 a() {
        Profile b2 = Profile.b();
        Map map = f9751a;
        C1791b80 b80 = (C1791b80) map.get(b2);
        if (b80 != null) {
            return b80;
        }
        C1791b80 b802 = new C1791b80(b2);
        map.put(b2, b802);
        return b802;
    }
}
