package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.zza;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

/* renamed from: ZV  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class ZV implements WV, XV, AbstractC1624aC1 {

    /* renamed from: a  reason: collision with root package name */
    public final Queue f9346a = new LinkedList();
    public final AbstractC2129d7 b;
    public final Z6 c;
    public final C4861t7 d;
    public final C2316eC1 e;
    public final Set f = new HashSet();
    public final Map g = new HashMap();
    public final int h;
    public final BinderC5725yB1 i;
    public boolean j;
    public final List k = new ArrayList();
    public ConnectionResult l = null;
    public final /* synthetic */ C2021cW m;

    public ZV(C2021cW cWVar, QV qv) {
        this.m = cWVar;
        AbstractC2129d7 c2 = qv.c(cWVar.S.getLooper(), this);
        this.b = c2;
        this.c = c2;
        this.d = qv.d;
        this.e = new C2316eC1();
        this.h = qv.f;
        if (c2.requiresSignIn()) {
            this.i = qv.d(cWVar.K, cWVar.S);
        } else {
            this.i = null;
        }
    }

    public final void a() {
        SE0.c(this.m.S);
        if (!((BaseGmsClient) this.b).a() && !((BaseGmsClient) this.b).o()) {
            C2021cW cWVar = this.m;
            int a2 = cWVar.M.a(cWVar.K, this.b);
            if (a2 != 0) {
                e0(new ConnectionResult(a2, null));
                return;
            }
            C2021cW cWVar2 = this.m;
            AbstractC2129d7 d7Var = this.b;
            C1670aW aWVar = new C1670aW(cWVar2, d7Var, this.d);
            if (d7Var.requiresSignIn()) {
                BinderC5725yB1 yb1 = this.i;
                AbstractC5045uB1 ub1 = yb1.g;
                if (ub1 != null) {
                    ub1.disconnect();
                }
                yb1.f.i = Integer.valueOf(System.identityHashCode(yb1));
                Y6 y6 = yb1.d;
                Context context = yb1.b;
                Looper looper = yb1.c.getLooper();
                C3800mv mvVar = yb1.f;
                yb1.g = (AbstractC5045uB1) y6.b(context, looper, mvVar, mvVar.g, yb1, yb1);
                yb1.h = aWVar;
                Set set = yb1.e;
                if (set == null || set.isEmpty()) {
                    yb1.c.post(new RunnableC5555xB1(yb1));
                } else {
                    C4752sV0 sv0 = (C4752sV0) yb1.g;
                    sv0.d(new C3242jg(sv0));
                }
            }
            ((BaseGmsClient) this.b).d(aWVar);
        }
    }

    public final boolean b() {
        return this.b.requiresSignIn();
    }

    @Override // defpackage.AbstractC0482Hx
    public final void c(int i2) {
        if (Looper.myLooper() == this.m.S.getLooper()) {
            j();
        } else {
            this.m.S.post(new RunnableC3338kB1(this));
        }
    }

    @Override // defpackage.AbstractC1624aC1
    public final void d(ConnectionResult connectionResult, C2470f7 f7Var, boolean z) {
        if (Looper.myLooper() == this.m.S.getLooper()) {
            e0(connectionResult);
        } else {
            this.m.S.post(new RunnableC3167jB1(this, connectionResult));
        }
    }

    public final Feature e(Feature[] featureArr) {
        if (!(featureArr == null || featureArr.length == 0)) {
            zza zza = ((BaseGmsClient) this.b).z;
            Feature[] featureArr2 = zza == null ? null : zza.G;
            if (featureArr2 == null) {
                featureArr2 = new Feature[0];
            }
            C4931ta taVar = new C4931ta(featureArr2.length);
            for (Feature feature : featureArr2) {
                taVar.put(feature.F, Long.valueOf(feature.x()));
            }
            for (Feature feature2 : featureArr) {
                if (!taVar.containsKey(feature2.F) || ((Long) taVar.get(feature2.F)).longValue() < feature2.x()) {
                    return feature2;
                }
            }
        }
        return null;
    }

    @Override // defpackage.AbstractC0777Ms0
    public final void e0(ConnectionResult connectionResult) {
        AbstractC5045uB1 ub1;
        SE0.c(this.m.S);
        BinderC5725yB1 yb1 = this.i;
        if (!(yb1 == null || (ub1 = yb1.g) == null)) {
            ub1.disconnect();
        }
        m();
        this.m.M.f8961a.clear();
        s(connectionResult);
        if (connectionResult.H == 4) {
            p(C2021cW.G);
        } else if (this.f9346a.isEmpty()) {
            this.l = connectionResult;
        } else {
            synchronized (C2021cW.H) {
                Objects.requireNonNull(this.m);
            }
            if (!this.m.c(connectionResult, this.h)) {
                if (connectionResult.H == 18) {
                    this.j = true;
                }
                if (this.j) {
                    Handler handler = this.m.S;
                    Message obtain = Message.obtain(handler, 9, this.d);
                    Objects.requireNonNull(this.m);
                    handler.sendMessageDelayed(obtain, 5000);
                    return;
                }
                String str = this.d.c.c;
                String valueOf = String.valueOf(connectionResult);
                StringBuilder sb = new StringBuilder(valueOf.length() + String.valueOf(str).length() + 63);
                sb.append("API: ");
                sb.append(str);
                sb.append(" is not available on this device. Connection failed with: ");
                sb.append(valueOf);
                p(new Status(17, sb.toString()));
            }
        }
    }

    @Override // defpackage.AbstractC0482Hx
    public final void f(Bundle bundle) {
        if (Looper.myLooper() == this.m.S.getLooper()) {
            i();
        } else {
            this.m.S.post(new RunnableC2997iB1(this));
        }
    }

    public final void g(AbstractC5385wB1 wb1) {
        SE0.c(this.m.S);
        if (!((BaseGmsClient) this.b).a()) {
            this.f9346a.add(wb1);
            ConnectionResult connectionResult = this.l;
            if (connectionResult == null || !connectionResult.x()) {
                a();
            } else {
                e0(this.l);
            }
        } else if (h(wb1)) {
            o();
        } else {
            this.f9346a.add(wb1);
        }
    }

    public final boolean h(AbstractC5385wB1 wb1) {
        if (!(wb1 instanceof XA1)) {
            q(wb1);
            return true;
        }
        XA1 xa1 = (XA1) wb1;
        Feature e2 = e(xa1.f(this));
        if (e2 == null) {
            q(wb1);
            return true;
        } else if (xa1.g(this)) {
            C1850bW bWVar = new C1850bW(this.d, e2, null);
            int indexOf = this.k.indexOf(bWVar);
            if (indexOf >= 0) {
                C1850bW bWVar2 = (C1850bW) this.k.get(indexOf);
                this.m.S.removeMessages(15, bWVar2);
                Handler handler = this.m.S;
                Message obtain = Message.obtain(handler, 15, bWVar2);
                Objects.requireNonNull(this.m);
                handler.sendMessageDelayed(obtain, 5000);
                return false;
            }
            this.k.add(bWVar);
            Handler handler2 = this.m.S;
            Message obtain2 = Message.obtain(handler2, 15, bWVar);
            Objects.requireNonNull(this.m);
            handler2.sendMessageDelayed(obtain2, 5000);
            Handler handler3 = this.m.S;
            Message obtain3 = Message.obtain(handler3, 16, bWVar);
            Objects.requireNonNull(this.m);
            handler3.sendMessageDelayed(obtain3, 120000);
            ConnectionResult connectionResult = new ConnectionResult(2, null);
            synchronized (C2021cW.H) {
                Objects.requireNonNull(this.m);
            }
            this.m.c(connectionResult, this.h);
            return false;
        } else {
            xa1.b(new Pp1(e2));
            return false;
        }
    }

    public final void i() {
        m();
        s(ConnectionResult.F);
        n();
        Iterator it = this.g.values().iterator();
        if (!it.hasNext()) {
            k();
            o();
            return;
        }
        C5859z.a(it.next());
        Objects.requireNonNull(null);
        throw null;
    }

    public final void j() {
        m();
        this.j = true;
        C2316eC1 ec1 = this.e;
        Objects.requireNonNull(ec1);
        ec1.a(true, EB1.f7943a);
        Handler handler = this.m.S;
        Message obtain = Message.obtain(handler, 9, this.d);
        Objects.requireNonNull(this.m);
        handler.sendMessageDelayed(obtain, 5000);
        Handler handler2 = this.m.S;
        Message obtain2 = Message.obtain(handler2, 11, this.d);
        Objects.requireNonNull(this.m);
        handler2.sendMessageDelayed(obtain2, 120000);
        this.m.M.f8961a.clear();
    }

    public final void k() {
        ArrayList arrayList = new ArrayList(this.f9346a);
        int size = arrayList.size();
        int i2 = 0;
        while (i2 < size) {
            Object obj = arrayList.get(i2);
            i2++;
            AbstractC5385wB1 wb1 = (AbstractC5385wB1) obj;
            if (!((BaseGmsClient) this.b).a()) {
                return;
            }
            if (h(wb1)) {
                this.f9346a.remove(wb1);
            }
        }
    }

    public final void l() {
        SE0.c(this.m.S);
        Status status = C2021cW.F;
        p(status);
        C2316eC1 ec1 = this.e;
        Objects.requireNonNull(ec1);
        ec1.a(false, status);
        for (C5378w90 w90 : (C5378w90[]) this.g.keySet().toArray(new C5378w90[this.g.size()])) {
            g(new RB1(w90, new C0563Je1()));
        }
        s(new ConnectionResult(4));
        if (((BaseGmsClient) this.b).a()) {
            AbstractC2129d7 d7Var = this.b;
            C3680mB1 mb1 = new C3680mB1(this);
            Objects.requireNonNull((BaseGmsClient) d7Var);
            this.m.S.post(new RunnableC3509lB1(mb1));
        }
    }

    public final void m() {
        SE0.c(this.m.S);
        this.l = null;
    }

    public final void n() {
        if (this.j) {
            this.m.S.removeMessages(11, this.d);
            this.m.S.removeMessages(9, this.d);
            this.j = false;
        }
    }

    public final void o() {
        this.m.S.removeMessages(12, this.d);
        Handler handler = this.m.S;
        handler.sendMessageDelayed(handler.obtainMessage(12, this.d), this.m.f9610J);
    }

    public final void p(Status status) {
        SE0.c(this.m.S);
        for (AbstractC5385wB1 wb1 : this.f9346a) {
            wb1.a(status);
        }
        this.f9346a.clear();
    }

    public final void q(AbstractC5385wB1 wb1) {
        wb1.c(this.e, b());
        try {
            wb1.e(this);
        } catch (DeadObjectException unused) {
            c(1);
            this.b.disconnect();
        }
    }

    public final boolean r(boolean z) {
        SE0.c(this.m.S);
        if (!((BaseGmsClient) this.b).a() || this.g.size() != 0) {
            return false;
        }
        C2316eC1 ec1 = this.e;
        if (!ec1.f9839a.isEmpty() || !ec1.b.isEmpty()) {
            if (z) {
                o();
            }
            return false;
        }
        this.b.disconnect();
        return true;
    }

    public final void s(ConnectionResult connectionResult) {
        for (TB1 tb1 : this.f) {
            String str = null;
            if (AbstractC0895Oq0.a(connectionResult, ConnectionResult.F)) {
                ((BaseGmsClient) this.b).h();
                str = "com.google.android.gms";
            }
            tb1.a(this.d, connectionResult, str);
        }
        this.f.clear();
    }
}
