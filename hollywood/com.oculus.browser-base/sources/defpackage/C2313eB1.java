package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/* renamed from: eB1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2313eB1 implements AbstractC4534rB1, AbstractC1624aC1 {

    /* renamed from: a  reason: collision with root package name */
    public final Lock f9838a;
    public final Condition b;
    public final Context c;
    public final UV d;
    public final HandlerC2655gB1 e;
    public final Map f;
    public final Map g = new HashMap();
    public final C3800mv h;
    public final Map i;
    public final Y6 j;
    public volatile AbstractC1801bB1 k;
    public int l;
    public final VA1 m;
    public final AbstractC4705sB1 n;

    public C2313eB1(Context context, VA1 va1, Lock lock, Looper looper, UV uv, Map map, C3800mv mvVar, Map map2, Y6 y6, ArrayList arrayList, AbstractC4705sB1 sb1) {
        this.c = context;
        this.f9838a = lock;
        this.d = uv;
        this.f = map;
        this.h = mvVar;
        this.i = map2;
        this.j = y6;
        this.m = va1;
        this.n = sb1;
        int size = arrayList.size();
        int i2 = 0;
        while (i2 < size) {
            Object obj = arrayList.get(i2);
            i2++;
            ((XB1) obj).c = this;
        }
        this.e = new HandlerC2655gB1(this, looper);
        this.b = lock.newCondition();
        this.k = new SA1(this);
    }

    @Override // defpackage.AbstractC4534rB1
    public final boolean a() {
        return this.k instanceof GA1;
    }

    @Override // defpackage.AbstractC4534rB1
    public final AbstractC4439qg b(AbstractC4439qg qgVar) {
        qgVar.i();
        return this.k.b(qgVar);
    }

    @Override // defpackage.AbstractC0482Hx
    public final void c(int i2) {
        this.f9838a.lock();
        try {
            this.k.c(i2);
        } finally {
            this.f9838a.unlock();
        }
    }

    @Override // defpackage.AbstractC1624aC1
    public final void d(ConnectionResult connectionResult, C2470f7 f7Var, boolean z) {
        this.f9838a.lock();
        try {
            this.k.d(connectionResult, f7Var, z);
        } finally {
            this.f9838a.unlock();
        }
    }

    @Override // defpackage.AbstractC4534rB1
    public final void disconnect() {
        if (this.k.disconnect()) {
            this.g.clear();
        }
    }

    @Override // defpackage.AbstractC4534rB1
    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        String concat = String.valueOf(str).concat("  ");
        printWriter.append((CharSequence) str).append("mState=").println(this.k);
        for (C2470f7 f7Var : this.i.keySet()) {
            printWriter.append((CharSequence) str).append((CharSequence) f7Var.c).println(":");
            ((AbstractC2129d7) this.f.get(f7Var.a())).dump(concat, fileDescriptor, printWriter, strArr);
        }
    }

    @Override // defpackage.AbstractC4534rB1
    public final void e() {
        this.k.e();
    }

    @Override // defpackage.AbstractC0482Hx
    public final void f(Bundle bundle) {
        this.f9838a.lock();
        try {
            this.k.f(bundle);
        } finally {
            this.f9838a.unlock();
        }
    }

    public final void g(ConnectionResult connectionResult) {
        this.f9838a.lock();
        try {
            this.k = new SA1(this);
            this.k.g();
            this.b.signalAll();
        } finally {
            this.f9838a.unlock();
        }
    }
}
