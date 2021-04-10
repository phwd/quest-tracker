package defpackage;

/* renamed from: HV  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class HV extends AbstractC3270jp0 {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f8159a;

    public HV(GV gv) {
        boolean g = AbstractC1575Zv.e().g("force-enable-night-mode");
        this.f8159a = g;
        Q7.o(g ? 2 : 1);
    }

    @Override // defpackage.AbstractC3270jp0
    public void b(AbstractC3441kp0 kp0) {
    }

    @Override // defpackage.AbstractC3270jp0
    public boolean c() {
        return this.f8159a;
    }

    @Override // defpackage.AbstractC3270jp0
    public void d(AbstractC3441kp0 kp0) {
    }
}
