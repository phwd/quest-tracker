package defpackage;

/* renamed from: X01  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class X01 extends I70 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Y01 f9185a;

    public X01(Y01 y01) {
        this.f9185a = y01;
    }

    @Override // defpackage.I70
    public void c(int i) {
        if (i == 1) {
            this.f9185a.f9248a.j(Z01.n, true);
            C0330Fi0 fi0 = this.f9185a.l.c;
            if (fi0 != null) {
                fi0.h.j(AbstractC0513Ii0.d, true);
            }
        }
    }

    @Override // defpackage.I70
    public void d(int i, boolean z, boolean z2) {
        if (i == 1) {
            this.f9185a.f9248a.j(Z01.n, false);
            C0330Fi0 fi0 = this.f9185a.l.c;
            if (fi0 != null) {
                fi0.h.j(AbstractC0513Ii0.d, false);
            }
        }
    }

    @Override // defpackage.I70
    public void e(int i, boolean z) {
        if (i == 1) {
            this.f9185a.b();
            Y01 y01 = this.f9185a;
            int i2 = y01.m;
            if (i2 == 5 || i2 == 6 || y01.e) {
                y01.f9248a.j(Z01.r, true);
            }
            Y01 y012 = this.f9185a;
            if (y012.e) {
                y012.f9248a.j(Z01.p, true);
            }
        }
    }
}
