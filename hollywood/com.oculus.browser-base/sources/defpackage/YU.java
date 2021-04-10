package defpackage;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.Messenger;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.gcm.PendingCallback;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: YU  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class YU extends Service {
    public final Object F = new Object();
    public int G;
    public ExecutorService H;
    public Messenger I;

    /* renamed from: J  reason: collision with root package name */
    public ComponentName f9275J;
    public VU K;
    public XH1 L;

    public void a() {
    }

    public abstract int b(C2217df1 df1);

    public final void c(int i) {
        synchronized (this.F) {
            this.G = i;
            if (!this.K.c(this.f9275J.getClassName())) {
                stopSelf(this.G);
            }
        }
    }

    public final boolean d(String str) {
        boolean z;
        boolean z2;
        synchronized (this.F) {
            VU vu = this.K;
            String className = this.f9275J.getClassName();
            synchronized (vu) {
                Map map = (Map) vu.c.get(className);
                if (map == null) {
                    map = new C4931ta();
                    vu.c.put(className, map);
                }
                z = map.put(str, Boolean.FALSE) == null;
            }
            z2 = !z;
            if (z2) {
                String packageName = getPackageName();
                StringBuilder sb = new StringBuilder(String.valueOf(packageName).length() + 44 + String.valueOf(str).length());
                sb.append(packageName);
                sb.append(" ");
                sb.append(str);
                sb.append(": Task already running, won't start another");
                Log.w("GcmTaskService", sb.toString());
            }
        }
        return z2;
    }

    public IBinder onBind(Intent intent) {
        if (intent == null || !"com.google.android.gms.gcm.ACTION_TASK_READY".equals(intent.getAction())) {
            return null;
        }
        return this.I.getBinder();
    }

    public void onCreate() {
        super.onCreate();
        this.K = VU.a(this);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ThreadFactoryC1987cG1());
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        this.H = Executors.unconfigurableExecutorService(threadPoolExecutor);
        this.I = new Messenger(new WU(this, Looper.getMainLooper()));
        this.f9275J = new ComponentName(this, getClass());
        this.L = PH1.f8681a;
    }

    public void onDestroy() {
        super.onDestroy();
        List<Runnable> shutdownNow = this.H.shutdownNow();
        if (!shutdownNow.isEmpty()) {
            int size = shutdownNow.size();
            StringBuilder sb = new StringBuilder(79);
            sb.append("Shutting down, but not all tasks are finished executing. Remaining: ");
            sb.append(size);
            Log.e("GcmTaskService", sb.toString());
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent == null) {
            c(i2);
            return 2;
        }
        try {
            intent.setExtrasClassLoader(PendingCallback.class.getClassLoader());
            String action = intent.getAction();
            if ("com.google.android.gms.gcm.ACTION_TASK_READY".equals(action)) {
                String stringExtra = intent.getStringExtra("tag");
                Parcelable parcelableExtra = intent.getParcelableExtra("callback");
                Bundle bundleExtra = intent.getBundleExtra("extras");
                ArrayList parcelableArrayListExtra = intent.getParcelableArrayListExtra("triggered_uris");
                long longExtra = intent.getLongExtra("max_exec_duration", 180);
                if (!(parcelableExtra instanceof PendingCallback)) {
                    String packageName = getPackageName();
                    StringBuilder sb = new StringBuilder(String.valueOf(packageName).length() + 47 + String.valueOf(stringExtra).length());
                    sb.append(packageName);
                    sb.append(" ");
                    sb.append(stringExtra);
                    sb.append(": Could not process request, invalid callback.");
                    Log.e("GcmTaskService", sb.toString());
                    return 2;
                } else if (d(stringExtra)) {
                    c(i2);
                    return 2;
                } else {
                    XU xu = new XU(this, stringExtra, ((PendingCallback) parcelableExtra).F, bundleExtra, longExtra, parcelableArrayListExtra);
                    try {
                        this.H.execute(xu);
                    } catch (RejectedExecutionException e) {
                        Log.e("GcmTaskService", "Executor is shutdown. onDestroy was called but main looper had an unprocessed start task message. The task will be retried with backoff delay.", e);
                        xu.a(1);
                    }
                }
            } else if ("com.google.android.gms.gcm.SERVICE_ACTION_INITIALIZE".equals(action)) {
                a();
            } else {
                StringBuilder sb2 = new StringBuilder(String.valueOf(action).length() + 37);
                sb2.append("Unknown action received ");
                sb2.append(action);
                sb2.append(", terminating");
                Log.e("GcmTaskService", sb2.toString());
            }
            c(i2);
            return 2;
        } finally {
            c(i2);
        }
    }
}
