package defpackage;

import java.util.ArrayList;

/* renamed from: yT  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5767yT extends AbstractC3094in1 {
    public final /* synthetic */ Object F;
    public final /* synthetic */ ArrayList G;
    public final /* synthetic */ Object H;
    public final /* synthetic */ ArrayList I;

    /* renamed from: J  reason: collision with root package name */
    public final /* synthetic */ Object f11683J;
    public final /* synthetic */ ArrayList K;
    public final /* synthetic */ CT L;

    public C5767yT(CT ct, Object obj, ArrayList arrayList, Object obj2, ArrayList arrayList2, Object obj3, ArrayList arrayList3) {
        this.L = ct;
        this.F = obj;
        this.G = arrayList;
        this.H = obj2;
        this.I = arrayList2;
        this.f11683J = obj3;
        this.K = arrayList3;
    }

    @Override // defpackage.AbstractC2753gn1, defpackage.AbstractC3094in1
    public void a(AbstractC2924hn1 hn1) {
        Object obj = this.F;
        if (obj != null) {
            this.L.n(obj, this.G, null);
        }
        Object obj2 = this.H;
        if (obj2 != null) {
            this.L.n(obj2, this.I, null);
        }
        Object obj3 = this.f11683J;
        if (obj3 != null) {
            this.L.n(obj3, this.K, null);
        }
    }

    @Override // defpackage.AbstractC2753gn1
    public void c(AbstractC2924hn1 hn1) {
        hn1.w(this);
    }
}
