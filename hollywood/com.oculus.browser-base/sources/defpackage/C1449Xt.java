package defpackage;

/* renamed from: Xt  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1449Xt implements J00 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AbstractActivityC2601fu f9241a;

    public C1449Xt(AbstractActivityC2601fu fuVar) {
        this.f9241a = fuVar;
    }

    @Override // defpackage.J00
    public void a() {
        if (!this.f9241a.v()) {
            AbstractActivityC2601fu fuVar = this.f9241a;
            if (!fuVar.D0 || !fuVar.E0) {
                fuVar.finish();
            } else {
                ((AbstractC0246Ea1) fuVar.P()).l(true).g(false, false);
            }
        }
    }

    @Override // defpackage.J00
    public boolean b() {
        return ((AbstractC0246Ea1) this.f9241a.P()).l(true).getCount() > 0;
    }

    @Override // defpackage.J00
    public boolean isActiveModel() {
        return ((AbstractC0246Ea1) this.f9241a.P()).l(true).isActiveModel();
    }
}
