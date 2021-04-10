package defpackage;

/* renamed from: x20  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5527x20 implements AbstractC1494Yl {

    /* renamed from: a  reason: collision with root package name */
    public final SA f11583a;
    public final AbstractC3255jk0 b;
    public final long c;

    public C5527x20(SA sa, AbstractC3255jk0 jk0, long j) {
        this.f11583a = sa;
        this.b = jk0;
        this.c = j;
    }

    @Override // defpackage.AbstractC1494Yl
    public void a(Object obj) {
        C5187v20 v20 = new C5187v20();
        v20.d = (C5922zL0[]) obj;
        this.b.b(v20.c(this.f11583a, new C0942Pj0(0, 2, this.c)));
    }
}
