package defpackage;

import android.util.Pair;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: JW0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class JW0 extends AbstractC5750yK0 {
    public final C4935tb0 I;

    /* renamed from: J  reason: collision with root package name */
    public final AbstractC2648g90 f8293J;
    public final SparseArray K = new SparseArray();

    public JW0(C4935tb0 tb0) {
        this.I = tb0;
        HW0 hw0 = new HW0(this);
        this.f8293J = hw0;
        tb0.F.b(hw0);
    }

    @Override // defpackage.AbstractC5750yK0
    public int b() {
        return this.I.size();
    }

    @Override // defpackage.AbstractC5750yK0
    public int d(int i) {
        return ((C4765sb0) this.I.G.get(i)).f11283a;
    }

    @Override // defpackage.AbstractC5750yK0
    public void j(XK0 xk0, int i) {
        ((IW0) xk0).x(((C4765sb0) this.I.G.get(i)).b);
    }

    public View s(ViewGroup viewGroup, int i) {
        return ((AbstractC5105ub0) ((Pair) this.K.get(i)).first).a(viewGroup);
    }

    /* renamed from: t */
    public IW0 m(ViewGroup viewGroup, int i) {
        return new IW0(this, s(viewGroup, i), (YH0) ((Pair) this.K.get(i)).second);
    }

    /* renamed from: u */
    public void q(IW0 iw0) {
        iw0.x(null);
    }

    public void v(int i, AbstractC5105ub0 ub0, YH0 yh0) {
        this.K.put(i, new Pair(ub0, yh0));
    }
}
