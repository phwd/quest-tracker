package defpackage;

import org.chromium.chrome.browser.video_tutorials.Tutorial;

/* renamed from: zt1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC6010zt1 implements Runnable {
    public final Ft1 F;
    public final Tutorial G;

    public RunnableC6010zt1(Ft1 ft1, Tutorial tutorial) {
        this.F = ft1;
        this.G = tutorial;
    }

    public void run() {
        Ft1 ft1 = this.F;
        ft1.e.onResult(this.G);
    }
}
