package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;

/* renamed from: xo  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5653xo {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f11634a = new Object();
    public static final int[] b = new int[4];
    public static final AtomicInteger c = new AtomicInteger();
    public static boolean d;
    public int A;
    public int B;
    public int C;
    public boolean D;
    public int E;
    public int F;
    public boolean G;
    public int[] H;
    public AbstractC2737gi0 I;

    /* renamed from: J  reason: collision with root package name */
    public String f11635J;
    public boolean K;
    public final Handler e = new Handler();
    public final Executor f = new ExecutorC3095io(this);
    public ComponentName g;
    public final ComponentName h;
    public final Bundle i;
    public final boolean j;
    public AbstractC5483wo k;
    public C5313vo l;
    public C0098Bo m;
    public AbstractC5267vY n;
    public boolean o;
    public boolean p;
    public boolean q;
    public int r;
    public final C4121oo s;
    public final C4633ro t;
    public final String u;
    public final boolean v;
    public AbstractC2074cp w;
    public AbstractC2074cp x;
    public AbstractC2074cp y;
    public int z;

    public C5653xo(Context context, ComponentName componentName, ComponentName componentName2, boolean z2, boolean z3, Bundle bundle, String str) {
        this.g = componentName;
        this.h = componentName2;
        bundle = bundle == null ? new Bundle() : bundle;
        this.i = bundle;
        bundle.putBoolean("org.chromium.base.process_launcher.extra.bind_to_caller", z2);
        bundle.putString("org.chromium.base.process_launcher.extra.browser_package_name", AbstractC0456Hk.f8178a.d);
        this.j = z2;
        this.u = str;
        this.v = z3;
        this.s = new C4121oo(this, context);
        this.t = new C4633ro(this);
        c((!d || componentName2 == null) ? this.g : componentName2);
    }

    public static String e() {
        ClassLoader classLoader = C5653xo.class.getClassLoader();
        return classLoader.toString() + classLoader.hashCode();
    }

    public void a() {
        if (!g()) {
            AbstractC1220Ua0.f("ChildProcessConn", "The connection is not bound for %d", Integer.valueOf(this.r));
            return;
        }
        if (this.A == 0) {
            ((ServiceConnectionC2244dp) this.x).a();
            o();
        }
        this.A++;
    }

    public final boolean b(boolean z2) {
        boolean z3;
        if (z2) {
            z3 = ((ServiceConnectionC2244dp) this.w).a();
        } else {
            this.A++;
            z3 = ((ServiceConnectionC2244dp) this.x).a();
        }
        if (!z3) {
            return false;
        }
        if (!d && this.h != null) {
            this.e.postDelayed(new RunnableC3608lo(this), 10000);
        }
        ((ServiceConnectionC2244dp) this.y).a();
        o();
        return true;
    }

    public final void c(ComponentName componentName) {
        Intent intent = new Intent();
        intent.setComponent(componentName);
        Bundle bundle = this.i;
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        int i2 = (this.v ? Integer.MIN_VALUE : 0) | 1;
        this.x = this.s.a(intent, i2, this.t, this.u);
        this.w = this.s.a(intent, i2 | 64, this.t, this.u);
        this.y = this.s.a(intent, i2 | 32, this.t, this.u);
    }

    public final void d() {
        try {
            TraceEvent.Y("ChildProcessConnection.doConnectionSetup", null);
            BinderC5143uo uoVar = new BinderC5143uo(this);
            try {
                AbstractC5267vY vYVar = this.n;
                C5313vo voVar = this.l;
                vYVar.y(voVar.f11499a, uoVar, voVar.b);
            } catch (RemoteException e2) {
                AbstractC1220Ua0.a("ChildProcessConn", "Failed to setup connection.", e2);
            }
            this.l = null;
        } finally {
            TraceEvent.f0("ChildProcessConnection.doConnectionSetup");
        }
    }

    public boolean f() {
        boolean z2;
        synchronized (f11634a) {
            z2 = this.K;
        }
        return z2;
    }

    public boolean g() {
        return this.n != null;
    }

    public boolean h() {
        boolean z2;
        synchronized (f11634a) {
            z2 = this.G;
        }
        return z2;
    }

    public final void i() {
        AbstractC5483wo woVar = this.k;
        if (woVar != null) {
            this.k = null;
            woVar.a(this);
        }
    }

    public void j(IBinder iBinder) {
        if (!this.o) {
            try {
                AbstractC1220Ua0.d("ChildProcessConn", "onServiceConnected: " + this.g.getShortClassName(), new Object[0]);
                TraceEvent.g0("ChildProcessConnection: process launch", (long) hashCode());
                AbstractC5267vY vYVar = null;
                TraceEvent.Y("ChildProcessConnection.ChildServiceConnection.onServiceConnected", null);
                this.o = true;
                int i2 = AbstractBinderC5097uY.f11417a;
                if (iBinder != null) {
                    IInterface queryLocalInterface = iBinder.queryLocalInterface("org.chromium.base.process_launcher.IChildProcessService");
                    if (queryLocalInterface == null || !(queryLocalInterface instanceof AbstractC5267vY)) {
                        vYVar = new C4927tY(iBinder);
                    } else {
                        vYVar = (AbstractC5267vY) queryLocalInterface;
                    }
                }
                this.n = vYVar;
                if (this.j) {
                    try {
                        if (!vYVar.J(e())) {
                            AbstractC5483wo woVar = this.k;
                            if (woVar != null) {
                                woVar.b(this);
                            }
                            n();
                            return;
                        }
                    } catch (RemoteException e2) {
                        AbstractC1220Ua0.a("ChildProcessConn", "Failed to bind service to connection.", e2);
                        TraceEvent.f0("ChildProcessConnection.ChildServiceConnection.onServiceConnected");
                        return;
                    }
                }
                AbstractC5483wo woVar2 = this.k;
                if (woVar2 != null) {
                    woVar2.c();
                }
                this.p = true;
                if (this.I == null) {
                    C3266jo joVar = new C3266jo(this);
                    ThreadUtils.d(new RunnableC3437ko(joVar));
                    this.I = joVar;
                }
                if (this.l != null) {
                    d();
                }
                TraceEvent.f0("ChildProcessConnection.ChildServiceConnection.onServiceConnected");
            } finally {
                TraceEvent.f0("ChildProcessConnection.ChildServiceConnection.onServiceConnected");
            }
        }
    }

    public void k() {
        if (!this.q) {
            this.q = true;
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(this.r);
            StringBuilder i2 = AbstractC2531fV.i("bindings:");
            i2.append(((ServiceConnectionC2244dp) this.y).h ? "W" : " ");
            i2.append(((ServiceConnectionC2244dp) this.x).h ? "M" : " ");
            i2.append(((ServiceConnectionC2244dp) this.w).h ? "S" : " ");
            synchronized (f11634a) {
                i2.append(" state:");
                i2.append(this.E);
                i2.append(" counts:");
                for (int i3 = 0; i3 < 4; i3++) {
                    i2.append(b[i3]);
                    i2.append(",");
                }
            }
            objArr[1] = i2.toString();
            AbstractC1220Ua0.f("ChildProcessConn", "onServiceDisconnected (crash or killed by oom): pid=%d %s", objArr);
            n();
            i();
            C0098Bo bo = this.m;
            if (bo != null) {
                bo.a(null);
                this.m = null;
            }
        }
    }

    public void l() {
        if (g()) {
            int i2 = this.A - 1;
            this.A = i2;
            if (i2 == 0) {
                ((ServiceConnectionC2244dp) this.x).c();
                o();
            }
        }
    }

    public void m(boolean z2, AbstractC5483wo woVar) {
        try {
            TraceEvent.Y("ChildProcessConnection.start", null);
            this.k = woVar;
            if (!b(z2)) {
                AbstractC1220Ua0.a("ChildProcessConn", "Failed to establish the service connection.", new Object[0]);
                i();
            }
            TraceEvent.f0("ChildProcessConnection.start");
            TraceEvent.l0("ChildProcessConnection: process launch", (long) hashCode());
            AbstractC1220Ua0.d("ChildProcessConn", "ChildProcessConnection.start: " + this.g.getShortClassName() + ", useStrongBinding = " + z2, new Object[0]);
        } catch (Throwable th) {
            TraceEvent.f0("ChildProcessConnection.start");
            TraceEvent.l0("ChildProcessConnection: process launch", (long) hashCode());
            AbstractC1220Ua0.d("ChildProcessConn", "ChildProcessConnection.start: " + this.g.getShortClassName() + ", useStrongBinding = " + z2, new Object[0]);
            throw th;
        }
    }

    public void n() {
        this.n = null;
        this.l = null;
        this.D = true;
        ((ServiceConnectionC2244dp) this.w).c();
        ((ServiceConnectionC2244dp) this.y).c();
        ((ServiceConnectionC2244dp) this.x).c();
        o();
        synchronized (f11634a) {
            this.H = Arrays.copyOf(b, 4);
        }
        AbstractC2737gi0 gi0 = this.I;
        if (gi0 != null) {
            ThreadUtils.d(new RunnableC3779mo(gi0));
            this.I = null;
        }
    }

    public final void o() {
        int i2;
        if (this.D) {
            i2 = 0;
        } else if (((ServiceConnectionC2244dp) this.w).h) {
            i2 = 3;
        } else {
            i2 = ((ServiceConnectionC2244dp) this.x).h ? 2 : 1;
        }
        synchronized (f11634a) {
            int i3 = this.E;
            if (i2 != i3) {
                if (i3 != 0) {
                    int[] iArr = b;
                    iArr[i3] = iArr[i3] - 1;
                }
                if (i2 != 0) {
                    int[] iArr2 = b;
                    iArr2[i2] = iArr2[i2] + 1;
                }
            }
            this.E = i2;
            if (!this.D) {
                this.F = i2;
            }
        }
    }

    public void p(int i2, int i3) {
        if (g()) {
            if (this.B != i2 || this.C != i3) {
                this.B = i2;
                this.C = i3;
                ServiceConnectionC2244dp dpVar = (ServiceConnectionC2244dp) this.y;
                Objects.requireNonNull(dpVar);
                if (AbstractC4952th.c()) {
                    try {
                        C4520r7.c(dpVar.f9809a, dpVar, i2, i3);
                    } catch (IllegalArgumentException e2) {
                        if (!(e2.getCause() instanceof RemoteException)) {
                            throw e2;
                        }
                    }
                    AbstractC4952th.b(dpVar.f9809a, dpVar.b, dpVar, dpVar.c, dpVar.d, dpVar.e, dpVar.g);
                }
            }
        }
    }
}
