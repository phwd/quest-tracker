package defpackage;

import org.chromium.components.browser_ui.bottomsheet.BottomSheet;

/* renamed from: uj  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC5128uj implements Runnable {
    public final C5638xj F;

    public RunnableC5128uj(C5638xj xjVar) {
        this.F = xjVar;
    }

    public void run() {
        BottomSheet bottomSheet = this.F.F;
        if (bottomSheet.g0) {
            bottomSheet.v(bottomSheet.h(), true, 3);
        }
    }
}
