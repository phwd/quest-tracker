package defpackage;

/* renamed from: Fr  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0348Fr implements AbstractC1768b01, AbstractC0850Ny0, AbstractC4371qE {
    public M2 F;

    public C0348Fr(String str) {
    }

    @Override // defpackage.AbstractC0850Ny0
    public void b() {
        NU0.f8549a.o("ChromeTabbedActivity.BackgroundTimeMs", -1);
    }

    @Override // defpackage.AbstractC0850Ny0
    public void c() {
    }

    @Override // defpackage.AbstractC1768b01
    public void d() {
    }

    @Override // defpackage.AbstractC4371qE
    public void destroy() {
        this.F.b(this);
    }

    @Override // defpackage.AbstractC1768b01
    public void e() {
        NU0.f8549a.o("ChromeTabbedActivity.BackgroundTimeMs", System.currentTimeMillis());
    }

    public long f() {
        return NU0.f8549a.h("ChromeTabbedActivity.BackgroundTimeMs", -1);
    }
}
