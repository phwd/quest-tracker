package defpackage;

import android.app.ActivityManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.oculus.os.Version;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: cW  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2021cW implements Handler.Callback {
    public static final Status F = new Status(4, "Sign-out occurred while this API call was in progress.");
    public static final Status G = new Status(4, "The user must be signed in to make this API call.");
    public static final Object H = new Object();
    public static C2021cW I;

    /* renamed from: J  reason: collision with root package name */
    public long f9610J = 10000;
    public final Context K;
    public final SV L;
    public final TV M;
    public final AtomicInteger N = new AtomicInteger(1);
    public final AtomicInteger O = new AtomicInteger(0);
    public final Map P = new ConcurrentHashMap(5, 0.75f, 1);
    public final Set Q = new C5271va(0);
    public final Set R = new C5271va(0);
    public final Handler S;

    public C2021cW(Context context, Looper looper, SV sv) {
        this.K = context;
        ZB1 zb1 = new ZB1(looper, this);
        this.S = zb1;
        this.L = sv;
        this.M = new TV(sv);
        zb1.sendMessage(zb1.obtainMessage(6));
    }

    public static C2021cW a(Context context) {
        C2021cW cWVar;
        synchronized (H) {
            if (I == null) {
                HandlerThread handlerThread = new HandlerThread("GoogleApiHandler", 9);
                handlerThread.start();
                Looper looper = handlerThread.getLooper();
                Context applicationContext = context.getApplicationContext();
                Object obj = SV.c;
                I = new C2021cW(applicationContext, looper, SV.d);
            }
            cWVar = I;
        }
        return cWVar;
    }

    public final void b(QV qv) {
        C4861t7 t7Var = qv.d;
        ZV zv = (ZV) this.P.get(t7Var);
        if (zv == null) {
            zv = new ZV(this, qv);
            this.P.put(t7Var, zv);
        }
        if (zv.b()) {
            this.R.add(t7Var);
        }
        zv.a();
    }

    public final boolean c(ConnectionResult connectionResult, int i) {
        PendingIntent pendingIntent;
        SV sv = this.L;
        Context context = this.K;
        Objects.requireNonNull(sv);
        if (connectionResult.x()) {
            pendingIntent = connectionResult.I;
        } else {
            Intent a2 = sv.a(context, connectionResult.H, null);
            if (a2 == null) {
                pendingIntent = null;
            } else {
                pendingIntent = PendingIntent.getActivity(context, 0, a2, 134217728);
            }
        }
        if (pendingIntent == null) {
            return false;
        }
        int i2 = connectionResult.H;
        int i3 = GoogleApiActivity.F;
        Intent intent = new Intent(context, GoogleApiActivity.class);
        intent.putExtra("pending_intent", pendingIntent);
        intent.putExtra("failing_client_id", i);
        intent.putExtra("notify_manager", true);
        sv.k(context, i2, PendingIntent.getActivity(context, 0, intent, 134217728));
        return true;
    }

    public boolean handleMessage(Message message) {
        Status status;
        Feature[] f;
        int i = message.what;
        ZV zv = null;
        long j = 300000;
        int i2 = 0;
        switch (i) {
            case 1:
                if (((Boolean) message.obj).booleanValue()) {
                    j = 10000;
                }
                this.f9610J = j;
                this.S.removeMessages(12);
                for (C4861t7 t7Var : this.P.keySet()) {
                    Handler handler = this.S;
                    handler.sendMessageDelayed(handler.obtainMessage(12, t7Var), this.f9610J);
                }
                break;
            case 2:
                TB1 tb1 = (TB1) message.obj;
                Iterator it = ((C4250pa) tb1.f8945a.keySet()).iterator();
                while (true) {
                    AbstractC1590a10 a10 = (AbstractC1590a10) it;
                    if (!a10.hasNext()) {
                        break;
                    } else {
                        C4861t7 t7Var2 = (C4861t7) a10.next();
                        ZV zv2 = (ZV) this.P.get(t7Var2);
                        if (zv2 == null) {
                            tb1.a(t7Var2, new ConnectionResult(13), null);
                            break;
                        } else if (((BaseGmsClient) zv2.b).a()) {
                            ConnectionResult connectionResult = ConnectionResult.F;
                            ((BaseGmsClient) zv2.b).h();
                            tb1.a(t7Var2, connectionResult, "com.google.android.gms");
                        } else {
                            SE0.c(zv2.m.S);
                            if (zv2.l != null) {
                                SE0.c(zv2.m.S);
                                tb1.a(t7Var2, zv2.l, null);
                            } else {
                                SE0.c(zv2.m.S);
                                zv2.f.add(tb1);
                                zv2.a();
                            }
                        }
                    }
                }
            case 3:
                for (ZV zv3 : this.P.values()) {
                    zv3.m();
                    zv3.a();
                }
                break;
            case 4:
            case Version.VERSION_8 /*{ENCODED_INT: 8}*/:
            case Version.VERSION_13 /*{ENCODED_INT: 13}*/:
                C4875tB1 tb12 = (C4875tB1) message.obj;
                ZV zv4 = (ZV) this.P.get(tb12.c.d);
                if (zv4 == null) {
                    b(tb12.c);
                    zv4 = (ZV) this.P.get(tb12.c.d);
                }
                if (!zv4.b() || this.O.get() == tb12.b) {
                    zv4.g(tb12.f11328a);
                    break;
                } else {
                    tb12.f11328a.a(F);
                    zv4.l();
                    break;
                }
            case 5:
                int i3 = message.arg1;
                ConnectionResult connectionResult2 = (ConnectionResult) message.obj;
                Iterator it2 = this.P.values().iterator();
                while (true) {
                    if (it2.hasNext()) {
                        ZV zv5 = (ZV) it2.next();
                        if (zv5.h == i3) {
                            zv = zv5;
                        }
                    }
                }
                if (zv != null) {
                    SV sv = this.L;
                    int i4 = connectionResult2.H;
                    Objects.requireNonNull(sv);
                    AtomicBoolean atomicBoolean = AbstractC3729mW.f10427a;
                    String B = ConnectionResult.B(i4);
                    String str = connectionResult2.f9654J;
                    StringBuilder sb = new StringBuilder(String.valueOf(str).length() + String.valueOf(B).length() + 69);
                    sb.append("Error resolution was canceled by the user, original error message: ");
                    sb.append(B);
                    sb.append(": ");
                    sb.append(str);
                    zv.p(new Status(17, sb.toString()));
                    break;
                } else {
                    StringBuilder sb2 = new StringBuilder(76);
                    sb2.append("Could not find API instance ");
                    sb2.append(i3);
                    sb2.append(" while trying to fail enqueued calls.");
                    Log.wtf("GoogleApiManager", sb2.toString(), new Exception());
                    break;
                }
            case 6:
                if (this.K.getApplicationContext() instanceof Application) {
                    Application application = (Application) this.K.getApplicationContext();
                    ComponentCallbacks2C0439He he = ComponentCallbacks2C0439He.F;
                    synchronized (he) {
                        if (!he.f8168J) {
                            application.registerActivityLifecycleCallbacks(he);
                            application.registerComponentCallbacks(he);
                            he.f8168J = true;
                        }
                    }
                    C2826hB1 hb1 = new C2826hB1(this);
                    synchronized (he) {
                        he.I.add(hb1);
                    }
                    if (!he.H.get()) {
                        ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
                        ActivityManager.getMyMemoryState(runningAppProcessInfo);
                        if (!he.H.getAndSet(true) && runningAppProcessInfo.importance > 100) {
                            he.G.set(true);
                        }
                    }
                    if (!he.G.get()) {
                        this.f9610J = 300000;
                        break;
                    }
                }
                break;
            case Version.VERSION_7 /*{ENCODED_INT: 7}*/:
                b((QV) message.obj);
                break;
            case Version.VERSION_9 /*{ENCODED_INT: 9}*/:
                if (this.P.containsKey(message.obj)) {
                    ZV zv6 = (ZV) this.P.get(message.obj);
                    SE0.c(zv6.m.S);
                    if (zv6.j) {
                        zv6.a();
                        break;
                    }
                }
                break;
            case Version.VERSION_10 /*{ENCODED_INT: 10}*/:
                for (C4861t7 t7Var3 : this.R) {
                    ((ZV) this.P.remove(t7Var3)).l();
                }
                this.R.clear();
                break;
            case Version.VERSION_11 /*{ENCODED_INT: 11}*/:
                if (this.P.containsKey(message.obj)) {
                    ZV zv7 = (ZV) this.P.get(message.obj);
                    SE0.c(zv7.m.S);
                    if (zv7.j) {
                        zv7.n();
                        C2021cW cWVar = zv7.m;
                        if (cWVar.L.e(cWVar.K) == 18) {
                            status = new Status(8, "Connection timed out while waiting for Google Play services update to complete.");
                        } else {
                            status = new Status(8, "API failed to connect while resuming due to an unknown error.");
                        }
                        zv7.p(status);
                        zv7.b.disconnect();
                        break;
                    }
                }
                break;
            case Version.VERSION_12 /*{ENCODED_INT: 12}*/:
                if (this.P.containsKey(message.obj)) {
                    ((ZV) this.P.get(message.obj)).r(true);
                    break;
                }
                break;
            case Version.VERSION_14 /*{ENCODED_INT: 14}*/:
                C5859z.a(message.obj);
                Objects.requireNonNull(null);
                throw null;
            case Version.VERSION_15 /*{ENCODED_INT: 15}*/:
                C1850bW bWVar = (C1850bW) message.obj;
                if (this.P.containsKey(bWVar.f9544a)) {
                    ZV zv8 = (ZV) this.P.get(bWVar.f9544a);
                    if (zv8.k.contains(bWVar) && !zv8.j) {
                        if (!((BaseGmsClient) zv8.b).a()) {
                            zv8.a();
                            break;
                        } else {
                            zv8.k();
                            break;
                        }
                    }
                }
                break;
            case Version.VERSION_16 /*{ENCODED_INT: 16}*/:
                C1850bW bWVar2 = (C1850bW) message.obj;
                if (this.P.containsKey(bWVar2.f9544a)) {
                    ZV zv9 = (ZV) this.P.get(bWVar2.f9544a);
                    if (zv9.k.remove(bWVar2)) {
                        zv9.m.S.removeMessages(15, bWVar2);
                        zv9.m.S.removeMessages(16, bWVar2);
                        Feature feature = bWVar2.b;
                        ArrayList arrayList = new ArrayList(zv9.f9346a.size());
                        for (AbstractC5385wB1 wb1 : zv9.f9346a) {
                            if ((wb1 instanceof XA1) && (f = ((XA1) wb1).f(zv9)) != null) {
                                int length = f.length;
                                int i5 = 0;
                                while (true) {
                                    if (i5 >= length) {
                                        i5 = -1;
                                    } else if (!AbstractC0895Oq0.a(f[i5], feature)) {
                                        i5++;
                                    }
                                }
                                if (i5 >= 0) {
                                    arrayList.add(wb1);
                                }
                            }
                        }
                        int size = arrayList.size();
                        while (i2 < size) {
                            Object obj = arrayList.get(i2);
                            i2++;
                            AbstractC5385wB1 wb12 = (AbstractC5385wB1) obj;
                            zv9.f9346a.remove(wb12);
                            wb12.b(new Pp1(feature));
                        }
                        break;
                    }
                }
                break;
            default:
                StringBuilder sb3 = new StringBuilder(31);
                sb3.append("Unknown message id: ");
                sb3.append(i);
                Log.w("GoogleApiManager", sb3.toString());
                return false;
        }
        return true;
    }
}
