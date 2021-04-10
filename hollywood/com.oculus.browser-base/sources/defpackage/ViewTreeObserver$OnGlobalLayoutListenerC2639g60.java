package defpackage;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;

/* renamed from: g60  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ViewTreeObserver$OnGlobalLayoutListenerC2639g60 implements ViewTreeObserver.OnGlobalLayoutListener {
    public final View F;
    public final Runnable G;
    public final Runnable H = new RunnableC2468f60(this);
    public final Rect I = new Rect();

    /* renamed from: J  reason: collision with root package name */
    public Uy1 f9977J;
    public boolean K;
    public int L;

    public ViewTreeObserver$OnGlobalLayoutListenerC2639g60(View view, Runnable runnable) {
        this.F = view;
        this.G = runnable;
    }

    public final int a() {
        Uy1 uy1 = this.f9977J;
        if (uy1 == null) {
            return this.F.getRootView().getHeight();
        }
        uy1.a(this.I);
        return Math.min(this.I.height(), this.f9977J.f9058a.getDecorView().getHeight());
    }

    public final void b() {
        if (this.K) {
            this.F.removeCallbacks(this.H);
            this.F.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            this.K = false;
        }
    }

    public void onGlobalLayout() {
        if (a() > this.L) {
            this.G.run();
            b();
        }
    }
}
