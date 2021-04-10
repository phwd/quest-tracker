package X;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.Messenger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class C0 extends Service {
    public Messenger A00;
    public int A01;
    public ExecutorService A02;
    public final Set A03 = new HashSet();

    public final synchronized ExecutorService A04() {
        ExecutorService executorService;
        executorService = this.A02;
        if (executorService == null) {
            executorService = Executors.newFixedThreadPool(2, new ThreadFactoryC0126Bx());
            this.A02 = executorService;
        }
        return executorService;
    }

    public static RunnableC0128Bz A00(C0 c0, String str, C2 c2, Bundle bundle) {
        Set set = c0.A03;
        synchronized (set) {
            if (set.add(str)) {
                return new RunnableC0128Bz(c0, str, c2, bundle);
            }
            C0139Dd.A0P("GcmTaskService", "%s: Task already running, won't start another", c0.getPackageName());
            return null;
        }
    }

    private void A01(int i) {
        Set set = this.A03;
        synchronized (set) {
            this.A01 = i;
            if (set.isEmpty()) {
                stopSelf(this.A01);
            }
        }
    }

    public static void A02(C0 c0, String str) {
        Set set = c0.A03;
        synchronized (set) {
            set.remove(str);
            if (set.isEmpty()) {
                c0.stopSelf(c0.A01);
            }
        }
    }

    public final IBinder onBind(Intent intent) {
        Messenger messenger;
        if (intent == null || !"com.google.android.gms.gcm.ACTION_TASK_READY".equals(intent.getAction())) {
            return null;
        }
        synchronized (this) {
            messenger = this.A00;
            if (messenger == null) {
                messenger = new Messenger(new HandlerC0127By(this, Looper.getMainLooper(), new ComponentName(this, getClass())));
                this.A00 = messenger;
            }
        }
        return messenger.getBinder();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
        X.C1.A00 = java.lang.Boolean.FALSE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0159, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x015a, code lost:
        r3.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x015d, code lost:
        throw r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:40:0x00b4 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int onStartCommand(android.content.Intent r16, int r17, int r18) {
        /*
        // Method dump skipped, instructions count: 457
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0.onStartCommand(android.content.Intent, int, int):int");
    }

    public final void onDestroy() {
        super.onDestroy();
        ExecutorService A04 = A04();
        if (A04 != null) {
            List<Runnable> shutdownNow = A04.shutdownNow();
            if (!shutdownNow.isEmpty()) {
                C0139Dd.A0O("GcmTaskService", "Shutting down, but not all tasks are finished executing. Remaining: %d", Integer.valueOf(shutdownNow.size()));
            }
        }
    }
}
