package defpackage;

/* renamed from: yF  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5735yF implements AbstractC1555Zl {

    /* renamed from: a  reason: collision with root package name */
    public final SA f11671a;
    public final AbstractC3255jk0 b;
    public final long c;

    public C5735yF(SA sa, AbstractC3255jk0 jk0, long j) {
        this.f11671a = sa;
        this.b = jk0;
        this.c = j;
    }

    @Override // defpackage.AbstractC1555Zl
    public void a(Object obj, Object obj2) {
        C5395wF wFVar = new C5395wF();
        wFVar.d = ((Integer) obj).intValue();
        wFVar.e = (JI0[]) obj2;
        this.b.b(wFVar.c(this.f11671a, new C0942Pj0(2, 2, this.c)));
    }
}
