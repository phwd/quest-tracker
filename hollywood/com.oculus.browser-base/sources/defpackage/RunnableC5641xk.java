package defpackage;

import J.N;
import org.chromium.content.browser.BrowserStartupControllerImpl;

/* renamed from: xk  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC5641xk implements Runnable {
    public final /* synthetic */ boolean F;
    public final /* synthetic */ Runnable G;
    public final /* synthetic */ BrowserStartupControllerImpl H;

    public RunnableC5641xk(BrowserStartupControllerImpl browserStartupControllerImpl, boolean z, Runnable runnable) {
        this.H = browserStartupControllerImpl;
        this.F = z;
        this.G = runnable;
    }

    public void run() {
        if (!this.H.f) {
            GE.a();
            N.MwoPtAzD(this.F);
            this.H.f = true;
        }
        Runnable runnable = this.G;
        if (runnable != null) {
            runnable.run();
        }
    }
}
