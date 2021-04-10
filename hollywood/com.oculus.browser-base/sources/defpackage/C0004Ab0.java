package defpackage;

import J.N;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ThreadUtils;

/* renamed from: Ab0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0004Ab0 {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f7680a;
    public final Runnable b = new RunnableC5785yb0(this);

    static {
        ApplicationStatus.h.b(new C5955zb0());
    }

    public final void a(boolean z) {
        if (!f7680a) {
            f7680a = true;
            PU0 pu0 = NU0.f8549a;
            long currentTimeMillis = System.currentTimeMillis();
            long h = pu0.h("MainIntent.LaunchTimestamp", 0);
            int i = 0;
            int f = pu0.f("MainIntent.LaunchCount", 0);
            if (currentTimeMillis - h > 86400000) {
                if (h != 0) {
                    AbstractC3364kK0.d("MobileStartup.DailyLaunchCount", f);
                }
                pu0.o("MainIntent.LaunchTimestamp", currentTimeMillis);
            } else {
                i = f;
            }
            pu0.n("MainIntent.LaunchCount", i + 1);
            AbstractC3364kK0.g("MobileStartup.LaunchType", !z ? 1 : 0, 2);
            if (N.M09VlOh_("AndroidDefaultBrowserPromo")) {
                pu0.c("Chrome.DefaultBrowserPromo.SessionCount");
            }
            ThreadUtils.b().removeCallbacks(this.b);
        }
    }
}
