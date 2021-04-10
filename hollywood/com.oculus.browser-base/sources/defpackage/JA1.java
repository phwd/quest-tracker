package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;

/* renamed from: JA1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class JA1 implements AbstractC1801bB1 {

    /* renamed from: a  reason: collision with root package name */
    public final C2313eB1 f8277a;
    public final Lock b;
    public final Context c;
    public final UV d;
    public ConnectionResult e;
    public int f;
    public int g = 0;
    public int h;
    public final Bundle i = new Bundle();
    public final Set j = new HashSet();
    public AbstractC5045uB1 k;
    public boolean l;
    public boolean m;
    public boolean n;
    public AbstractC4757sY o;
    public boolean p;
    public boolean q;
    public final C3800mv r;
    public final Map s;
    public final Y6 t;
    public ArrayList u = new ArrayList();

    public JA1(C2313eB1 eb1, C3800mv mvVar, Map map, UV uv, Y6 y6, Lock lock, Context context) {
        this.f8277a = eb1;
        this.r = mvVar;
        this.s = map;
        this.d = uv;
        this.t = y6;
        this.b = lock;
        this.c = context;
    }

    public final boolean a() {
        int i2 = this.h - 1;
        this.h = i2;
        if (i2 > 0) {
            return false;
        }
        if (i2 < 0) {
            VA1 va1 = this.f8277a.m;
            Objects.requireNonNull(va1);
            StringWriter stringWriter = new StringWriter();
            va1.k("", null, new PrintWriter(stringWriter), null);
            Log.w("GACConnecting", stringWriter.toString());
            Log.wtf("GACConnecting", "GoogleApiClient received too many callbacks for the given step. Clients may be in an unexpected state; GoogleApiClient will now disconnect.", new Exception());
            o(new ConnectionResult(8, null));
            return false;
        }
        ConnectionResult connectionResult = this.e;
        if (connectionResult == null) {
            return true;
        }
        this.f8277a.l = this.f;
        o(connectionResult);
        return false;
    }

    @Override // defpackage.AbstractC1801bB1
    public final AbstractC4439qg b(AbstractC4439qg qgVar) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }

    @Override // defpackage.AbstractC1801bB1
    public final void c(int i2) {
        o(new ConnectionResult(8, null));
    }

    @Override // defpackage.AbstractC1801bB1
    public final void d(ConnectionResult connectionResult, C2470f7 f7Var, boolean z) {
        if (n(1)) {
            l(connectionResult, f7Var, z);
            if (a()) {
                i();
            }
        }
    }

    @Override // defpackage.AbstractC1801bB1
    public final boolean disconnect() {
        k();
        m(true);
        this.f8277a.g(null);
        return true;
    }

    @Override // defpackage.AbstractC1801bB1
    public final void e() {
    }

    @Override // defpackage.AbstractC1801bB1
    public final void f(Bundle bundle) {
        if (n(1)) {
            if (bundle != null) {
                this.i.putAll(bundle);
            }
            if (a()) {
                i();
            }
        }
    }

    @Override // defpackage.AbstractC1801bB1
    public final void g() {
        this.f8277a.g.clear();
        this.m = false;
        this.e = null;
        this.g = 0;
        this.l = true;
        this.n = false;
        this.p = false;
        HashMap hashMap = new HashMap();
        for (C2470f7 f7Var : this.s.keySet()) {
            AbstractC2129d7 d7Var = (AbstractC2129d7) this.f8277a.f.get(f7Var.a());
            Objects.requireNonNull(f7Var.f9899a);
            boolean booleanValue = ((Boolean) this.s.get(f7Var)).booleanValue();
            if (d7Var.requiresSignIn()) {
                this.m = true;
                if (booleanValue) {
                    this.j.add(f7Var.a());
                } else {
                    this.l = false;
                }
            }
            hashMap.put(d7Var, new LA1(this, f7Var, booleanValue));
        }
        if (this.m) {
            this.r.i = Integer.valueOf(System.identityHashCode(this.f8277a.m));
            QA1 qa1 = new QA1(this, null);
            Y6 y6 = this.t;
            Context context = this.c;
            Looper looper = this.f8277a.m.g;
            C3800mv mvVar = this.r;
            this.k = (AbstractC5045uB1) y6.b(context, looper, mvVar, mvVar.g, qa1, qa1);
        }
        this.h = this.f8277a.f.size();
        this.u.add(AbstractC2484fB1.f9904a.submit(new KA1(this, hashMap)));
    }

    public final void h() {
        if (this.h == 0) {
            if (!this.m || this.n) {
                ArrayList arrayList = new ArrayList();
                this.g = 1;
                this.h = this.f8277a.f.size();
                for (AbstractC1607a7 a7Var : this.f8277a.f.keySet()) {
                    if (!this.f8277a.g.containsKey(a7Var)) {
                        arrayList.add((AbstractC2129d7) this.f8277a.f.get(a7Var));
                    } else if (a()) {
                        i();
                    }
                }
                if (!arrayList.isEmpty()) {
                    this.u.add(AbstractC2484fB1.f9904a.submit(new PA1(this, arrayList)));
                }
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public final void i() {
        C2313eB1 eb1 = this.f8277a;
        eb1.f9838a.lock();
        try {
            eb1.m.o();
            eb1.k = new GA1(eb1);
            eb1.k.g();
            eb1.b.signalAll();
            eb1.f9838a.unlock();
            AbstractC2484fB1.f9904a.execute(new IA1(this));
            AbstractC5045uB1 ub1 = this.k;
            Bundle bundle = null;
            if (ub1 != null) {
                if (this.p) {
                    AbstractC4757sY sYVar = this.o;
                    boolean z = this.q;
                    C4752sV0 sv0 = (C4752sV0) ub1;
                    Objects.requireNonNull(sv0);
                    try {
                        OB1 ob1 = (OB1) sv0.l();
                        int intValue = sv0.G.intValue();
                        Parcel c2 = ob1.c();
                        int i2 = HB1.f8141a;
                        if (sYVar == null) {
                            c2.writeStrongBinder(null);
                        } else {
                            c2.writeStrongBinder(((AbstractC5564xE1) sYVar).asBinder());
                        }
                        c2.writeInt(intValue);
                        c2.writeInt(z ? 1 : 0);
                        ob1.d(9, c2);
                    } catch (RemoteException unused) {
                        Log.w("SignInClientImpl", "Remote service probably died when saveDefaultAccount is called");
                    }
                }
                m(false);
            }
            for (AbstractC1607a7 a7Var : this.f8277a.g.keySet()) {
                ((AbstractC2129d7) this.f8277a.f.get(a7Var)).disconnect();
            }
            if (!this.i.isEmpty()) {
                bundle = this.i;
            }
            this.f8277a.n.a(bundle);
        } catch (Throwable th) {
            eb1.f9838a.unlock();
            throw th;
        }
    }

    public final void j() {
        this.m = false;
        this.f8277a.m.p = Collections.emptySet();
        for (AbstractC1607a7 a7Var : this.j) {
            if (!this.f8277a.g.containsKey(a7Var)) {
                this.f8277a.g.put(a7Var, new ConnectionResult(17, null));
            }
        }
    }

    public final void k() {
        ArrayList arrayList = this.u;
        int size = arrayList.size();
        int i2 = 0;
        while (i2 < size) {
            Object obj = arrayList.get(i2);
            i2++;
            ((Future) obj).cancel(true);
        }
        this.u.clear();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0021, code lost:
        if (r8 != false) goto L_0x0023;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void l(com.google.android.gms.common.ConnectionResult r6, defpackage.C2470f7 r7, boolean r8) {
        /*
            r5 = this;
            Y6 r0 = r7.f9899a
            java.util.Objects.requireNonNull(r0)
            r0 = 2147483647(0x7fffffff, float:NaN)
            r1 = 0
            r2 = 1
            if (r8 == 0) goto L_0x0023
            boolean r8 = r6.x()
            if (r8 == 0) goto L_0x0014
        L_0x0012:
            r8 = r2
            goto L_0x0021
        L_0x0014:
            UV r8 = r5.d
            int r3 = r6.H
            r4 = 0
            android.content.Intent r8 = r8.a(r4, r3, r4)
            if (r8 == 0) goto L_0x0020
            goto L_0x0012
        L_0x0020:
            r8 = r1
        L_0x0021:
            if (r8 == 0) goto L_0x002c
        L_0x0023:
            com.google.android.gms.common.ConnectionResult r8 = r5.e
            if (r8 == 0) goto L_0x002b
            int r8 = r5.f
            if (r0 >= r8) goto L_0x002c
        L_0x002b:
            r1 = r2
        L_0x002c:
            if (r1 == 0) goto L_0x0032
            r5.e = r6
            r5.f = r0
        L_0x0032:
            eB1 r8 = r5.f8277a
            java.util.Map r8 = r8.g
            a7 r7 = r7.a()
            r8.put(r7, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.JA1.l(com.google.android.gms.common.ConnectionResult, f7, boolean):void");
    }

    public final void m(boolean z) {
        AbstractC5045uB1 ub1 = this.k;
        if (ub1 != null) {
            if (((BaseGmsClient) ub1).a() && z) {
                C4752sV0 sv0 = (C4752sV0) this.k;
                Objects.requireNonNull(sv0);
                try {
                    OB1 ob1 = (OB1) sv0.l();
                    int intValue = sv0.G.intValue();
                    Parcel c2 = ob1.c();
                    c2.writeInt(intValue);
                    ob1.d(7, c2);
                } catch (RemoteException unused) {
                    Log.w("SignInClientImpl", "Remote service probably died when clearAccountFromSessionStore is called");
                }
            }
            this.k.disconnect();
            if (this.r.h) {
                this.k = null;
            }
            this.o = null;
        }
    }

    public final boolean n(int i2) {
        if (this.g == i2) {
            return true;
        }
        VA1 va1 = this.f8277a.m;
        Objects.requireNonNull(va1);
        StringWriter stringWriter = new StringWriter();
        va1.k("", null, new PrintWriter(stringWriter), null);
        Log.w("GACConnecting", stringWriter.toString());
        String valueOf = String.valueOf(this);
        StringBuilder sb = new StringBuilder(valueOf.length() + 23);
        sb.append("Unexpected callback in ");
        sb.append(valueOf);
        Log.w("GACConnecting", sb.toString());
        int i3 = this.h;
        StringBuilder sb2 = new StringBuilder(33);
        sb2.append("mRemainingConnections=");
        sb2.append(i3);
        Log.w("GACConnecting", sb2.toString());
        int i4 = this.g;
        String str = "UNKNOWN";
        String str2 = i4 != 0 ? i4 != 1 ? str : "STEP_GETTING_REMOTE_SERVICE" : "STEP_SERVICE_BINDINGS_AND_SIGN_IN";
        if (i2 == 0) {
            str = "STEP_SERVICE_BINDINGS_AND_SIGN_IN";
        } else if (i2 == 1) {
            str = "STEP_GETTING_REMOTE_SERVICE";
        }
        StringBuilder sb3 = new StringBuilder(str.length() + str2.length() + 70);
        sb3.append("GoogleApiClient connecting is in step ");
        sb3.append(str2);
        sb3.append(" but received callback for step ");
        sb3.append(str);
        Log.e("GACConnecting", sb3.toString(), new Exception());
        o(new ConnectionResult(8, null));
        return false;
    }

    public final void o(ConnectionResult connectionResult) {
        k();
        m(!connectionResult.x());
        this.f8277a.g(connectionResult);
        this.f8277a.n.b(connectionResult);
    }
}
