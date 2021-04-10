package X;

import android.os.Handler;
import com.facebook.rti.common.time.RealtimeSinceBootClock;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.Nullable;

/* renamed from: X.22S  reason: invalid class name */
public final class AnonymousClass22S {
    public int A00 = 0;
    public long A01;
    public AnonymousClass24C A02;
    public AbstractC144823m A03;
    @Nullable
    public Runnable A04;
    public Runnable A05;
    public Future<?> A06;
    public boolean A07;
    public AnonymousClass1YF A08;
    public final Handler A09;
    public final AnonymousClass1QM<Boolean> A0A;
    public final ExecutorService A0B;
    public final ScheduledExecutorService A0C;
    public final RealtimeSinceBootClock A0D;
    public final AnonymousClass23H A0E;

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0061, code lost:
        if (r1.A5R(r3) == false) goto L_0x0063;
     */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x008b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean A02() {
        /*
        // Method dump skipped, instructions count: 193
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass22S.A02():boolean");
    }

    public static void A00(AnonymousClass22S r2) {
        Future<?> future = r2.A06;
        if (future != null) {
            future.cancel(false);
            r2.A06 = null;
        }
        r2.A01(AnonymousClass23S.BACK_TO_BACK);
        AnonymousClass23H r1 = r2.A0E;
        r1.A01 = -2;
        r1.A00 = r1.A02;
        r2.A00 = 0;
    }

    private void A01(AnonymousClass23S r5) {
        Future<?> future = this.A06;
        if (future != null) {
            future.cancel(false);
            this.A06 = null;
        }
        AnonymousClass22Y r1 = ((AnonymousClass1YE) this.A08).A00;
        if (r5 == AnonymousClass23S.BACK_TO_BACK) {
            this.A03 = new AnonymousClass23M(r1.A02, r1.A05, r1.A03);
        } else if (r5 == AnonymousClass23S.BACK_OFF) {
            this.A03 = new AnonymousClass231(r1.A00, r1.A04, r1.A01);
        } else {
            throw new IllegalArgumentException(String.format(null, "Invalid strategy %s specified", r5));
        }
    }

    /* JADX WARN: Incorrect args count in method signature: (Lcom/facebook/rti/common/time/MonotonicClock;LX/1QM<Ljava/lang/Boolean;>;Ljava/util/concurrent/ExecutorService;Ljava/util/concurrent/ScheduledExecutorService;Landroid/os/Handler;LX/1YF;LX/24C;Lcom/facebook/rti/common/logging/RtiFlytrapLogger;)V */
    public AnonymousClass22S(RealtimeSinceBootClock realtimeSinceBootClock, AnonymousClass1QM r5, ExecutorService executorService, ScheduledExecutorService scheduledExecutorService, Handler handler, AnonymousClass1YF r9, AnonymousClass24C r10) {
        this.A0D = realtimeSinceBootClock;
        this.A0A = r5;
        this.A0B = executorService;
        this.A0C = scheduledExecutorService;
        this.A09 = handler;
        this.A08 = r9;
        this.A02 = r10;
        AnonymousClass22Y r0 = ((AnonymousClass1YE) r9).A00;
        this.A0E = new AnonymousClass23H(r0.A00, r0.A01);
    }
}
