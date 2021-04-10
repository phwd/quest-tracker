package defpackage;

/* renamed from: Jb  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0552Jb implements AbstractC4764sb {

    /* renamed from: a  reason: collision with root package name */
    public final SA f8299a;
    public final AbstractC3255jk0 b;
    public final long c;

    public C0552Jb(SA sa, AbstractC3255jk0 jk0, long j) {
        this.f8299a = sa;
        this.b = jk0;
        this.c = j;
    }

    @Override // defpackage.AbstractC1555Zl
    public void a(Object obj, Object obj2) {
        C0430Hb hb = new C0430Hb();
        hb.d = ((Integer) obj).intValue();
        hb.e = (C5941zV) obj2;
        this.b.b(hb.c(this.f8299a, new C0942Pj0(1, 2, this.c)));
    }
}
