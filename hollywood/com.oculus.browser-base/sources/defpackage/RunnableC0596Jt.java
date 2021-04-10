package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: Jt  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0596Jt implements Runnable {
    public final AbstractActivityC2601fu F;
    public final Tab G;

    public RunnableC0596Jt(AbstractActivityC2601fu fuVar, Tab tab) {
        this.F = fuVar;
        this.G = tab;
    }

    public void run() {
        AbstractActivityC2601fu fuVar = this.F;
        Tab tab = this.G;
        boolean z = fuVar.Q0().r(tab.getId()) != null;
        fuVar.Q0().q(tab, false, true, false);
        if (!z) {
            fuVar.Q1(9);
        }
    }
}
