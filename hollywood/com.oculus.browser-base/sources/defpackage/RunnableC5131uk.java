package defpackage;

import org.chromium.base.ThreadUtils;
import org.chromium.content.browser.BrowserStartupControllerImpl;

/* renamed from: uk  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC5131uk implements Runnable {
    public final /* synthetic */ boolean F;
    public final /* synthetic */ BrowserStartupControllerImpl G;

    public RunnableC5131uk(BrowserStartupControllerImpl browserStartupControllerImpl, boolean z) {
        this.G = browserStartupControllerImpl;
        this.F = z;
    }

    public void run() {
        Object obj = ThreadUtils.f10596a;
        BrowserStartupControllerImpl browserStartupControllerImpl = this.G;
        if (!browserStartupControllerImpl.g) {
            browserStartupControllerImpl.j = this.F ? 1 : 0;
            if (browserStartupControllerImpl.b() > 0) {
                this.G.c(1);
            }
        }
    }
}
