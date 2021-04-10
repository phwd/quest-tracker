package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/* renamed from: DH1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class DH1 implements ServiceConnection {

    /* renamed from: a  reason: collision with root package name */
    public final Context f7879a;
    public final Intent b;
    public final ScheduledExecutorService c;
    public final Queue d = new ArrayDeque();
    public XG1 e;
    public boolean f = false;

    public DH1(Context context, String str) {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(0, new ThreadFactoryC3945nm0("EnhancedIntentService"));
        Context applicationContext = context.getApplicationContext();
        this.f7879a = applicationContext;
        this.b = new Intent(str).setPackage(applicationContext.getPackageName());
        this.c = scheduledThreadPoolExecutor;
    }

    public final synchronized void a() {
        while (!this.d.isEmpty()) {
            XG1 xg1 = this.e;
            if (xg1 == null || !xg1.isBinderAlive()) {
                Log.isLoggable("EnhancedIntentService", 3);
                if (!this.f) {
                    this.f = true;
                    try {
                        C1030Qx a2 = C1030Qx.a();
                        Context context = this.f7879a;
                        Intent intent = this.b;
                        Objects.requireNonNull(a2);
                        context.getClass().getName();
                        if (!a2.b(context, intent, this, 65)) {
                            Log.e("EnhancedIntentService", "binding to the service failed");
                            this.f = false;
                            b();
                        } else {
                            return;
                        }
                    } catch (SecurityException e2) {
                        Log.e("EnhancedIntentService", "Exception while binding the service", e2);
                    }
                }
                return;
            }
            this.e.a((EG1) this.d.poll());
        }
    }

    public final void b() {
        while (!this.d.isEmpty()) {
            ((EG1) this.d.poll()).a();
        }
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        synchronized (this) {
            this.f = false;
            this.e = (XG1) iBinder;
            if (Log.isLoggable("EnhancedIntentService", 3)) {
                String.valueOf(componentName).length();
            }
            if (iBinder == null) {
                Log.e("EnhancedIntentService", "Null service connection");
                b();
            } else {
                a();
            }
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            String.valueOf(componentName).length();
        }
        a();
    }
}
