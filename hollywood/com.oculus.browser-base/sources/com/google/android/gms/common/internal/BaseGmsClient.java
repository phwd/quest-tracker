package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Scope;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class BaseGmsClient {

    /* renamed from: a  reason: collision with root package name */
    public static final Feature[] f9657a = new Feature[0];
    public AtomicInteger A = new AtomicInteger(0);
    public int b;
    public long c;
    public long d;
    public int e;
    public long f;
    public JG1 g;
    public final Context h;
    public final Looper i;
    public final ZF1 j;
    public final Handler k;
    public final Object l = new Object();
    public final Object m = new Object();
    public HY n;
    public AbstractC3071ig o;
    public IInterface p;
    public final ArrayList q = new ArrayList();
    public ServiceConnectionC3926ng r;
    public int s = 1;
    public final MB1 t;
    public final PB1 u;
    public final int v;
    public final String w;
    public ConnectionResult x = null;
    public boolean y = false;
    public volatile zza z = null;

    public BaseGmsClient(Context context, Looper looper, ZF1 zf1, UV uv, int i2, MB1 mb1, PB1 pb1, String str) {
        SE0.i(context, "Context must not be null");
        this.h = context;
        SE0.i(looper, "Looper must not be null");
        this.i = looper;
        SE0.i(zf1, "Supervisor must not be null");
        this.j = zf1;
        SE0.i(uv, "API availability must not be null");
        this.k = new HandlerC3584lg(this, looper);
        this.v = i2;
        this.t = mb1;
        this.u = pb1;
        this.w = str;
    }

    public static boolean v(BaseGmsClient baseGmsClient, int i2, int i3, IInterface iInterface) {
        boolean z2;
        synchronized (baseGmsClient.l) {
            if (baseGmsClient.s != i2) {
                z2 = false;
            } else {
                baseGmsClient.u(i3, iInterface);
                z2 = true;
            }
        }
        return z2;
    }

    public static boolean x(BaseGmsClient baseGmsClient) {
        if (baseGmsClient.y || TextUtils.isEmpty(baseGmsClient.m()) || TextUtils.isEmpty(null)) {
            return false;
        }
        try {
            Class.forName(baseGmsClient.m());
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public boolean a() {
        boolean z2;
        synchronized (this.l) {
            z2 = this.s == 4;
        }
        return z2;
    }

    public Bundle b() {
        return null;
    }

    public final void c() {
        if (!a()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    public void d(AbstractC3071ig igVar) {
        SE0.i(igVar, "Connection progress callbacks cannot be null.");
        this.o = igVar;
        u(2, null);
    }

    public void disconnect() {
        this.A.incrementAndGet();
        synchronized (this.q) {
            int size = this.q.size();
            for (int i2 = 0; i2 < size; i2++) {
                AbstractC3413kg kgVar = (AbstractC3413kg) this.q.get(i2);
                synchronized (kgVar) {
                    kgVar.f10293a = null;
                }
            }
            this.q.clear();
        }
        synchronized (this.m) {
            this.n = null;
        }
        u(1, null);
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int i2;
        IInterface iInterface;
        HY hy;
        synchronized (this.l) {
            i2 = this.s;
            iInterface = this.p;
        }
        synchronized (this.m) {
            hy = this.n;
        }
        printWriter.append((CharSequence) str).append("mConnectState=");
        if (i2 == 1) {
            printWriter.print("DISCONNECTED");
        } else if (i2 == 2) {
            printWriter.print("REMOTE_CONNECTING");
        } else if (i2 == 3) {
            printWriter.print("LOCAL_CONNECTING");
        } else if (i2 == 4) {
            printWriter.print("CONNECTED");
        } else if (i2 != 5) {
            printWriter.print("UNKNOWN");
        } else {
            printWriter.print("DISCONNECTING");
        }
        printWriter.append(" mService=");
        if (iInterface == null) {
            printWriter.append("null");
        } else {
            printWriter.append((CharSequence) m()).append("@").append((CharSequence) Integer.toHexString(System.identityHashCode(iInterface.asBinder())));
        }
        printWriter.append(" mServiceBroker=");
        if (hy == null) {
            printWriter.println("null");
        } else {
            printWriter.append("IGmsServiceBroker@").println(Integer.toHexString(System.identityHashCode(hy.f8162a)));
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
        if (this.d > 0) {
            PrintWriter append = printWriter.append((CharSequence) str).append("lastConnectedTime=");
            long j2 = this.d;
            String format = simpleDateFormat.format(new Date(this.d));
            StringBuilder sb = new StringBuilder(String.valueOf(format).length() + 21);
            sb.append(j2);
            sb.append(" ");
            sb.append(format);
            append.println(sb.toString());
        }
        if (this.c > 0) {
            printWriter.append((CharSequence) str).append("lastSuspendedCause=");
            int i3 = this.b;
            if (i3 == 1) {
                printWriter.append("CAUSE_SERVICE_DISCONNECTED");
            } else if (i3 != 2) {
                printWriter.append((CharSequence) String.valueOf(i3));
            } else {
                printWriter.append("CAUSE_NETWORK_LOST");
            }
            PrintWriter append2 = printWriter.append(" lastSuspendedTime=");
            long j3 = this.c;
            String format2 = simpleDateFormat.format(new Date(this.c));
            StringBuilder sb2 = new StringBuilder(String.valueOf(format2).length() + 21);
            sb2.append(j3);
            sb2.append(" ");
            sb2.append(format2);
            append2.println(sb2.toString());
        }
        if (this.f > 0) {
            printWriter.append((CharSequence) str).append("lastFailedStatus=").append((CharSequence) AbstractC1924bw.a(this.e));
            PrintWriter append3 = printWriter.append(" lastFailedTime=");
            long j4 = this.f;
            String format3 = simpleDateFormat.format(new Date(this.f));
            StringBuilder sb3 = new StringBuilder(String.valueOf(format3).length() + 21);
            sb3.append(j4);
            sb3.append(" ");
            sb3.append(format3);
            append3.println(sb3.toString());
        }
    }

    public abstract IInterface e(IBinder iBinder);

    public Account f() {
        return null;
    }

    public Feature[] g() {
        return f9657a;
    }

    public int getMinApkVersion() {
        return UV.f9031a;
    }

    public String h() {
        JG1 jg1;
        if (!a() || (jg1 = this.g) == null) {
            throw new RuntimeException("Failed to connect when checking package");
        }
        Objects.requireNonNull(jg1);
        return "com.google.android.gms";
    }

    public Bundle i() {
        return new Bundle();
    }

    public void j(AbstractC4757sY sYVar, Set set) {
        Bundle i2 = i();
        GetServiceRequest getServiceRequest = new GetServiceRequest(this.v);
        getServiceRequest.I = this.h.getPackageName();
        getServiceRequest.L = i2;
        if (set != null) {
            getServiceRequest.K = (Scope[]) set.toArray(new Scope[set.size()]);
        }
        if (requiresSignIn()) {
            getServiceRequest.M = f() != null ? f() : new Account("<<default account>>", "com.google");
            if (sYVar != null) {
                getServiceRequest.f9658J = ((AbstractC5564xE1) sYVar).f11600a;
            }
        }
        getServiceRequest.N = f9657a;
        getServiceRequest.O = g();
        try {
            synchronized (this.m) {
                HY hy = this.n;
                if (hy != null) {
                    hy.c(new BinderC3755mg(this, this.A.get()), getServiceRequest);
                } else {
                    Log.w("GmsClient", "mServiceBroker is null, client disconnected");
                }
            }
        } catch (DeadObjectException e2) {
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e2);
            Handler handler = this.k;
            handler.sendMessage(handler.obtainMessage(6, this.A.get(), 1));
        } catch (SecurityException e3) {
            throw e3;
        } catch (RemoteException | RuntimeException e4) {
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e4);
            r(8, null, null, this.A.get());
        }
    }

    public Set k() {
        return Collections.EMPTY_SET;
    }

    public final IInterface l() {
        IInterface iInterface;
        synchronized (this.l) {
            if (this.s != 5) {
                c();
                SE0.k(this.p != null, "Client is connected but service is null");
                iInterface = this.p;
            } else {
                throw new DeadObjectException();
            }
        }
        return iInterface;
    }

    public abstract String m();

    public abstract String n();

    public boolean o() {
        boolean z2;
        synchronized (this.l) {
            int i2 = this.s;
            if (i2 != 2) {
                if (i2 != 3) {
                    z2 = false;
                }
            }
            z2 = true;
        }
        return z2;
    }

    public void p() {
        this.d = System.currentTimeMillis();
    }

    public void q(ConnectionResult connectionResult) {
        this.e = connectionResult.H;
        this.f = System.currentTimeMillis();
    }

    public void r(int i2, IBinder iBinder, Bundle bundle, int i3) {
        Handler handler = this.k;
        handler.sendMessage(handler.obtainMessage(1, i3, -1, new C4268pg(this, i2, iBinder, bundle)));
    }

    public boolean requiresSignIn() {
        return false;
    }

    public void s() {
    }

    public final void t(int i2, int i3) {
        Handler handler = this.k;
        handler.sendMessage(handler.obtainMessage(7, i3, -1, new C4097og(this, i2)));
    }

    public final void u(int i2, IInterface iInterface) {
        JG1 jg1;
        SE0.a((i2 == 4) == (iInterface != null));
        synchronized (this.l) {
            this.s = i2;
            this.p = iInterface;
            s();
            if (i2 == 1) {
                ServiceConnectionC3926ng ngVar = this.r;
                if (ngVar != null) {
                    ZF1 zf1 = this.j;
                    String str = this.g.f8280a;
                    String y2 = y();
                    Objects.requireNonNull(zf1);
                    zf1.c(new NV(str, "com.google.android.gms", 129), ngVar, y2);
                    this.r = null;
                }
            } else if (i2 == 2 || i2 == 3) {
                if (!(this.r == null || (jg1 = this.g) == null)) {
                    String str2 = jg1.f8280a;
                    StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 70 + "com.google.android.gms".length());
                    sb.append("Calling connect() while still connected, missing disconnect() for ");
                    sb.append(str2);
                    sb.append(" on ");
                    sb.append("com.google.android.gms");
                    Log.e("GmsClient", sb.toString());
                    ZF1 zf12 = this.j;
                    String str3 = this.g.f8280a;
                    ServiceConnectionC3926ng ngVar2 = this.r;
                    String y3 = y();
                    Objects.requireNonNull(zf12);
                    zf12.c(new NV(str3, "com.google.android.gms", 129), ngVar2, y3);
                    this.A.incrementAndGet();
                }
                this.r = new ServiceConnectionC3926ng(this, this.A.get());
                String n2 = n();
                this.g = new JG1("com.google.android.gms", n2, false, false);
                ZF1 zf13 = this.j;
                ServiceConnectionC3926ng ngVar3 = this.r;
                String y4 = y();
                Objects.requireNonNull(this.g);
                if (!zf13.b(new NV(n2, "com.google.android.gms", 129, false), ngVar3, y4)) {
                    String str4 = this.g.f8280a;
                    StringBuilder sb2 = new StringBuilder(String.valueOf(str4).length() + 34 + "com.google.android.gms".length());
                    sb2.append("unable to connect to service: ");
                    sb2.append(str4);
                    sb2.append(" on ");
                    sb2.append("com.google.android.gms");
                    Log.e("GmsClient", sb2.toString());
                    t(16, this.A.get());
                }
            } else if (i2 == 4) {
                p();
            }
        }
    }

    public final void w() {
        boolean z2;
        int i2;
        synchronized (this.l) {
            z2 = this.s == 3;
        }
        if (z2) {
            i2 = 5;
            this.y = true;
        } else {
            i2 = 4;
        }
        Handler handler = this.k;
        handler.sendMessage(handler.obtainMessage(i2, this.A.get(), 16));
    }

    public final String y() {
        String str = this.w;
        return str == null ? this.h.getClass().getName() : str;
    }
}
