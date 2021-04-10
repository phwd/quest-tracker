package defpackage;

import java.util.HashSet;
import java.util.Set;

/* renamed from: Hs1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class Hs1 {
    public static Set a() {
        P21 f0 = P21.f0();
        try {
            HashSet hashSet = new HashSet(NU0.f8549a.j("verified_digital_asset_links"));
            f0.close();
            return hashSet;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    public static void b(Set set) {
        NU0.f8549a.q("verified_digital_asset_links", set);
    }
}
