package defpackage;

import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.metrics.PageLoadMetrics;

/* renamed from: k11  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC3308k11 implements Runnable {
    public final AbstractC0601Jv0 F;

    public RunnableC3308k11(AbstractC0601Jv0 jv0) {
        this.F = jv0;
    }

    public void run() {
        AbstractC0601Jv0 jv0 = this.F;
        Object obj = ThreadUtils.f10596a;
        C1322Vq0 vq0 = PageLoadMetrics.f10695a;
        if (vq0 != null) {
            vq0.c(jv0);
        }
    }
}
