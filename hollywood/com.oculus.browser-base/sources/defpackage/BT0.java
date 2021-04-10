package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: BT0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class BT0 implements Runnable {
    public final GT0 F;
    public final Tab G;
    public final int H;
    public final boolean I;

    /* renamed from: J  reason: collision with root package name */
    public final boolean f7738J;

    public BT0(GT0 gt0, Tab tab, int i, boolean z, boolean z2) {
        this.F = gt0;
        this.G = tab;
        this.H = i;
        this.I = z;
        this.f7738J = z2;
    }

    public void run() {
        this.F.d(this.G, this.H, this.I, this.f7738J);
    }
}
