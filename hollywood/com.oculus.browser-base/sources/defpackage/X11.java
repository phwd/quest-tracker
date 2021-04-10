package defpackage;

import org.chromium.components.browser_ui.widget.ViewResourceFrameLayout;

/* renamed from: X11  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class X11 implements Runnable {
    public final Z11 F;
    public final ViewResourceFrameLayout G;

    public X11(Z11 z11, ViewResourceFrameLayout viewResourceFrameLayout) {
        this.F = z11;
        this.G = viewResourceFrameLayout;
    }

    public void run() {
        this.G.removeOnLayoutChangeListener(this.F.f9314a);
    }
}
