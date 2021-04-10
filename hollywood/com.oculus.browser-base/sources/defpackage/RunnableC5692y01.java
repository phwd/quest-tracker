package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: y01  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC5692y01 implements Runnable {
    public final C5862z01 F;

    public RunnableC5692y01(C5862z01 z01) {
        this.F = z01;
    }

    public void run() {
        C5862z01 z01 = this.F;
        Tab j = ((AbstractC0246Ea1) z01.f11716a.L).j();
        if (j != null) {
            z01.f11716a.M.b(j);
        }
    }
}
