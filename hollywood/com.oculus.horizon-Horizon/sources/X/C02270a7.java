package X;

import android.os.Handler;
import com.facebook.rti.common.time.MonotonicClock;
import com.facebook.rti.common.time.RealtimeSinceBootClock;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.Nullable;

/* renamed from: X.0a7  reason: invalid class name and case insensitive filesystem */
public final class C02270a7 {
    public int A00 = 0;
    public long A01;
    @Nullable
    public AnonymousClass0WB A02;
    public AbstractC01590Wa A03;
    public AbstractC02280a9 A04;
    @Nullable
    public Runnable A05;
    public Runnable A06;
    public Future<?> A07;
    public boolean A08;
    public AnonymousClass0X3 A09;
    public final Handler A0A;
    public final AnonymousClass0WY<Boolean> A0B;
    public final ExecutorService A0C;
    public final ScheduledExecutorService A0D;
    public final RealtimeSinceBootClock A0E;
    public final C02290aA A0F;

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0040, code lost:
        if (r9.A08 != false) goto L_0x0042;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x005e, code lost:
        if (r1.A4i(r2) == false) goto L_0x0060;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean A02() {
        /*
        // Method dump skipped, instructions count: 210
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C02270a7.A02():boolean");
    }

    public static void A00(C02270a7 r2) {
        Future<?> future = r2.A07;
        if (future != null) {
            future.cancel(false);
            r2.A07 = null;
        }
        r2.A01(AnonymousClass0a8.BACK_TO_BACK);
        C02290aA r1 = r2.A0F;
        r1.A01 = -2;
        r1.A00 = r1.A02;
        r2.A00 = 0;
    }

    private void A01(AnonymousClass0a8 r5) {
        Future<?> future = this.A07;
        if (future != null) {
            future.cancel(false);
            this.A07 = null;
        }
        AnonymousClass0X5 A002 = this.A09.A00();
        if (r5 == AnonymousClass0a8.BACK_TO_BACK) {
            this.A04 = new C05050kA(A002.A02, A002.A05, A002.A03);
        } else if (r5 == AnonymousClass0a8.BACK_OFF) {
            this.A04 = new AnonymousClass0k8(A002.A00, A002.A04, A002.A01);
        } else {
            throw new IllegalArgumentException(String.format(null, "Invalid strategy %s specified", r5));
        }
    }

    public C02270a7(MonotonicClock monotonicClock, AnonymousClass0WY<Boolean> r5, ExecutorService executorService, ScheduledExecutorService scheduledExecutorService, Handler handler, AnonymousClass0X3 r9, AbstractC01590Wa r10, @Nullable AnonymousClass0WB r11) {
        this.A0E = monotonicClock;
        this.A0B = r5;
        this.A0C = executorService;
        this.A0D = scheduledExecutorService;
        this.A0A = handler;
        this.A09 = r9;
        this.A03 = r10;
        this.A02 = r11;
        AnonymousClass0X5 A002 = r9.A00();
        this.A0F = new C02290aA(A002.A00, A002.A01);
    }
}
