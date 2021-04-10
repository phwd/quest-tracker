package defpackage;

import android.view.View;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: sT  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC4747sT implements Runnable {
    public final /* synthetic */ int F;
    public final /* synthetic */ ArrayList G;
    public final /* synthetic */ ArrayList H;
    public final /* synthetic */ ArrayList I;

    /* renamed from: J  reason: collision with root package name */
    public final /* synthetic */ ArrayList f11276J;

    public RunnableC4747sT(AbstractC5257vT vTVar, int i, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, ArrayList arrayList4) {
        this.F = i;
        this.G = arrayList;
        this.H = arrayList2;
        this.I = arrayList3;
        this.f11276J = arrayList4;
    }

    public void run() {
        for (int i = 0; i < this.F; i++) {
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            ((View) this.G.get(i)).setTransitionName((String) this.H.get(i));
            ((View) this.I.get(i)).setTransitionName((String) this.f11276J.get(i));
        }
    }
}
