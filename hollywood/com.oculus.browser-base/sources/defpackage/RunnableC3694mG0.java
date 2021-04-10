package defpackage;

import J.N;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.policy.EnterpriseInfo;
import org.chromium.components.policy.PolicyService;

/* renamed from: mG0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC3694mG0 implements Runnable {
    public void run() {
        Object obj = ThreadUtils.f10596a;
        if (NU0.f8549a.d("Chrome.FirstRun.SkippedByPolicy", false)) {
            RQ rq = RQ.f8829a;
            if (rq == null) {
                rq = new RQ();
            } else {
                RQ.f8829a = null;
            }
            C1570Zs0 zs0 = new C1570Zs0();
            zs0.a((PolicyService) N.MXHPjU6q());
            new C4629rm1(new WX0(rq, zs0, EnterpriseInfo.b(), null), rq);
        }
    }
}
