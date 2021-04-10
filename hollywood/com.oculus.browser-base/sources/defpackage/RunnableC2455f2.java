package defpackage;

import androidx.appcompat.widget.ActionBarOverlayLayout;

/* renamed from: f2  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC2455f2 implements Runnable {
    public final /* synthetic */ ActionBarOverlayLayout F;

    public RunnableC2455f2(ActionBarOverlayLayout actionBarOverlayLayout) {
        this.F = actionBarOverlayLayout;
    }

    public void run() {
        this.F.i();
        ActionBarOverlayLayout actionBarOverlayLayout = this.F;
        actionBarOverlayLayout.j0 = actionBarOverlayLayout.f9459J.animate().translationY(0.0f).setListener(this.F.k0);
    }
}
