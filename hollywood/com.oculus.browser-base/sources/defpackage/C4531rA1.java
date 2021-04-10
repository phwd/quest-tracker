package defpackage;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.IInterface;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: rA1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4531rA1 {

    /* renamed from: a  reason: collision with root package name */
    public static final Map f11188a = Collections.synchronizedMap(new HashMap());
    public final Context b;
    public final Rz1 c;
    public final String d;
    public final List e = new ArrayList();
    public boolean f;
    public final Intent g;
    public final AbstractC5722yA1 h;
    public final WeakReference i;
    public final IBinder.DeathRecipient j = new C5042uA1(this);
    public ServiceConnection k;
    public IInterface l;

    public C4531rA1(Context context, Rz1 rz1, String str, Intent intent, AbstractC5722yA1 ya1) {
        this.b = context;
        this.c = rz1;
        this.d = str;
        this.g = intent;
        this.h = ya1;
        this.i = new WeakReference(null);
    }

    public static void b(C4531rA1 ra1, AbstractRunnableC4702sA1 sa1) {
        if (ra1.l == null && !ra1.f) {
            ra1.c.a(4, "Initiate binding to the service.", new Object[0]);
            ra1.e.add(sa1);
            ServiceConnectionC5552xA1 xa1 = new ServiceConnectionC5552xA1(ra1, (byte) 0);
            ra1.k = xa1;
            ra1.f = true;
            if (!ra1.b.bindService(ra1.g, xa1, 1)) {
                ra1.c.a(4, "Failed to bind to the service.", new Object[0]);
                ra1.f = false;
                for (AbstractRunnableC4702sA1 sa12 : ra1.e) {
                    C2140dA1 da1 = sa12.F;
                    if (da1 != null) {
                        da1.a(new Mz1());
                    }
                }
                ra1.e.clear();
            }
        } else if (ra1.f) {
            ra1.c.a(4, "Waiting to bind to the service.", new Object[0]);
            ra1.e.add(sa1);
        } else {
            sa1.run();
        }
    }

    public final void a() {
        d(new C5382wA1(this));
    }

    public final void c(AbstractRunnableC4702sA1 sa1) {
        d(new C4872tA1(this, sa1.F, sa1));
    }

    public final void d(AbstractRunnableC4702sA1 sa1) {
        Handler handler;
        Map map = f11188a;
        synchronized (map) {
            if (!map.containsKey(this.d)) {
                HandlerThread handlerThread = new HandlerThread(this.d, 10);
                handlerThread.start();
                map.put(this.d, new Handler(handlerThread.getLooper()));
            }
            handler = (Handler) map.get(this.d);
        }
        handler.post(sa1);
    }
}
