package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.util.HashMap;
import java.util.Objects;

/* renamed from: ZF1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class ZF1 implements Handler.Callback {
    public static final Object F = new Object();
    public static ZF1 G;
    public final HashMap H = new HashMap();
    public final Context I;

    /* renamed from: J  reason: collision with root package name */
    public final Handler f9333J;
    public final C1030Qx K;
    public final long L;
    public final long M;

    public ZF1(Context context) {
        this.I = context.getApplicationContext();
        this.f9333J = new YG1(context.getMainLooper(), this);
        this.K = C1030Qx.a();
        this.L = 5000;
        this.M = 300000;
    }

    public static ZF1 a(Context context) {
        synchronized (F) {
            if (G == null) {
                G = new ZF1(context.getApplicationContext());
            }
        }
        return G;
    }

    public final boolean b(NV nv, ServiceConnection serviceConnection, String str) {
        boolean z;
        SE0.i(serviceConnection, "ServiceConnection must not be null");
        synchronized (this.H) {
            AG1 ag1 = (AG1) this.H.get(nv);
            if (ag1 == null) {
                ag1 = new AG1(this, nv);
                nv.a(this.I);
                ag1.f7664a.put(serviceConnection, serviceConnection);
                ag1.a(str);
                this.H.put(nv, ag1);
            } else {
                this.f9333J.removeMessages(0, nv);
                if (!ag1.f7664a.containsKey(serviceConnection)) {
                    ZF1 zf1 = ag1.g;
                    C1030Qx qx = zf1.K;
                    ag1.e.a(zf1.I);
                    ag1.f7664a.put(serviceConnection, serviceConnection);
                    int i = ag1.b;
                    if (i == 1) {
                        serviceConnection.onServiceConnected(ag1.f, ag1.d);
                    } else if (i == 2) {
                        ag1.a(str);
                    }
                } else {
                    String valueOf = String.valueOf(nv);
                    StringBuilder sb = new StringBuilder(valueOf.length() + 81);
                    sb.append("Trying to bind a GmsServiceConnection that was already connected before.  config=");
                    sb.append(valueOf);
                    throw new IllegalStateException(sb.toString());
                }
            }
            z = ag1.c;
        }
        return z;
    }

    public final void c(NV nv, ServiceConnection serviceConnection, String str) {
        SE0.i(serviceConnection, "ServiceConnection must not be null");
        synchronized (this.H) {
            AG1 ag1 = (AG1) this.H.get(nv);
            if (ag1 == null) {
                String valueOf = String.valueOf(nv);
                StringBuilder sb = new StringBuilder(valueOf.length() + 50);
                sb.append("Nonexistent connection status for service config: ");
                sb.append(valueOf);
                throw new IllegalStateException(sb.toString());
            } else if (ag1.f7664a.containsKey(serviceConnection)) {
                C1030Qx qx = ag1.g.K;
                ag1.f7664a.remove(serviceConnection);
                if (ag1.f7664a.isEmpty()) {
                    this.f9333J.sendMessageDelayed(this.f9333J.obtainMessage(0, nv), this.L);
                }
            } else {
                String valueOf2 = String.valueOf(nv);
                StringBuilder sb2 = new StringBuilder(valueOf2.length() + 76);
                sb2.append("Trying to unbind a GmsServiceConnection  that was not bound before.  config=");
                sb2.append(valueOf2);
                throw new IllegalStateException(sb2.toString());
            }
        }
    }

    public final boolean handleMessage(Message message) {
        int i = message.what;
        if (i == 0) {
            synchronized (this.H) {
                NV nv = (NV) message.obj;
                AG1 ag1 = (AG1) this.H.get(nv);
                if (ag1 != null && ag1.f7664a.isEmpty()) {
                    if (ag1.c) {
                        ag1.g.f9333J.removeMessages(1, ag1.e);
                        ZF1 zf1 = ag1.g;
                        C1030Qx qx = zf1.K;
                        Context context = zf1.I;
                        Objects.requireNonNull(qx);
                        context.unbindService(ag1);
                        ag1.c = false;
                        ag1.b = 2;
                    }
                    this.H.remove(nv);
                }
            }
            return true;
        } else if (i != 1) {
            return false;
        } else {
            synchronized (this.H) {
                NV nv2 = (NV) message.obj;
                AG1 ag12 = (AG1) this.H.get(nv2);
                if (ag12 != null && ag12.b == 3) {
                    String valueOf = String.valueOf(nv2);
                    StringBuilder sb = new StringBuilder(valueOf.length() + 47);
                    sb.append("Timeout waiting for ServiceConnection callback ");
                    sb.append(valueOf);
                    Log.e("GmsClientSupervisor", sb.toString(), new Exception());
                    ComponentName componentName = ag12.f;
                    if (componentName == null) {
                        componentName = nv2.d;
                    }
                    if (componentName == null) {
                        componentName = new ComponentName(nv2.c, "unknown");
                    }
                    ag12.onServiceDisconnected(componentName);
                }
            }
            return true;
        }
    }
}
