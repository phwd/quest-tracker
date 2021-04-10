package X;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import com.facebook.rti.common.guavalite.annotations.VisibleForTesting;
import com.facebook.rti.common.time.RealtimeSinceBootClock;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
/* renamed from: X.1X5  reason: invalid class name */
public final class AnonymousClass1X5 extends AbstractExecutorService implements ExecutorService, ScheduledExecutorService {
    @VisibleForTesting
    public static final String A0B = AnonymousClass006.A07(AnonymousClass1X5.class.getCanonicalName(), ".ACTION_ALARM.");
    public final Handler A00;
    public final String A01;
    @GuardedBy("this")
    public final PriorityQueue<AnonymousClass1XC<?>> A02 = new PriorityQueue<>();
    public final int A03;
    public final AlarmManager A04;
    public final PendingIntent A05;
    public final BroadcastReceiver A06;
    public final Context A07;
    public final RealtimeSinceBootClock A08;
    public final C06141Qs A09;
    public final AtomicLong A0A = new AtomicLong(-1);

    public static void A01(AnonymousClass1X5 r7) {
        ArrayList<AnonymousClass1X8> arrayList;
        synchronized (r7) {
            arrayList = new ArrayList();
            while (true) {
                PriorityQueue<AnonymousClass1XC<?>> priorityQueue = r7.A02;
                if (priorityQueue.isEmpty() || priorityQueue.peek().A00 > SystemClock.elapsedRealtime()) {
                    A02(r7);
                } else {
                    arrayList.add(priorityQueue.remove().A01);
                }
            }
            A02(r7);
        }
        arrayList.size();
        for (AnonymousClass1X8 r0 : arrayList) {
            r0.run();
        }
    }

    /* renamed from: A03 */
    public final AbstractScheduledFutureC02560jD<?> schedule(Runnable runnable, long j, TimeUnit timeUnit) {
        AnonymousClass1X8<?> r4 = new AnonymousClass1X8<>(this, runnable, null);
        A00(r4, SystemClock.elapsedRealtime() + timeUnit.toMillis(j));
        return r4;
    }

    public final void execute(Runnable runnable) {
        A00(new AnonymousClass1X8<>(this, runnable, null), SystemClock.elapsedRealtime());
        this.A00.post(new AnonymousClass1XE(this));
    }

    public final boolean isShutdown() {
        return false;
    }

    public final boolean isTerminated() {
        return false;
    }

    @GuardedBy("this")
    public static void A02(AnonymousClass1X5 r6) {
        PriorityQueue<AnonymousClass1XC<?>> priorityQueue = r6.A02;
        if (priorityQueue.isEmpty()) {
            r6.A09.A03(r6.A04, r6.A05);
            return;
        }
        long j = priorityQueue.peek().A00;
        AtomicLong atomicLong = r6.A0A;
        if (atomicLong.get() != j) {
            SystemClock.elapsedRealtime();
            int i = r6.A03;
            if (i >= 23) {
                r6.A09.A02(r6.A04, j, r6.A05);
            } else if (i >= 19) {
                r6.A09.A00(r6.A04, j, r6.A05);
            } else {
                r6.A04.set(2, j, r6.A05);
            }
            atomicLong.set(j);
        }
    }

    @Override // java.util.concurrent.ExecutorService
    public final boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final ScheduledFuture scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final ScheduledFuture scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    public final void shutdown() {
        this.A09.A03(this.A04, this.A05);
        try {
            this.A07.unregisterReceiver(this.A06);
        } catch (IllegalArgumentException e) {
            AnonymousClass0MD.A0D("WakingExecutorService", e, "Failed to unregister broadcast receiver");
        }
    }

    @Override // java.util.concurrent.ExecutorService
    public final List<Runnable> shutdownNow() {
        throw new UnsupportedOperationException();
    }

    public AnonymousClass1X5(AnonymousClass1QQ r5, String str, Context context, RealtimeSinceBootClock realtimeSinceBootClock, Handler handler, C06141Qs r10) {
        StringBuilder sb = new StringBuilder(A0B);
        sb.append(str);
        String packageName = context.getPackageName();
        if (!TextUtils.isEmpty(packageName)) {
            sb.append('.');
            sb.append(packageName);
        }
        this.A01 = sb.toString();
        this.A07 = context;
        this.A08 = realtimeSinceBootClock;
        AnonymousClass1QO A002 = r5.A00("alarm", AlarmManager.class);
        if (A002.A02()) {
            this.A04 = (AlarmManager) A002.A01();
            this.A03 = Build.VERSION.SDK_INT;
            this.A00 = handler;
            this.A09 = r10;
            Intent intent = new Intent(this.A01);
            intent.setPackage(this.A07.getPackageName());
            this.A05 = PendingIntent.getBroadcast(this.A07, 0, intent, 134217728);
            AnonymousClass1X6 r3 = new AnonymousClass1X6(this);
            this.A06 = r3;
            this.A07.registerReceiver(r3, new IntentFilter(this.A01), null, handler);
            return;
        }
        throw new IllegalArgumentException("Cannot acquire Alarm service");
    }

    private void A00(AnonymousClass1X8<?> r3, long j) {
        SystemClock.elapsedRealtime();
        synchronized (this) {
            this.A02.add(new AnonymousClass1XC<>(r3, j));
            A02(this);
        }
    }

    @Override // java.util.concurrent.AbstractExecutorService
    @TargetApi(11)
    public final <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t) {
        return new AnonymousClass1X7(this, runnable, t);
    }

    @Override // java.util.concurrent.AbstractExecutorService
    @TargetApi(11)
    public final <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        return new AnonymousClass1X7(this, callable);
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final /* bridge */ /* synthetic */ ScheduledFuture schedule(Callable callable, long j, TimeUnit timeUnit) {
        AnonymousClass1X8<?> r4 = new AnonymousClass1X8<>(this, callable);
        A00(r4, SystemClock.elapsedRealtime() + timeUnit.toMillis(j));
        return r4;
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public final /* bridge */ /* synthetic */ Future submit(Callable callable) {
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        AnonymousClass1X8<?> r4 = new AnonymousClass1X8<>(this, callable);
        A00(r4, SystemClock.elapsedRealtime() + timeUnit.toMillis(0));
        this.A00.post(new AnonymousClass1XD(this));
        return r4;
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public final /* bridge */ /* synthetic */ Future submit(Runnable runnable, Object obj) {
        AnonymousClass1X8<?> r2 = new AnonymousClass1X8<>(this, runnable, obj);
        A00(r2, SystemClock.elapsedRealtime());
        this.A00.post(new AnonymousClass1XE(this));
        return r2;
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public final /* bridge */ /* synthetic */ Future submit(Runnable runnable) {
        AnonymousClass1X8<?> r2 = new AnonymousClass1X8<>(this, runnable, null);
        A00(r2, SystemClock.elapsedRealtime());
        this.A00.post(new AnonymousClass1XE(this));
        return r2;
    }
}
