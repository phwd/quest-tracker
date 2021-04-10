package defpackage;

/* renamed from: fh  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2562fh implements AbstractC1494Yl {

    /* renamed from: a  reason: collision with root package name */
    public final SA f9942a;
    public final AbstractC3255jk0 b;
    public final long c;

    public C2562fh(SA sa, AbstractC3255jk0 jk0, long j) {
        this.f9942a = sa;
        this.b = jk0;
        this.c = j;
    }

    @Override // defpackage.AbstractC1494Yl
    public void a(Object obj) {
        C2221dh dhVar = new C2221dh();
        dhVar.d = (C3245jh) obj;
        this.b.b(dhVar.c(this.f9942a, new C0942Pj0(0, 2, this.c)));
    }
}
