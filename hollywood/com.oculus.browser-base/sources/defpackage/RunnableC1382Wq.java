package defpackage;

import J.N;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.provider.ChromeBrowserProviderImpl;

/* renamed from: Wq  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC1382Wq implements Runnable {
    public final ChromeBrowserProviderImpl F;

    public RunnableC1382Wq(ChromeBrowserProviderImpl chromeBrowserProviderImpl) {
        this.F = chromeBrowserProviderImpl;
    }

    public void run() {
        ChromeBrowserProviderImpl chromeBrowserProviderImpl = this.F;
        if (chromeBrowserProviderImpl.i == 0) {
            C1321Vq.b().e();
            Object obj = ThreadUtils.f10596a;
            if (chromeBrowserProviderImpl.i == 0) {
                chromeBrowserProviderImpl.i = N.MfoP08MA(chromeBrowserProviderImpl);
            }
        }
    }
}
