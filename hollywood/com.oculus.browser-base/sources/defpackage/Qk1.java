package defpackage;

/* renamed from: Qk1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Qk1 implements EQ {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Uk1 f8783a;

    public Qk1(Uk1 uk1) {
        this.f8783a = uk1;
    }

    @Override // defpackage.EQ
    public void a() {
        this.f8783a.K.f9104a.m(false);
        Uk1 uk1 = this.f8783a;
        C0090Bk bk = uk1.H0;
        if (bk != null) {
            bk.p(uk1.J0);
        }
    }

    @Override // defpackage.EQ
    public void b() {
        this.f8783a.K.f9104a.m(true);
        Uk1 uk1 = this.f8783a;
        C0090Bk bk = uk1.H0;
        if (bk != null) {
            uk1.J0 = bk.r(uk1.J0);
        }
    }
}
