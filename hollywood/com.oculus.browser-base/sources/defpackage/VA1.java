package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BasePendingResult;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.locks.Lock;

/* renamed from: VA1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class VA1 extends YV implements AbstractC4705sB1 {
    public final Lock b;
    public final MV c;
    public AbstractC4534rB1 d = null;
    public final int e;
    public final Context f;
    public final Looper g;
    public final Queue h = new LinkedList();
    public volatile boolean i;
    public long j = 120000;
    public long k = 5000;
    public final WA1 l;
    public final SV m;
    public C4364qB1 n;
    public final Map o;
    public Set p = new HashSet();
    public final C3800mv q;
    public final Map r;
    public final Y6 s;
    public final C5888z90 t = new C5888z90();
    public final ArrayList u;
    public Integer v = null;
    public final EB1 w;
    public final LV x;

    public VA1(Context context, Lock lock, Looper looper, C3800mv mvVar, SV sv, Y6 y6, Map map, List list, List list2, Map map2, int i2, int i3, ArrayList arrayList) {
        UA1 ua1 = new UA1(this);
        this.x = ua1;
        this.f = context;
        this.b = lock;
        this.c = new MV(looper, ua1);
        this.g = looper;
        this.l = new WA1(this, looper);
        this.m = sv;
        this.e = i2;
        if (i2 >= 0) {
            this.v = Integer.valueOf(i3);
        }
        this.r = map;
        this.o = map2;
        this.u = arrayList;
        this.w = new EB1(map2);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            WV wv = (WV) it.next();
            MV mv = this.c;
            Objects.requireNonNull(mv);
            Objects.requireNonNull(wv, "null reference");
            synchronized (mv.N) {
                if (mv.G.contains(wv)) {
                    String valueOf = String.valueOf(wv);
                    StringBuilder sb = new StringBuilder(valueOf.length() + 62);
                    sb.append("registerConnectionCallbacks(): listener ");
                    sb.append(valueOf);
                    sb.append(" is already registered");
                    Log.w("GmsClientEvents", sb.toString());
                } else {
                    mv.G.add(wv);
                }
            }
            if (mv.F.a()) {
                Handler handler = mv.M;
                handler.sendMessage(handler.obtainMessage(1, wv));
            }
        }
        Iterator it2 = list2.iterator();
        while (it2.hasNext()) {
            this.c.b((XV) it2.next());
        }
        this.q = mvVar;
        this.s = y6;
    }

    public static int l(Iterable iterable, boolean z) {
        Iterator it = iterable.iterator();
        boolean z2 = false;
        while (it.hasNext()) {
            if (((AbstractC2129d7) it.next()).requiresSignIn()) {
                z2 = true;
            }
        }
        if (z2) {
            return 1;
        }
        return 3;
    }

    public static void m(VA1 va1) {
        va1.b.lock();
        try {
            if (va1.i) {
                va1.n();
            }
        } finally {
            va1.b.unlock();
        }
    }

    public static String q(int i2) {
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? "UNKNOWN" : "SIGN_IN_MODE_NONE" : "SIGN_IN_MODE_OPTIONAL" : "SIGN_IN_MODE_REQUIRED";
    }

    @Override // defpackage.AbstractC4705sB1
    public final void a(Bundle bundle) {
        while (!this.h.isEmpty()) {
            f((AbstractC4439qg) this.h.remove());
        }
        MV mv = this.c;
        SE0.d(mv.M, "onConnectionSuccess must only be called on the Handler thread");
        synchronized (mv.N) {
            boolean z = true;
            SE0.j(!mv.L);
            mv.M.removeMessages(1);
            mv.L = true;
            if (mv.H.size() != 0) {
                z = false;
            }
            SE0.j(z);
            ArrayList arrayList = new ArrayList(mv.G);
            int i2 = mv.K.get();
            int size = arrayList.size();
            int i3 = 0;
            while (i3 < size) {
                Object obj = arrayList.get(i3);
                i3++;
                WV wv = (WV) obj;
                if (!mv.f8478J || !mv.F.a() || mv.K.get() != i2) {
                    break;
                } else if (!mv.H.contains(wv)) {
                    wv.f(bundle);
                }
            }
            mv.H.clear();
            mv.L = false;
        }
    }

    @Override // defpackage.AbstractC4705sB1
    public final void b(ConnectionResult connectionResult) {
        SV sv = this.m;
        Context context = this.f;
        int i2 = connectionResult.H;
        Objects.requireNonNull(sv);
        if (!AbstractC3729mW.c(context, i2)) {
            o();
        }
        if (!this.i) {
            MV mv = this.c;
            SE0.d(mv.M, "onConnectionFailure must only be called on the Handler thread");
            mv.M.removeMessages(1);
            synchronized (mv.N) {
                ArrayList arrayList = new ArrayList(mv.I);
                int i3 = mv.K.get();
                int size = arrayList.size();
                int i4 = 0;
                while (true) {
                    if (i4 >= size) {
                        break;
                    }
                    Object obj = arrayList.get(i4);
                    i4++;
                    XV xv = (XV) obj;
                    if (!mv.f8478J) {
                        break;
                    } else if (mv.K.get() != i3) {
                        break;
                    } else if (mv.I.contains(xv)) {
                        xv.e0(connectionResult);
                    }
                }
            }
            this.c.a();
        }
    }

    @Override // defpackage.AbstractC4705sB1
    public final void c(int i2, boolean z) {
        if (i2 == 1 && !z && !this.i) {
            this.i = true;
            if (this.n == null) {
                try {
                    this.n = this.m.i(this.f.getApplicationContext(), new C1972cB1(this));
                } catch (SecurityException unused) {
                }
            }
            WA1 wa1 = this.l;
            wa1.sendMessageDelayed(wa1.obtainMessage(1), this.j);
            WA1 wa12 = this.l;
            wa12.sendMessageDelayed(wa12.obtainMessage(2), this.k);
        }
        for (BasePendingResult basePendingResult : (BasePendingResult[]) this.w.c.toArray(EB1.b)) {
            basePendingResult.h(EB1.f7943a);
        }
        MV mv = this.c;
        SE0.d(mv.M, "onUnintentionalDisconnection must only be called on the Handler thread");
        mv.M.removeMessages(1);
        synchronized (mv.N) {
            mv.L = true;
            ArrayList arrayList = new ArrayList(mv.G);
            int i3 = mv.K.get();
            int size = arrayList.size();
            int i4 = 0;
            while (i4 < size) {
                Object obj = arrayList.get(i4);
                i4++;
                WV wv = (WV) obj;
                if (!mv.f8478J || mv.K.get() != i3) {
                    break;
                } else if (mv.G.contains(wv)) {
                    wv.c(i2);
                }
            }
            mv.H.clear();
            mv.L = false;
        }
        this.c.a();
        if (i2 == 2) {
            n();
        }
    }

    @Override // defpackage.YV
    public final void d() {
        this.b.lock();
        try {
            boolean z = false;
            if (this.e >= 0) {
                if (this.v != null) {
                    z = true;
                }
                SE0.k(z, "Sign-in mode should have been set explicitly by auto-manage.");
            } else {
                Integer num = this.v;
                if (num == null) {
                    this.v = Integer.valueOf(l(this.o.values(), false));
                } else if (num.intValue() == 2) {
                    throw new IllegalStateException("Cannot call connect() when SignInMode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
                }
            }
            j(this.v.intValue());
        } finally {
            this.b.unlock();
        }
    }

    @Override // defpackage.YV
    public final void e() {
        this.b.lock();
        try {
            this.w.a();
            AbstractC4534rB1 rb1 = this.d;
            if (rb1 != null) {
                rb1.disconnect();
            }
            C5888z90 z90 = this.t;
            for (C5718y90 y90 : z90.f11728a) {
                y90.b = null;
            }
            z90.f11728a.clear();
            for (AbstractC4439qg qgVar : this.h) {
                qgVar.h.set(null);
                qgVar.a();
            }
            this.h.clear();
            if (this.d != null) {
                o();
                this.c.a();
                this.b.unlock();
            }
        } finally {
            this.b.unlock();
        }
    }

    @Override // defpackage.YV
    public final AbstractC4439qg f(AbstractC4439qg qgVar) {
        SE0.b(qgVar.o != null, "This task can not be executed (it's probably a Batch or malformed)");
        boolean containsKey = this.o.containsKey(qgVar.o);
        C2470f7 f7Var = qgVar.p;
        String str = f7Var != null ? f7Var.c : "the API";
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 65);
        sb.append("GoogleApiClient is not configured to use ");
        sb.append(str);
        sb.append(" required for this call.");
        SE0.b(containsKey, sb.toString());
        this.b.lock();
        try {
            if (this.d == null) {
                throw new IllegalStateException("GoogleApiClient is not connected yet.");
            } else if (this.i) {
                this.h.add(qgVar);
                while (!this.h.isEmpty()) {
                    AbstractC4439qg qgVar2 = (AbstractC4439qg) this.h.remove();
                    this.w.b(qgVar2);
                    qgVar2.l(Status.G);
                }
                return qgVar;
            } else {
                AbstractC4439qg b2 = this.d.b(qgVar);
                this.b.unlock();
                return b2;
            }
        } finally {
            this.b.unlock();
        }
    }

    @Override // defpackage.YV
    public final AbstractC2129d7 g(AbstractC1607a7 a7Var) {
        AbstractC2129d7 d7Var = (AbstractC2129d7) this.o.get(a7Var);
        SE0.i(d7Var, "Appropriate Api was not requested.");
        return d7Var;
    }

    @Override // defpackage.YV
    public final Looper h() {
        return this.g;
    }

    @Override // defpackage.YV
    public final boolean i() {
        AbstractC4534rB1 rb1 = this.d;
        return rb1 != null && rb1.a();
    }

    public final void j(int i2) {
        this.b.lock();
        boolean z = true;
        if (!(i2 == 3 || i2 == 1 || i2 == 2)) {
            z = false;
        }
        try {
            StringBuilder sb = new StringBuilder(33);
            sb.append("Illegal sign-in mode: ");
            sb.append(i2);
            SE0.b(z, sb.toString());
            p(i2);
            n();
        } finally {
            this.b.unlock();
        }
    }

    public final void k(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.append((CharSequence) str).append("mContext=").println(this.f);
        printWriter.append((CharSequence) str).append("mResuming=").print(this.i);
        printWriter.append(" mWorkQueue.size()=").print(this.h.size());
        printWriter.append(" mUnconsumedApiCalls.size()=").println(this.w.c.size());
        AbstractC4534rB1 rb1 = this.d;
        if (rb1 != null) {
            rb1.dump(str, null, printWriter, null);
        }
    }

    public final void n() {
        this.c.f8478J = true;
        this.d.e();
    }

    public final boolean o() {
        if (!this.i) {
            return false;
        }
        this.i = false;
        this.l.removeMessages(2);
        this.l.removeMessages(1);
        C4364qB1 qb1 = this.n;
        if (qb1 != null) {
            qb1.a();
            this.n = null;
        }
        return true;
    }

    public final void p(int i2) {
        Integer num = this.v;
        if (num == null) {
            this.v = Integer.valueOf(i2);
        } else if (num.intValue() != i2) {
            String q2 = q(i2);
            String q3 = q(this.v.intValue());
            StringBuilder sb = new StringBuilder(q3.length() + q2.length() + 51);
            sb.append("Cannot use sign-in mode: ");
            sb.append(q2);
            sb.append(". Mode was already set to ");
            sb.append(q3);
            throw new IllegalStateException(sb.toString());
        }
        if (this.d == null) {
            boolean z = false;
            for (AbstractC2129d7 d7Var : this.o.values()) {
                if (d7Var.requiresSignIn()) {
                    z = true;
                }
            }
            int intValue = this.v.intValue();
            if (intValue != 1) {
                if (intValue == 2 && z) {
                    Context context = this.f;
                    Lock lock = this.b;
                    Looper looper = this.g;
                    SV sv = this.m;
                    Map map = this.o;
                    C3800mv mvVar = this.q;
                    Map map2 = this.r;
                    Y6 y6 = this.s;
                    ArrayList arrayList = this.u;
                    C4931ta taVar = new C4931ta();
                    C4931ta taVar2 = new C4931ta();
                    for (Map.Entry entry : map.entrySet()) {
                        AbstractC2129d7 d7Var2 = (AbstractC2129d7) entry.getValue();
                        Objects.requireNonNull(d7Var2);
                        if (d7Var2.requiresSignIn()) {
                            taVar.put((AbstractC1607a7) entry.getKey(), d7Var2);
                        } else {
                            taVar2.put((AbstractC1607a7) entry.getKey(), d7Var2);
                        }
                    }
                    SE0.k(!taVar.isEmpty(), "CompositeGoogleApiClient should not be used without any APIs that require sign-in.");
                    C4931ta taVar3 = new C4931ta();
                    C4931ta taVar4 = new C4931ta();
                    for (C2470f7 f7Var : map2.keySet()) {
                        AbstractC1607a7 a2 = f7Var.a();
                        if (taVar.containsKey(a2)) {
                            taVar3.put(f7Var, (Boolean) map2.get(f7Var));
                        } else if (taVar2.containsKey(a2)) {
                            taVar4.put(f7Var, (Boolean) map2.get(f7Var));
                        } else {
                            throw new IllegalStateException("Each API in the isOptionalMap must have a corresponding client in the clients map.");
                        }
                    }
                    ArrayList arrayList2 = new ArrayList();
                    ArrayList arrayList3 = new ArrayList();
                    int size = arrayList.size();
                    int i3 = 0;
                    while (i3 < size) {
                        Object obj = arrayList.get(i3);
                        int i4 = i3 + 1;
                        XB1 xb1 = (XB1) obj;
                        if (taVar3.containsKey(xb1.f9198a)) {
                            arrayList2.add(xb1);
                        } else if (taVar4.containsKey(xb1.f9198a)) {
                            arrayList3.add(xb1);
                        } else {
                            throw new IllegalStateException("Each ClientCallbacks must have a corresponding API in the isOptionalMap");
                        }
                        size = size;
                        i3 = i4;
                    }
                    this.d = new YB1(context, this, lock, looper, sv, taVar, taVar2, mvVar, y6, null, arrayList2, arrayList3, taVar3, taVar4);
                    return;
                }
            } else if (!z) {
                throw new IllegalStateException("SIGN_IN_MODE_REQUIRED cannot be used on a GoogleApiClient that does not contain any authenticated APIs. Use connect() instead.");
            }
            this.d = new C2313eB1(this.f, this, this.b, this.g, this.m, this.o, this.q, this.r, this.s, this.u, this);
        }
    }
}
