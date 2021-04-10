package defpackage;

import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import org.chromium.base.TraceEvent;

/* renamed from: zs0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C6006zs0 extends JW0 {
    public int L = -1;
    public IK0 M;

    public C6006zs0(C4935tb0 tb0) {
        super(tb0);
    }

    @Override // defpackage.AbstractC5750yK0
    public void i(RecyclerView recyclerView) {
        this.M = recyclerView.U;
        this.L = -1;
    }

    @Override // defpackage.JW0, defpackage.AbstractC5750yK0
    public void j(XK0 xk0, int i) {
        ((IW0) xk0).x(((C4765sb0) this.I.G.get(i)).b);
    }

    @Override // defpackage.JW0, defpackage.AbstractC5750yK0
    public /* bridge */ /* synthetic */ XK0 m(ViewGroup viewGroup, int i) {
        return m(viewGroup, i);
    }

    @Override // defpackage.JW0, defpackage.AbstractC5750yK0
    public void q(XK0 xk0) {
        IW0 iw0 = (IW0) xk0;
        iw0.x(null);
        View view = iw0.G;
        if (view != null) {
            view.setSelected(false);
        }
    }

    @Override // defpackage.JW0
    public View s(ViewGroup viewGroup, int i) {
        TraceEvent k0 = TraceEvent.k0("OmniboxSuggestionsList.CreateView", "type:" + i);
        try {
            int i2 = I31.b;
            H31 h31 = new H31("Android.Omnibox.SuggestionView.CreateTime");
            try {
                View a2 = ((AbstractC5105ub0) ((Pair) this.K.get(i)).first).a(viewGroup);
                h31.close();
                if (k0 != null) {
                    k0.close();
                }
                return a2;
            } catch (Throwable th) {
                AbstractC0754Mh1.f8495a.a(th, th);
            }
            throw th;
            throw th;
        } catch (Throwable th2) {
            AbstractC0754Mh1.f8495a.a(th, th2);
        }
    }

    @Override // defpackage.JW0
    public void u(IW0 iw0) {
        iw0.x(null);
        View view = iw0.G;
        if (view != null) {
            view.setSelected(false);
        }
    }

    public View w() {
        int i;
        if (this.M != null && (i = this.L) >= 0 && i < b()) {
            View u = this.M.u(this.L);
            if (u != null) {
                return u;
            }
            this.L = -1;
        }
        return null;
    }

    public boolean x(int i) {
        if (this.M == null || i < 0 || i >= b()) {
            return false;
        }
        View u = this.M.u(this.L);
        if (u != null) {
            u.setSelected(false);
        }
        this.L = i;
        this.M.N0(i);
        View u2 = this.M.u(i);
        if (u2 != null) {
            u2.setSelected(true);
        }
        return true;
    }
}
