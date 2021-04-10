package defpackage;

/* renamed from: Pf  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0929Pf implements AbstractC1494Yl {

    /* renamed from: a  reason: collision with root package name */
    public final SA f8703a;
    public final AbstractC3255jk0 b;
    public final long c;

    public C0929Pf(SA sa, AbstractC3255jk0 jk0, long j) {
        this.f8703a = sa;
        this.b = jk0;
        this.c = j;
    }

    @Override // defpackage.AbstractC1494Yl
    public void a(Object obj) {
        C0807Nf nf = new C0807Nf();
        nf.d = (C0625Kf[]) obj;
        this.b.b(nf.c(this.f8703a, new C0942Pj0(0, 2, this.c)));
    }
}
