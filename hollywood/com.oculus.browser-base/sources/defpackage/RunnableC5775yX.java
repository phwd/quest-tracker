package defpackage;

import org.chromium.chrome.browser.compositor.CompositorViewHolder;

/* renamed from: yX  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC5775yX implements Runnable {
    public final FX F;
    public final CompositorViewHolder G;

    public RunnableC5775yX(FX fx, CompositorViewHolder compositorViewHolder) {
        this.F = fx;
        this.G = compositorViewHolder;
    }

    public void run() {
        FX fx = this.F;
        CompositorViewHolder compositorViewHolder = this.G;
        compositorViewHolder.G.b(fx.S);
    }
}
