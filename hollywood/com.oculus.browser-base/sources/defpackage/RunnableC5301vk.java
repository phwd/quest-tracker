package defpackage;

import org.chromium.content.browser.BrowserStartupControllerImpl;

/* renamed from: vk  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC5301vk implements Runnable {
    public final /* synthetic */ int F;
    public final /* synthetic */ BrowserStartupControllerImpl G;

    public RunnableC5301vk(BrowserStartupControllerImpl browserStartupControllerImpl, int i) {
        this.G = browserStartupControllerImpl;
        this.F = i;
    }

    public void run() {
        this.G.d(this.F);
    }
}
