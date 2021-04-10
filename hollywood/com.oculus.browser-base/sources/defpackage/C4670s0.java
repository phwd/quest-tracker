package defpackage;

import J.N;
import java.util.ArrayList;

/* renamed from: s0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4670s0 implements AbstractC2504fI0 {
    public final C5010u0 F;
    public final int G;
    public final int H;
    public final int I;

    /* renamed from: J  reason: collision with root package name */
    public final C4491qx0 f11243J;

    public C4670s0(C5010u0 u0Var, int i, int i2, int i3, C4491qx0 qx0) {
        this.F = u0Var;
        this.G = i;
        this.H = i2;
        this.I = i3;
        this.f11243J = qx0;
    }

    /* renamed from: b */
    public void a(int i, C2465f50 f50) {
        C4840t0[] t0VarArr;
        C5010u0 u0Var = this.F;
        if (f50 == null) {
            t0VarArr = new C4840t0[0];
        } else {
            ArrayList arrayList = new ArrayList();
            C2978i50 i50 = f50.d;
            if (i50 != null) {
                this.f11243J.a(i50.b);
                arrayList.add(new C4840t0(new C2978i50(i50.f10120a, i50.b, i50.d, new C4499r0(this, i50)), 8));
            }
            if (!N.M09VlOh_("AutofillKeyboardAccessory") || f50.e.isEmpty()) {
                arrayList.add(new C4840t0(f50.f9896a, 1));
            }
            if (!f50.b.isEmpty()) {
                arrayList.add(new C4840t0(f50.b, 7));
            }
            for (C3319k50 k50 : f50.e) {
                arrayList.add(new C4840t0(k50, this.H));
            }
            for (C2807h50 h50 : f50.f) {
                arrayList.add(new C4840t0(h50, 6));
            }
            t0VarArr = (C4840t0[]) arrayList.toArray(new C4840t0[0]);
        }
        u0Var.u(t0VarArr);
    }
}
