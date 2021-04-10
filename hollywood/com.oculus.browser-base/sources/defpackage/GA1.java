package defpackage;

import android.os.Bundle;
import android.os.DeadObjectException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.BaseGmsClient;

/* renamed from: GA1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class GA1 implements AbstractC1801bB1 {

    /* renamed from: a  reason: collision with root package name */
    public final C2313eB1 f8072a;

    public GA1(C2313eB1 eb1) {
        this.f8072a = eb1;
    }

    @Override // defpackage.AbstractC1801bB1
    public final AbstractC4439qg b(AbstractC4439qg qgVar) {
        try {
            EB1 eb1 = this.f8072a.m.w;
            eb1.c.add(qgVar);
            qgVar.h.set(eb1.d);
            VA1 va1 = this.f8072a.m;
            AbstractC2129d7 d7Var = (AbstractC2129d7) va1.o.get(qgVar.o);
            SE0.i(d7Var, "Appropriate Api was not requested.");
            if (((BaseGmsClient) d7Var).a() || !this.f8072a.g.containsKey(qgVar.o)) {
                qgVar.k(d7Var);
            } else {
                qgVar.l(new Status(17));
            }
        } catch (DeadObjectException unused) {
            C2313eB1 eb12 = this.f8072a;
            eb12.e.sendMessage(eb12.e.obtainMessage(1, new HA1(this, this)));
        }
        return qgVar;
    }

    @Override // defpackage.AbstractC1801bB1
    public final void c(int i) {
        this.f8072a.g(null);
        this.f8072a.n.c(i, false);
    }

    @Override // defpackage.AbstractC1801bB1
    public final void d(ConnectionResult connectionResult, C2470f7 f7Var, boolean z) {
    }

    @Override // defpackage.AbstractC1801bB1
    public final boolean disconnect() {
        VA1 va1 = this.f8072a.m;
        va1.b.lock();
        va1.b.unlock();
        this.f8072a.g(null);
        return true;
    }

    @Override // defpackage.AbstractC1801bB1
    public final void e() {
    }

    @Override // defpackage.AbstractC1801bB1
    public final void f(Bundle bundle) {
    }

    @Override // defpackage.AbstractC1801bB1
    public final void g() {
    }
}
