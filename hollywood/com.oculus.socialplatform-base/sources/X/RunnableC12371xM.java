package X;

import io.reactivex.annotations.NonNull;
import java.util.concurrent.TimeUnit;

/* renamed from: X.1xM  reason: invalid class name and case insensitive filesystem */
public final class RunnableC12371xM implements Runnable, AbstractC12181wx {
    public static final String __redex_internal_original_name = "io.reactivex.Scheduler$Worker$PeriodicTask";
    public long A00;
    public long A01;
    public long A02;
    public final long A03;
    @NonNull
    public final AnonymousClass1xW A04;
    @NonNull
    public final Runnable A05;
    public final /* synthetic */ AbstractC12411xQ A06;

    public RunnableC12371xM(AbstractC12411xQ r1, @NonNull long j, Runnable runnable, @NonNull long j2, AnonymousClass1xW r7, long j3) {
        this.A06 = r1;
        this.A05 = runnable;
        this.A04 = r7;
        this.A03 = j3;
        this.A01 = j2;
        this.A02 = j;
    }

    public final void run() {
        long j;
        this.A05.run();
        AnonymousClass1xW r9 = this.A04;
        if (!EnumC12511xi.isDisposed((AbstractC12271xB) r9.get())) {
            TimeUnit timeUnit = TimeUnit.NANOSECONDS;
            long convert = timeUnit.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
            long j2 = AbstractC12361xL.A00;
            long j3 = this.A01;
            if (j2 + convert >= j3) {
                long j4 = this.A03;
                if (convert < j3 + j4 + j2) {
                    long j5 = this.A02;
                    long j6 = this.A00 + 1;
                    this.A00 = j6;
                    j = j5 + (j6 * j4);
                    this.A01 = convert;
                    EnumC12511xi.replace(r9, this.A06.A02(this, j - convert, timeUnit));
                }
            }
            long j7 = this.A03;
            j = convert + j7;
            long j8 = this.A00 + 1;
            this.A00 = j8;
            this.A02 = j - (j7 * j8);
            this.A01 = convert;
            EnumC12511xi.replace(r9, this.A06.A02(this, j - convert, timeUnit));
        }
    }
}
