package defpackage;

import java.util.Iterator;

/* renamed from: Y0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Y0 extends AbstractC2032cb {
    public final /* synthetic */ C1769b1 i;

    public Y0(C1769b1 b1Var, W0 w0) {
        this.i = b1Var;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        this.i.c = C1769b1.u();
        C1769b1 b1Var = this.i;
        b1Var.d = C1769b1.v(b1Var);
        C1769b1 b1Var2 = this.i;
        b1Var2.e.set(b1Var2.x());
        this.i.f.countDown();
        return null;
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        Void r3 = (Void) obj;
        AbstractC3364kK0.g("Signin.AndroidNumberOfDeviceAccounts", this.i.n().size(), 50);
        Iterator it = this.i.g.iterator();
        while (it.hasNext()) {
            ((Runnable) it.next()).run();
        }
        this.i.g.clear();
        this.i.w();
        C1769b1.s(this.i);
    }

    @Override // defpackage.AbstractC2032cb
    public void l() {
        C1769b1.t(this.i);
    }
}
