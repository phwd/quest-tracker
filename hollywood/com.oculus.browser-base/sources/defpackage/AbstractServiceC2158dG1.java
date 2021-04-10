package defpackage;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;
import android.util.SparseArray;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: dG1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractServiceC2158dG1 extends Service {
    public final ExecutorService F;
    public Binder G;
    public final Object H = new Object();
    public int I;

    /* renamed from: J  reason: collision with root package name */
    public int f9766J = 0;

    public AbstractServiceC2158dG1() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ThreadFactoryC3945nm0("EnhancedIntentService"));
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        this.F = Executors.unconfigurableExecutorService(threadPoolExecutor);
    }

    public final void a(Intent intent) {
        if (intent != null) {
            SparseArray sparseArray = AbstractC5849yw1.f11711a;
            int intExtra = intent.getIntExtra("androidx.contentpager.content.wakelockid", 0);
            if (!(intExtra == 0 && (intExtra = intent.getIntExtra("android.support.content.wakelockid", 0)) == 0)) {
                SparseArray sparseArray2 = AbstractC5849yw1.f11711a;
                synchronized (sparseArray2) {
                    PowerManager.WakeLock wakeLock = (PowerManager.WakeLock) sparseArray2.get(intExtra);
                    if (wakeLock != null) {
                        wakeLock.release();
                        sparseArray2.remove(intExtra);
                    } else {
                        Log.w("WakefulBroadcastReceiv.", "No active wake lock id #" + intExtra);
                    }
                }
            }
        }
        synchronized (this.H) {
            int i = this.f9766J - 1;
            this.f9766J = i;
            if (i == 0) {
                stopSelfResult(this.I);
            }
        }
    }

    public abstract void handleIntent(Intent intent);

    public final synchronized IBinder onBind(Intent intent) {
        Log.isLoggable("EnhancedIntentService", 3);
        if (this.G == null) {
            this.G = new XG1(this);
        }
        return this.G;
    }

    public final int onStartCommand(Intent intent, int i, int i2) {
        synchronized (this.H) {
            this.I = i2;
            this.f9766J++;
        }
        if (intent == null) {
            a(intent);
            return 2;
        }
        this.F.execute(new RunnableC4208pG1(this, intent, intent));
        return 3;
    }
}
