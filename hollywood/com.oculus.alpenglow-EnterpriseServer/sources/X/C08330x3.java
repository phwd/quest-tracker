package X;

import android.os.Handler;
import com.facebook.rti.common.time.MonotonicClock;
import com.facebook.rti.common.time.RealtimeSinceBootClock;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.Nullable;

/* renamed from: X.0x3  reason: invalid class name and case insensitive filesystem */
public final class C08330x3 {
    public int A00 = 0;
    public long A01;
    @Nullable
    public AbstractC09080yd A02;
    public AbstractC07660vk A03;
    public AbstractC09640zx A04;
    @Nullable
    public Runnable A05;
    public Runnable A06;
    public Future<?> A07;
    public boolean A08;
    public AnonymousClass0v4 A09;
    public final Handler A0A;
    public final AbstractC09610zk<Boolean> A0B;
    public final ExecutorService A0C;
    public final ScheduledExecutorService A0D;
    public final RealtimeSinceBootClock A0E;
    public final C09170ym A0F;

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0040, code lost:
        if (r8.A08 != false) goto L_0x0042;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x005e, code lost:
        if (r1.A51(r2) == false) goto L_0x0060;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean A02() {
        /*
        // Method dump skipped, instructions count: 210
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C08330x3.A02():boolean");
    }

    public static void A00(C08330x3 r2) {
        Future<?> future = r2.A07;
        if (future != null) {
            future.cancel(false);
            r2.A07 = null;
        }
        r2.A01(AnonymousClass0z4.BACK_TO_BACK);
        C09170ym r1 = r2.A0F;
        r1.A01 = -2;
        r1.A00 = r1.A02;
        r2.A00 = 0;
    }

    private void A01(AnonymousClass0z4 r5) {
        Future<?> future = this.A07;
        if (future != null) {
            future.cancel(false);
            this.A07 = null;
        }
        C08460xH r1 = ((AnonymousClass0v5) this.A09).A01;
        if (r5 == AnonymousClass0z4.BACK_TO_BACK) {
            this.A04 = new C09250yw(r1.A02, r1.A05, r1.A03);
        } else if (r5 == AnonymousClass0z4.BACK_OFF) {
            this.A04 = new C09010yL(r1.A00, r1.A04, r1.A01);
        } else {
            throw new IllegalArgumentException(String.format(null, "Invalid strategy %s specified", r5));
        }
    }

    public C08330x3(MonotonicClock monotonicClock, AbstractC09610zk<Boolean> r5, ExecutorService executorService, ScheduledExecutorService scheduledExecutorService, Handler handler, AnonymousClass0v4 r9, AbstractC07660vk r10, @Nullable AbstractC09080yd r11) {
        this.A0E = monotonicClock;
        this.A0B = r5;
        this.A0C = executorService;
        this.A0D = scheduledExecutorService;
        this.A0A = handler;
        this.A09 = r9;
        this.A03 = r10;
        this.A02 = r11;
        C08460xH r0 = ((AnonymousClass0v5) r9).A01;
        this.A0F = new C09170ym(r0.A00, r0.A01);
    }
}
