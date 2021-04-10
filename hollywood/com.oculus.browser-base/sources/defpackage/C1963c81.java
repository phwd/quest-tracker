package defpackage;

/* renamed from: c81  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1963c81 implements AbstractC0850Ny0 {
    public final /* synthetic */ C2475f81 F;

    public C1963c81(C2475f81 f81) {
        this.F = f81;
    }

    @Override // defpackage.AbstractC0850Ny0
    public void b() {
        C2475f81 f81 = this.F;
        f81.g(((AbstractC0246Ea1) f81.e).k());
        if (((AbstractC0246Ea1) this.F.e).h) {
            StringBuilder i = AbstractC2531fV.i("TabStrip.SessionVisibility.");
            i.append(this.F.x ? "Visible" : "Hidden");
            AbstractC3535lK0.a(i.toString());
        }
    }

    @Override // defpackage.AbstractC0850Ny0
    public void c() {
        this.F.g(-1);
    }
}
