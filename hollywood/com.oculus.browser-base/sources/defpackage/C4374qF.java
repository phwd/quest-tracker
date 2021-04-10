package defpackage;

/* renamed from: qF  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4374qF implements AbstractC1494Yl {

    /* renamed from: a  reason: collision with root package name */
    public final SA f11126a;
    public final AbstractC3255jk0 b;
    public final long c;

    public C4374qF(SA sa, AbstractC3255jk0 jk0, long j) {
        this.f11126a = sa;
        this.b = jk0;
        this.c = j;
    }

    @Override // defpackage.AbstractC1494Yl
    public void a(Object obj) {
        C4032oF oFVar = new C4032oF();
        oFVar.d = ((Integer) obj).intValue();
        this.b.b(oFVar.c(this.f11126a, new C0942Pj0(1, 2, this.c)));
    }
}
