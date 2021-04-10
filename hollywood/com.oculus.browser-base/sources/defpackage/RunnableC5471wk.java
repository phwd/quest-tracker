package defpackage;

import org.chromium.content.browser.BrowserStartupControllerImpl;

/* renamed from: wk  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC5471wk implements Runnable {
    public final /* synthetic */ AbstractC4451qk F;
    public final /* synthetic */ BrowserStartupControllerImpl G;

    public RunnableC5471wk(BrowserStartupControllerImpl browserStartupControllerImpl, AbstractC4451qk qkVar) {
        this.G = browserStartupControllerImpl;
        this.F = qkVar;
    }

    public void run() {
        if (this.G.i) {
            this.F.onSuccess();
        } else {
            this.F.a();
        }
    }
}
