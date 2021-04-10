package defpackage;

import android.graphics.Rect;
import android.view.View;
import java.util.ArrayList;

/* renamed from: jT  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class RunnableC3210jT implements Runnable {
    public final /* synthetic */ AbstractC5257vT F;
    public final /* synthetic */ C4931ta G;
    public final /* synthetic */ Object H;
    public final /* synthetic */ C3381kT I;

    /* renamed from: J  reason: collision with root package name */
    public final /* synthetic */ ArrayList f10207J;
    public final /* synthetic */ View K;
    public final /* synthetic */ AbstractComponentCallbacksC3550lS L;
    public final /* synthetic */ AbstractComponentCallbacksC3550lS M;
    public final /* synthetic */ boolean N;
    public final /* synthetic */ ArrayList O;
    public final /* synthetic */ Object P;
    public final /* synthetic */ Rect Q;

    public RunnableC3210jT(AbstractC5257vT vTVar, C4931ta taVar, Object obj, C3381kT kTVar, ArrayList arrayList, View view, AbstractComponentCallbacksC3550lS lSVar, AbstractComponentCallbacksC3550lS lSVar2, boolean z, ArrayList arrayList2, Object obj2, Rect rect) {
        this.F = vTVar;
        this.G = taVar;
        this.H = obj;
        this.I = kTVar;
        this.f10207J = arrayList;
        this.K = view;
        this.L = lSVar;
        this.M = lSVar2;
        this.N = z;
        this.O = arrayList2;
        this.P = obj2;
        this.Q = rect;
    }

    public void run() {
        C4931ta e = AbstractC3552lT.e(this.F, this.G, this.H, this.I);
        if (e != null) {
            this.f10207J.addAll(e.values());
            this.f10207J.add(this.K);
        }
        AbstractC3552lT.c(this.L, this.M, this.N, e, false);
        Object obj = this.H;
        if (obj != null) {
            this.F.u(obj, this.O, this.f10207J);
            View k = AbstractC3552lT.k(e, this.I, this.P, this.N);
            if (k != null) {
                this.F.j(k, this.Q);
            }
        }
    }
}
