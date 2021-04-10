package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.BaseGmsClient;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.locks.Lock;

/* renamed from: YB1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class YB1 implements AbstractC4534rB1 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f9260a;
    public final VA1 b;
    public final C2313eB1 c;
    public final C2313eB1 d;
    public final Map e;
    public final Set f = Collections.newSetFromMap(new WeakHashMap());
    public final AbstractC2129d7 g;
    public Bundle h;
    public ConnectionResult i = null;
    public ConnectionResult j = null;
    public boolean k = false;
    public final Lock l;
    public int m = 0;

    public YB1(Context context, VA1 va1, Lock lock, Looper looper, UV uv, Map map, Map map2, C3800mv mvVar, Y6 y6, AbstractC2129d7 d7Var, ArrayList arrayList, ArrayList arrayList2, Map map3, Map map4) {
        this.f9260a = context;
        this.b = va1;
        this.l = lock;
        this.g = null;
        this.c = new C2313eB1(context, va1, lock, looper, uv, map2, null, map4, null, arrayList2, new C1804bC1(this, null));
        this.d = new C2313eB1(context, va1, lock, looper, uv, map, mvVar, map3, y6, arrayList, new C2146dC1(this, null));
        C4931ta taVar = new C4931ta();
        Iterator it = ((C4250pa) ((C4931ta) map2).keySet()).iterator();
        while (it.hasNext()) {
            taVar.put((AbstractC1607a7) it.next(), this.c);
        }
        Iterator it2 = ((C4250pa) ((C4931ta) map).keySet()).iterator();
        while (it2.hasNext()) {
            taVar.put((AbstractC1607a7) it2.next(), this.d);
        }
        this.e = Collections.unmodifiableMap(taVar);
    }

    public static void d(YB1 yb1) {
        ConnectionResult connectionResult;
        if (f(yb1.i)) {
            if (f(yb1.j) || yb1.h()) {
                int i2 = yb1.m;
                if (i2 != 1) {
                    if (i2 != 2) {
                        Log.wtf("CompositeGAC", "Attempted to call success callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new AssertionError());
                        yb1.m = 0;
                        return;
                    }
                    yb1.b.a(yb1.h);
                }
                yb1.g();
                yb1.m = 0;
                return;
            }
            ConnectionResult connectionResult2 = yb1.j;
            if (connectionResult2 == null) {
                return;
            }
            if (yb1.m == 1) {
                yb1.g();
                return;
            }
            yb1.c(connectionResult2);
            yb1.c.disconnect();
        } else if (yb1.i == null || !f(yb1.j)) {
            ConnectionResult connectionResult3 = yb1.i;
            if (connectionResult3 != null && (connectionResult = yb1.j) != null) {
                if (yb1.d.l < yb1.c.l) {
                    connectionResult3 = connectionResult;
                }
                yb1.c(connectionResult3);
            }
        } else {
            yb1.d.disconnect();
            yb1.c(yb1.i);
        }
    }

    public static boolean f(ConnectionResult connectionResult) {
        return connectionResult != null && connectionResult.A();
    }

    @Override // defpackage.AbstractC4534rB1
    public final boolean a() {
        this.l.lock();
        try {
            boolean z = true;
            if (!(this.c.k instanceof GA1) || (!(this.d.k instanceof GA1) && !h() && this.m != 1)) {
                z = false;
            }
            return z;
        } finally {
            this.l.unlock();
        }
    }

    @Override // defpackage.AbstractC4534rB1
    public final AbstractC4439qg b(AbstractC4439qg qgVar) {
        AbstractC1607a7 a7Var = qgVar.o;
        SE0.b(this.e.containsKey(a7Var), "GoogleApiClient is not configured to use the API required for this call.");
        if (!((C2313eB1) this.e.get(a7Var)).equals(this.d)) {
            return this.c.b(qgVar);
        }
        if (!h()) {
            return this.d.b(qgVar);
        }
        if (this.g == null) {
            qgVar.l(new Status(1, 4, null, null));
            return qgVar;
        }
        System.identityHashCode(this.b);
        Objects.requireNonNull((BaseGmsClient) this.g);
        throw new UnsupportedOperationException("Not a sign in API");
    }

    public final void c(ConnectionResult connectionResult) {
        int i2 = this.m;
        if (i2 != 1) {
            if (i2 != 2) {
                Log.wtf("CompositeGAC", "Attempted to call failure callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new Exception());
                this.m = 0;
            }
            this.b.b(connectionResult);
        }
        g();
        this.m = 0;
    }

    @Override // defpackage.AbstractC4534rB1
    public final void disconnect() {
        this.j = null;
        this.i = null;
        this.m = 0;
        this.c.disconnect();
        this.d.disconnect();
        g();
    }

    @Override // defpackage.AbstractC4534rB1
    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.append((CharSequence) str).append("authClient").println(":");
        this.d.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
        printWriter.append((CharSequence) str).append("anonClient").println(":");
        this.c.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
    }

    @Override // defpackage.AbstractC4534rB1
    public final void e() {
        this.m = 2;
        this.k = false;
        this.j = null;
        this.i = null;
        this.c.k.e();
        this.d.k.e();
    }

    public final void g() {
        for (AbstractC4922tV0 tv0 : this.f) {
            tv0.a();
        }
        this.f.clear();
    }

    public final boolean h() {
        ConnectionResult connectionResult = this.j;
        return connectionResult != null && connectionResult.H == 4;
    }
}
