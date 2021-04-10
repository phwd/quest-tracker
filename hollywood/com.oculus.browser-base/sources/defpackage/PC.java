package defpackage;

import J.N;
import org.chromium.chrome.browser.net.spdyproxy.DataReductionProxySettings;

/* renamed from: PC  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class PC {
    public static boolean a() {
        DataReductionProxySettings d = DataReductionProxySettings.d();
        if (N.M2eLfdI_(d.c, d) && !DataReductionProxySettings.d().f() && !DataReductionProxySettings.d().e()) {
            return true;
        }
        return false;
    }

    public static void b() {
        PU0 pu0 = NU0.f8549a;
        pu0.m("displayed_data_reduction_promo", true);
        pu0.o("displayed_data_reduction_promo_time_ms", System.currentTimeMillis());
        pu0.p("displayed_data_reduction_promo_version", N.MMSdy2S5());
    }

    public static void c(long j) {
        NU0.f8549a.o("displayed_data_reduction_snackbar_promo_saved_bytes", j);
    }
}
