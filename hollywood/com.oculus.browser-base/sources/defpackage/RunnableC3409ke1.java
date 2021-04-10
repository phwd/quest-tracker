package defpackage;

import org.chromium.chrome.browser.app.ChromeActivity;

/* renamed from: ke1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC3409ke1 implements Runnable {
    public final ChromeActivity F;

    public RunnableC3409ke1(ChromeActivity chromeActivity) {
        this.F = chromeActivity;
    }

    public void run() {
        this.F.onBackPressed();
    }
}
