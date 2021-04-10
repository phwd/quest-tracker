package defpackage;

import android.view.View;
import java.util.ArrayList;

/* renamed from: hT  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class RunnableC2869hT implements Runnable {
    public final /* synthetic */ Object F;
    public final /* synthetic */ AbstractC5257vT G;
    public final /* synthetic */ View H;
    public final /* synthetic */ AbstractComponentCallbacksC3550lS I;

    /* renamed from: J  reason: collision with root package name */
    public final /* synthetic */ ArrayList f10072J;
    public final /* synthetic */ ArrayList K;
    public final /* synthetic */ ArrayList L;
    public final /* synthetic */ Object M;

    public RunnableC2869hT(Object obj, AbstractC5257vT vTVar, View view, AbstractComponentCallbacksC3550lS lSVar, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, Object obj2) {
        this.F = obj;
        this.G = vTVar;
        this.H = view;
        this.I = lSVar;
        this.f10072J = arrayList;
        this.K = arrayList2;
        this.L = arrayList3;
        this.M = obj2;
    }

    public void run() {
        Object obj = this.F;
        if (obj != null) {
            this.G.m(obj, this.H);
            this.K.addAll(AbstractC3552lT.h(this.G, this.F, this.I, this.f10072J, this.H));
        }
        if (this.L != null) {
            if (this.M != null) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(this.H);
                this.G.n(this.M, this.L, arrayList);
            }
            this.L.clear();
            this.L.add(this.H);
        }
    }
}
