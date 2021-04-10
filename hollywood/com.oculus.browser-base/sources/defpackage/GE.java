package defpackage;

import org.chromium.ui.base.DeviceFormFactor;

/* renamed from: GE  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class GE {
    public static void a() {
        P21 f0 = P21.f0();
        try {
            if (!DeviceFormFactor.isTablet()) {
                AbstractC1575Zv.e().a("use-mobile-user-agent");
            }
            f0.close();
            return;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }
}
