package defpackage;

/* renamed from: Nb  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0795Nb implements AbstractC4934tb {

    /* renamed from: a  reason: collision with root package name */
    public final SA f8555a;
    public final AbstractC3255jk0 b;
    public final long c;

    public C0795Nb(SA sa, AbstractC3255jk0 jk0, long j) {
        this.f8555a = sa;
        this.b = jk0;
        this.c = j;
    }

    @Override // defpackage.AbstractC1494Yl
    public void a(Object obj) {
        C0674Lb lb = new C0674Lb();
        lb.d = ((Boolean) obj).booleanValue();
        this.b.b(lb.c(this.f8555a, new C0942Pj0(2, 2, this.c)));
    }
}
