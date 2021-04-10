package defpackage;

import org.chromium.components.browser_ui.widget.ViewResourceFrameLayout;

/* renamed from: W11  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class W11 implements Runnable {
    public final ViewResourceFrameLayout F;

    public W11(ViewResourceFrameLayout viewResourceFrameLayout) {
        this.F = viewResourceFrameLayout;
    }

    public void run() {
        this.F.requestLayout();
    }
}
