package defpackage;

import org.chromium.components.browser_ui.bottomsheet.BottomSheet;

/* renamed from: rj  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC4618rj implements Runnable {
    public final C5638xj F;

    public RunnableC4618rj(C5638xj xjVar) {
        this.F = xjVar;
    }

    public void run() {
        BottomSheet bottomSheet;
        int i;
        C5638xj xjVar = this.F;
        if (!xjVar.N.b() && (bottomSheet = xjVar.F) != null) {
            if (bottomSheet.a0 != null) {
                if (xjVar.L == xjVar.n()) {
                    i = xjVar.K;
                } else {
                    i = xjVar.F.j();
                }
                xjVar.F.v(i, true, 0);
            } else {
                xjVar.v(true);
            }
            xjVar.L = null;
            xjVar.K = -1;
        }
    }
}
