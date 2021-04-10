package defpackage;

/* renamed from: tk1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4963tk1 implements AbstractC2280e01 {

    /* renamed from: a  reason: collision with root package name */
    public final Uk1 f11365a;

    public C4963tk1(Uk1 uk1) {
        this.f11365a = uk1;
    }

    @Override // defpackage.AbstractC2280e01
    public void a(int i, boolean z) {
        Uk1 uk1 = this.f11365a;
        uk1.i0 = i;
        Vl1 vl1 = uk1.K;
        T01 t01 = vl1.c;
        if (t01 != null && vl1.f9104a.f9169J != null) {
            Y01 y01 = t01.f8931a;
            y01.m = i;
            y01.f9248a.j(Z01.g, z);
            y01.b();
            y01.d();
            y01.c(y01.n);
            y01.a((C0517Ik) y01.f.get());
            y01.e(y01.p);
            vl1.c();
        }
    }
}
