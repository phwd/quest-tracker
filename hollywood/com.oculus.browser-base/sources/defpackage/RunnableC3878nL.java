package defpackage;

import org.chromium.base.Callback;
import org.chromium.chrome.browser.policy.EnterpriseInfo;

/* renamed from: nL  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC3878nL implements Runnable {
    public final EnterpriseInfo F;
    public final Callback G;

    public RunnableC3878nL(EnterpriseInfo enterpriseInfo, Callback callback) {
        this.F = enterpriseInfo;
        this.G = callback;
    }

    public void run() {
        this.G.onResult(this.F.c);
    }
}
