package defpackage;

import org.chromium.chrome.browser.toolbar.top.StartSurfaceToolbarView;

/* renamed from: a11  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC1591a11 implements Runnable {
    public final StartSurfaceToolbarView F;
    public final boolean G;

    public RunnableC1591a11(StartSurfaceToolbarView startSurfaceToolbarView, boolean z) {
        this.F = startSurfaceToolbarView;
        this.G = z;
    }

    public void run() {
        this.F.a(this.G);
    }
}
