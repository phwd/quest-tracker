package defpackage;

import android.graphics.Rect;
import android.view.View;

/* renamed from: iT  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class RunnableC3040iT implements Runnable {
    public final /* synthetic */ AbstractComponentCallbacksC3550lS F;
    public final /* synthetic */ AbstractComponentCallbacksC3550lS G;
    public final /* synthetic */ boolean H;
    public final /* synthetic */ C4931ta I;

    /* renamed from: J  reason: collision with root package name */
    public final /* synthetic */ View f10140J;
    public final /* synthetic */ AbstractC5257vT K;
    public final /* synthetic */ Rect L;

    public RunnableC3040iT(AbstractComponentCallbacksC3550lS lSVar, AbstractComponentCallbacksC3550lS lSVar2, boolean z, C4931ta taVar, View view, AbstractC5257vT vTVar, Rect rect) {
        this.F = lSVar;
        this.G = lSVar2;
        this.H = z;
        this.I = taVar;
        this.f10140J = view;
        this.K = vTVar;
        this.L = rect;
    }

    public void run() {
        AbstractC3552lT.c(this.F, this.G, this.H, this.I, false);
        View view = this.f10140J;
        if (view != null) {
            this.K.j(view, this.L);
        }
    }
}
