package defpackage;

import org.chromium.chrome.browser.video_tutorials.Tutorial;

/* renamed from: At1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class At1 implements Runnable {
    public final Ft1 F;
    public final Tutorial G;

    public At1(Ft1 ft1, Tutorial tutorial) {
        this.F = ft1;
        this.G = tutorial;
    }

    public void run() {
        Ft1 ft1 = this.F;
        ft1.f.onResult(this.G);
    }
}
