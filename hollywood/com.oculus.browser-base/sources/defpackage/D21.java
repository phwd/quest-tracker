package defpackage;

import org.chromium.chrome.browser.omnibox.status.StatusView;

/* renamed from: D21  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class D21 implements Runnable {
    public final StatusView F;

    public D21(StatusView statusView) {
        this.F = statusView;
    }

    public void run() {
        StatusView statusView = this.F;
        statusView.K.setVisibility(8);
        statusView.Q = false;
        statusView.d();
        statusView.b();
    }
}
