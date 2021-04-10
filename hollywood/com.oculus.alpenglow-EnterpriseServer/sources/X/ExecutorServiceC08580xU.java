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
/* renamed from: X.0xU  reason: invalid class name and case insensitive filesystem */
public final class ExecutorServiceC08580xU extends AbstractExecutorService implements ExecutorService, ScheduledExecutorService {
    @VisibleForTesting
    public static final String A0B = AnonymousClass006.A05(ExecutorServiceC08580xU.class.getCanonicalName(), ".ACTION_ALARM.");
    public final Handler A00;
    public final String A01;
    @GuardedBy("this")
    public final PriorityQueue<AnonymousClass0yV<?>> A02 = new PriorityQueue<>();
    public final int A03;
    public final AlarmManager A04;
    public final PendingIntent A05;
    public final BroadcastReceiver A06;
    public final Context A07;
    public final RealtimeSinceBootClock A08;
    public final C08110wa A09;
    public final AtomicLong A0A = new AtomicLong(-1);

    public static void A00(ExecutorServiceC08580xU r7) {
        ArrayList<RunnableC08760xm> arrayList;
        synchronized (r7) {
            arrayList = new ArrayList();
            while (true) {
                PriorityQueue<AnonymousClass0yV<?>> priorityQueue = r7.A02;
                if (priorityQueue.isEmpty() || priorityQueue.peek().A00 > SystemClock.elapsedRealtime()) {
                    A01(r7);
                } else {
                    arrayList.add(priorityQueue.remove().A01);
                }
            }
            A01(r7);
        }
        arrayList.size();
        for (RunnableC08760xm r0 : arrayList) {
            r0.run();
        }
    }

    public final void execute(Runnable runnable) {
        A02(this, new RunnableC08760xm(this, runnable, null), SystemClock.elapsedRealtime());
        this.A00.post(new RunnableC09400zM(this));
    }

    public final boolean isShutdown() {
        return false;
    }

    public final boolean isTerminated() {
        return false;
    }

    @GuardedBy("this")
    public static void A01(ExecutorServiceC08580xU r6) {
        PriorityQueue<AnonymousClass0yV<?>> priorityQueue = r6.A02;
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
            AnonymousClass0NK.A0A("WakingExecutorService", e, "Failed to unregister broadcast receiver");
        }
    }

    @Override // java.util.concurrent.ExecutorService
    public final List<Runnable> shutdownNow() {
        throw new UnsupportedOperationException();
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/0xm<*>;J)V */
    public static void A02(ExecutorServiceC08580xU r2, RunnableC08760xm r3, long j) {
        SystemClock.elapsedRealtime();
        synchronized (r2) {
            r2.A02.add(new AnonymousClass0yV<>(r3, j));
            A01(r2);
        }
    }

    public ExecutorServiceC08580xU(C08800xq r5, String str, Context context, RealtimeSinceBootClock realtimeSinceBootClock, Handler handler, C08110wa r10) {
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
        AbstractC09150yk A002 = r5.A00("alarm", AlarmManager.class);
        if (A002.A02()) {
            this.A04 = (AlarmManager) A002.A01();
            this.A03 = Build.VERSION.SDK_INT;
            this.A00 = handler;
            this.A09 = r10;
            Intent intent = new Intent(this.A01);
            intent.setPackage(this.A07.getPackageName());
            this.A05 = PendingIntent.getBroadcast(this.A07, 0, intent, 134217728);
            C08960yC r3 = new C08960yC(this);
            this.A06 = r3;
            this.A07.registerReceiver(r3, new IntentFilter(this.A01), null, handler);
            return;
        }
        throw new IllegalArgumentException("Cannot acquire Alarm service");
    }

    @Override // java.util.concurrent.AbstractExecutorService
    @TargetApi(11)
    public final <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t) {
        return new RunnableFutureC08880xz(this, runnable, t);
    }

    @Override // java.util.concurrent.AbstractExecutorService
    @TargetApi(11)
    public final <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        return new RunnableFutureC08880xz(this, callable);
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final /* bridge */ /* synthetic */ ScheduledFuture schedule(Runnable runnable, long j, TimeUnit timeUnit) {
        RunnableC08760xm r4 = new RunnableC08760xm(this, runnable, null);
        A02(this, r4, SystemClock.elapsedRealtime() + timeUnit.toMillis(j));
        return r4;
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final /* bridge */ /* synthetic */ ScheduledFuture schedule(Callable callable, long j, TimeUnit timeUnit) {
        RunnableC08760xm r4 = new RunnableC08760xm(this, callable);
        A02(this, r4, SystemClock.elapsedRealtime() + timeUnit.toMillis(j));
        return r4;
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public final /* bridge */ /* synthetic */ Future submit(Callable callable) {
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        RunnableC08760xm r4 = new RunnableC08760xm(this, callable);
        A02(this, r4, SystemClock.elapsedRealtime() + timeUnit.toMillis(0));
        this.A00.post(new RunnableC09310zD(this));
        return r4;
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public final /* bridge */ /* synthetic */ Future submit(Runnable runnable, Object obj) {
        RunnableC08760xm r2 = new RunnableC08760xm(this, runnable, obj);
        A02(this, r2, SystemClock.elapsedRealtime());
        this.A00.post(new RunnableC09400zM(this));
        return r2;
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public final /* bridge */ /* synthetic */ Future submit(Runnable runnable) {
        RunnableC08760xm r2 = new RunnableC08760xm(this, runnable, null);
        A02(this, r2, SystemClock.elapsedRealtime());
        this.A00.post(new RunnableC09400zM(this));
        return r2;
    }
}
