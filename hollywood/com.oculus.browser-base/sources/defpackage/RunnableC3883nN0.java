package defpackage;

import org.chromium.chrome.browser.app.ChromeActivity;

/* renamed from: nN0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC3883nN0 implements Runnable {
    public final ChromeActivity F;

    public RunnableC3883nN0(ChromeActivity chromeActivity) {
        this.F = chromeActivity;
    }

    public void run() {
        this.F.onBackPressed();
    }
}
