package defpackage;

import org.chromium.chrome.browser.compositor.CompositorViewHolder;

/* renamed from: zX  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC5945zX implements Runnable {
    public final FX F;
    public final CompositorViewHolder G;

    public RunnableC5945zX(FX fx, CompositorViewHolder compositorViewHolder) {
        this.F = fx;
        this.G = compositorViewHolder;
    }

    public void run() {
        FX fx = this.F;
        CompositorViewHolder compositorViewHolder = this.G;
        compositorViewHolder.removeCallbacks(fx.F);
        C0887Om0 om0 = fx.S;
        if (om0 != null) {
            compositorViewHolder.G.c(om0);
        }
    }
}
