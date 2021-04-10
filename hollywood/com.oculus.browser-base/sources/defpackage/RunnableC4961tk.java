package defpackage;

import org.chromium.content.browser.BrowserStartupControllerImpl;

/* renamed from: tk  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC4961tk implements Runnable {
    public final /* synthetic */ BrowserStartupControllerImpl F;

    public RunnableC4961tk(BrowserStartupControllerImpl browserStartupControllerImpl) {
        this.F = browserStartupControllerImpl;
    }

    public void run() {
        this.F.a(new C4791sk(this));
    }
}
