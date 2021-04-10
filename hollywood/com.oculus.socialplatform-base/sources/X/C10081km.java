package X;

import android.os.SystemClock;
import androidx.annotation.VisibleForTesting;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.GuardedBy;

/* renamed from: X.1km  reason: invalid class name and case insensitive filesystem */
public final class C10081km {
    @GuardedBy("this")
    @VisibleForTesting
    public int A00 = 0;
    @GuardedBy("this")
    @VisibleForTesting
    public long A01 = 0;
    @GuardedBy("this")
    @VisibleForTesting
    public long A02 = 0;
    @GuardedBy("this")
    @VisibleForTesting
    public AnonymousClass0PZ A03 = null;
    @GuardedBy("this")
    @VisibleForTesting
    public Integer A04 = AnonymousClass007.A00;
    public final AnonymousClass1me A05;
    public final Runnable A06 = new RunnableC10091kn(this);
    public final Executor A07;
    public final int A08;
    public final Runnable A09 = new RunnableC10401ly(this);

    public final void A02() {
        AnonymousClass0PZ r1;
        synchronized (this) {
            r1 = this.A03;
            this.A03 = null;
            this.A00 = 0;
        }
        AnonymousClass0PZ.A04(r1);
    }

    public final boolean A04(AnonymousClass0PZ r3, int i) {
        AnonymousClass0PZ r1;
        if ((i & 1) != 1 && (i & 4) != 4 && !AnonymousClass0PZ.A08(r3)) {
            return false;
        }
        synchronized (this) {
            r1 = this.A03;
            this.A03 = AnonymousClass0PZ.A03(r3);
            this.A00 = i;
        }
        AnonymousClass0PZ.A04(r1);
        return true;
    }

    private void A00(long j) {
        Runnable runnable = this.A09;
        if (j > 0) {
            ScheduledExecutorService scheduledExecutorService = AnonymousClass1mZ.A00;
            if (scheduledExecutorService == null) {
                scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
                AnonymousClass1mZ.A00 = scheduledExecutorService;
            }
            scheduledExecutorService.schedule(runnable, j, TimeUnit.MILLISECONDS);
            return;
        }
        runnable.run();
    }

    public C10081km(Executor executor, AnonymousClass1me r4, int i) {
        this.A07 = executor;
        this.A05 = r4;
        this.A08 = i;
    }

    public static void A01(C10081km r6) {
        long j;
        boolean z;
        long uptimeMillis = SystemClock.uptimeMillis();
        synchronized (r6) {
            if (r6.A04 == AnonymousClass007.A04) {
                j = Math.max(r6.A01 + ((long) r6.A08), uptimeMillis);
                z = true;
                r6.A02 = uptimeMillis;
                r6.A04 = AnonymousClass007.A01;
            } else {
                r6.A04 = AnonymousClass007.A00;
                j = 0;
                z = false;
            }
        }
        if (z) {
            r6.A00(j - uptimeMillis);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003e, code lost:
        if (r1 == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0040, code lost:
        A00(r4 - r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A03() {
        /*
            r6 = this;
            long r2 = android.os.SystemClock.uptimeMillis()
            monitor-enter(r6)
            X.0PZ r5 = r6.A03     // Catch:{ all -> 0x0047 }
            int r1 = r6.A00     // Catch:{ all -> 0x0047 }
            r4 = 1
            r0 = r1 & r4
            if (r0 == r4) goto L_0x0018
            r0 = 4
            r1 = r1 & r0
            if (r1 == r0) goto L_0x0018
            boolean r0 = X.AnonymousClass0PZ.A08(r5)     // Catch:{ all -> 0x0047 }
            if (r0 == 0) goto L_0x0045
        L_0x0018:
            r1 = 0
            if (r4 == 0) goto L_0x0045
            java.lang.Integer r0 = r6.A04     // Catch:{ all -> 0x0047 }
            int r0 = r0.intValue()     // Catch:{ all -> 0x0047 }
            switch(r0) {
                case 0: goto L_0x0025;
                case 1: goto L_0x0024;
                case 2: goto L_0x0037;
                default: goto L_0x0024;
            }     // Catch:{ all -> 0x0047 }
        L_0x0024:
            goto L_0x003b
        L_0x0025:
            long r4 = r6.A01     // Catch:{ all -> 0x0047 }
            int r0 = r6.A08     // Catch:{ all -> 0x0047 }
            long r0 = (long) r0     // Catch:{ all -> 0x0047 }
            long r4 = r4 + r0
            long r4 = java.lang.Math.max(r4, r2)     // Catch:{ all -> 0x0047 }
            r6.A02 = r2     // Catch:{ all -> 0x0047 }
            java.lang.Integer r0 = X.AnonymousClass007.A01     // Catch:{ all -> 0x0047 }
            r6.A04 = r0     // Catch:{ all -> 0x0047 }
            r1 = 1
            goto L_0x003d
        L_0x0037:
            java.lang.Integer r0 = X.AnonymousClass007.A04     // Catch:{ all -> 0x0047 }
            r6.A04 = r0     // Catch:{ all -> 0x0047 }
        L_0x003b:
            r4 = 0
        L_0x003d:
            monitor-exit(r6)     // Catch:{ all -> 0x0047 }
            if (r1 == 0) goto L_0x0044
            long r4 = r4 - r2
            r6.A00(r4)
        L_0x0044:
            return
        L_0x0045:
            monitor-exit(r6)
            return
        L_0x0047:
            r0 = move-exception
            monitor-exit(r6)
            throw r0
            switch-data {0->0x0025, 1->0x0024, 2->0x0037, }
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C10081km.A03():void");
    }
}
