package X;

import java.util.concurrent.ScheduledFuture;

/* renamed from: X.0D9  reason: invalid class name */
public class AnonymousClass0D9 implements Runnable {
    public static final String __redex_internal_original_name = "bolts.Task$2";
    public final /* synthetic */ AnonymousClass0DD A00;
    public final /* synthetic */ ScheduledFuture A01;

    public AnonymousClass0D9(ScheduledFuture scheduledFuture, AnonymousClass0DD r2) {
        this.A01 = scheduledFuture;
        this.A00 = r2;
    }

    public final void run() {
        this.A01.cancel(true);
        this.A00.A00.A0L();
    }
}
