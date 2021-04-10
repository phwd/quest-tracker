package defpackage;

/* renamed from: Gf  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0381Gf implements AbstractC1494Yl {

    /* renamed from: a  reason: collision with root package name */
    public final SA f8100a;
    public final AbstractC3255jk0 b;
    public final long c;

    public C0381Gf(SA sa, AbstractC3255jk0 jk0, long j) {
        this.f8100a = sa;
        this.b = jk0;
        this.c = j;
    }

    @Override // defpackage.AbstractC1494Yl
    public void a(Object obj) {
        C0259Ef ef = new C0259Ef();
        ef.d = (int[]) obj;
        this.b.b(ef.c(this.f8100a, new C0942Pj0(1, 2, this.c)));
    }
}
