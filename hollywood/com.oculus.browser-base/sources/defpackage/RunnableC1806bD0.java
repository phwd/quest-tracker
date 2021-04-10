package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.WebContents;

/* renamed from: bD0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC1806bD0 implements Runnable {
    public final C2831hD0 F;
    public final Tab G;
    public final AbstractC1404Xa1 H;
    public final WebContents I;

    /* renamed from: J  reason: collision with root package name */
    public final AbstractC6022zx1 f9521J;
    public final TT K;
    public final W2 L;

    public RunnableC1806bD0(C2831hD0 hd0, Tab tab, AbstractC1404Xa1 xa1, WebContents webContents, AbstractC6022zx1 zx1, TT tt, W2 w2) {
        this.F = hd0;
        this.G = tab;
        this.H = xa1;
        this.I = webContents;
        this.f9521J = zx1;
        this.K = tt;
        this.L = w2;
    }

    public void run() {
        this.F.j(this.G, this.H, this.I, this.f9521J, this.K, this.L);
    }
}
