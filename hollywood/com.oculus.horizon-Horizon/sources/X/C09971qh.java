package X;

import android.os.SystemClock;
import androidx.annotation.VisibleForTesting;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.GuardedBy;

/* renamed from: X.1qh  reason: invalid class name and case insensitive filesystem */
public final class C09971qh {
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
    public AnonymousClass1qQ A03 = null;
    @GuardedBy("this")
    @VisibleForTesting
    public Integer A04 = AnonymousClass007.A00;
    public final AnonymousClass1uN A05;
    public final Runnable A06 = new AnonymousClass1rT(this);
    public final Executor A07;
    public final int A08;
    public final Runnable A09 = new RunnableC10461ss(this);

    public final void A02() {
        AnonymousClass1qQ r1;
        synchronized (this) {
            r1 = this.A03;
            this.A03 = null;
            this.A00 = 0;
        }
        AnonymousClass1qQ.A03(r1);
    }

    public final boolean A04(AnonymousClass1qQ r3, int i) {
        AnonymousClass1qQ r1;
        if ((i & 1) != 1 && (i & 4) != 4 && !AnonymousClass1qQ.A06(r3)) {
            return false;
        }
        synchronized (this) {
            r1 = this.A03;
            this.A03 = AnonymousClass1qQ.A02(r3);
            this.A00 = i;
        }
        AnonymousClass1qQ.A03(r1);
        return true;
    }

    private void A00(long j) {
        Runnable runnable = this.A09;
        if (j > 0) {
            ScheduledExecutorService scheduledExecutorService = C10531th.A00;
            if (scheduledExecutorService == null) {
                scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
                C10531th.A00 = scheduledExecutorService;
            }
            scheduledExecutorService.schedule(runnable, j, TimeUnit.MILLISECONDS);
            return;
        }
        runnable.run();
    }

    public C09971qh(Executor executor, AnonymousClass1uN r4, int i) {
        this.A07 = executor;
        this.A05 = r4;
        this.A08 = i;
    }

    public static void A01(C09971qh r6) {
        long j;
        boolean z;
        long uptimeMillis = SystemClock.uptimeMillis();
        synchronized (r6) {
            if (r6.A04 == AnonymousClass007.A0D) {
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
        if (r5 == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0040, code lost:
        A00(r1 - r3);
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
            r7 = this;
            long r3 = android.os.SystemClock.uptimeMillis()
            monitor-enter(r7)
            X.1qQ r5 = r7.A03     // Catch:{ all -> 0x0047 }
            int r2 = r7.A00     // Catch:{ all -> 0x0047 }
            r1 = 1
            r0 = r2 & r1
            if (r0 == r1) goto L_0x0018
            r0 = 4
            r2 = r2 & r0
            if (r2 == r0) goto L_0x0018
            boolean r0 = X.AnonymousClass1qQ.A06(r5)     // Catch:{ all -> 0x0047 }
            if (r0 == 0) goto L_0x0045
        L_0x0018:
            r5 = 0
            if (r1 == 0) goto L_0x0045
            java.lang.Integer r0 = r7.A04     // Catch:{ all -> 0x0047 }
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
            long r5 = r7.A01     // Catch:{ all -> 0x0047 }
            int r0 = r7.A08     // Catch:{ all -> 0x0047 }
            long r0 = (long) r0     // Catch:{ all -> 0x0047 }
            long r5 = r5 + r0
            long r1 = java.lang.Math.max(r5, r3)     // Catch:{ all -> 0x0047 }
            r7.A02 = r3     // Catch:{ all -> 0x0047 }
            java.lang.Integer r0 = X.AnonymousClass007.A01     // Catch:{ all -> 0x0047 }
            r7.A04 = r0     // Catch:{ all -> 0x0047 }
            r5 = 1
            goto L_0x003d
        L_0x0037:
            java.lang.Integer r0 = X.AnonymousClass007.A0D     // Catch:{ all -> 0x0047 }
            r7.A04 = r0     // Catch:{ all -> 0x0047 }
        L_0x003b:
            r1 = 0
        L_0x003d:
            monitor-exit(r7)     // Catch:{ all -> 0x0047 }
            if (r5 == 0) goto L_0x0044
            long r1 = r1 - r3
            r7.A00(r1)
        L_0x0044:
            return
        L_0x0045:
            monitor-exit(r7)
            return
        L_0x0047:
            r0 = move-exception
            monitor-exit(r7)
            throw r0
            switch-data {0->0x0025, 1->0x0024, 2->0x0037, }
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C09971qh.A03():void");
    }
}
