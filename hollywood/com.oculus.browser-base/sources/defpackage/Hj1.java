package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: Hj1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Hj1 implements Runnable {
    public final Oj1 F;

    public Hj1(Oj1 oj1) {
        this.F = oj1;
    }

    public void run() {
        Oj1 oj1 = this.F;
        oj1.g((Tab) oj1.P.get(), "IPH_DownloadPageScreenshot");
        C5760yP0 V = C5760yP0.V((Tab) oj1.P.get());
        if (V != null) {
            AbstractC3535lK0.a("Tab.Screenshot");
            V.F++;
        }
    }
}
