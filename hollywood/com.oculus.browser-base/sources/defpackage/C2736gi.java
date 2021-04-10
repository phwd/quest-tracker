package defpackage;

/* renamed from: gi  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2736gi implements AbstractC1494Yl {

    /* renamed from: a  reason: collision with root package name */
    public final SA f10013a;
    public final AbstractC3255jk0 b;
    public final long c;

    public C2736gi(SA sa, AbstractC3255jk0 jk0, long j) {
        this.f10013a = sa;
        this.b = jk0;
        this.c = j;
    }

    @Override // defpackage.AbstractC1494Yl
    public void a(Object obj) {
        C2394ei eiVar = new C2394ei();
        eiVar.d = (String) obj;
        this.b.b(eiVar.c(this.f10013a, new C0942Pj0(7, 2, this.c)));
    }
}
