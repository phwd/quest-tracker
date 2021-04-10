package defpackage;

import J.N;
import org.chromium.chrome.browser.compositor.CompositorView;

/* renamed from: Hw  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC0480Hw implements Runnable {
    public final /* synthetic */ SurfaceHolder$Callback2C0723Lw F;

    public RunnableC0480Hw(SurfaceHolder$Callback2C0723Lw lw) {
        this.F = lw;
    }

    public void run() {
        SurfaceHolder$Callback2C0723Lw lw = this.F;
        C0663Kw kw = lw.H;
        if (kw != null) {
            AbstractC0358Fw fw = lw.f8449J;
            kw.b().getSurface();
            CompositorView compositorView = (CompositorView) fw;
            long j = compositorView.K;
            if (j != 0) {
                N.MyANQhkH(j, compositorView);
            }
            SurfaceHolder$Callback2C0723Lw lw2 = this.F;
            lw2.H = null;
            lw2.c(kw);
        }
    }
}
