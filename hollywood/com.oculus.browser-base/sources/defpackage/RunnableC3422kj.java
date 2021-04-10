package defpackage;

import org.chromium.components.browser_ui.bottomsheet.BottomSheet;

/* renamed from: kj  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC3422kj implements Runnable {
    public final Throwable F;

    public RunnableC3422kj(Throwable th) {
        this.F = th;
    }

    public void run() {
        BottomSheet.F.onResult(this.F);
    }
}
