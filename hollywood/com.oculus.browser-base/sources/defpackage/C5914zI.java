package defpackage;

import java.util.List;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;

/* renamed from: zI  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5914zI {

    /* renamed from: a  reason: collision with root package name */
    public static C5914zI f11738a;

    public static C5914zI b() {
        if (f11738a == null) {
            f11738a = new C5914zI();
        }
        return f11738a;
    }

    public void a() {
        AbstractC2898hf.b().a(ContextUtils.getApplicationContext(), 55);
    }

    public void c() {
        boolean z;
        if (!CachedFeatureFlags.isEnabled("DownloadsAutoResumptionNative")) {
            List list = DI.f7880a.f7952a;
            int i = 0;
            boolean z2 = false;
            while (true) {
                if (i >= list.size()) {
                    z = false;
                    break;
                }
                BI bi = (BI) list.get(i);
                if (bi.f) {
                    if (bi.d) {
                        z = true;
                        z2 = true;
                        break;
                    }
                    z2 = true;
                }
                i++;
            }
            if (z2) {
                int i2 = z ? 1 : 2;
                C1294Ve1 ve1 = new C1294Ve1();
                ve1.b = 86400000;
                C1355We1 a2 = ve1.a();
                C1111Se1 se1 = new C1111Se1(55);
                se1.g = a2;
                se1.f = true;
                se1.c = i2;
                se1.d = false;
                se1.e = true;
                AbstractC2898hf.b().b(ContextUtils.getApplicationContext(), se1.a());
                return;
            }
            a();
        }
    }
}
