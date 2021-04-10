package defpackage;

import org.chromium.chrome.browser.omnibox.status.StatusView;

/* renamed from: C21  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C21 implements Runnable {
    public final StatusView F;

    public C21(StatusView statusView) {
        this.F = statusView;
    }

    public void run() {
        StatusView statusView = this.F;
        statusView.P = false;
        statusView.b();
    }
}
