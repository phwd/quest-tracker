package defpackage;

import java.util.HashMap;
import java.util.Map;
import org.chromium.chrome.browser.profiles.ProfileManager;

/* renamed from: zu0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C6012zu0 {

    /* renamed from: a  reason: collision with root package name */
    public static final Map f11778a = new HashMap();
    public static AbstractC1818bH0 b;

    public C6012zu0() {
        if (b == null) {
            C5842yu0 yu0 = new C5842yu0(this);
            b = yu0;
            ProfileManager.f10754a.b(yu0);
        }
    }
}
