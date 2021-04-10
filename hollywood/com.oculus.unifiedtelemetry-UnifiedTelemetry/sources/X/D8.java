package X;

import java.util.concurrent.ScheduledFuture;

public class D8 implements Runnable {
    public static final String __redex_internal_original_name = "bolts.Task$2";
    public final /* synthetic */ DC A00;
    public final /* synthetic */ ScheduledFuture A01;

    public D8(ScheduledFuture scheduledFuture, DC dc) {
        this.A01 = scheduledFuture;
        this.A00 = dc;
    }

    public final void run() {
        this.A01.cancel(true);
        this.A00.A00.A07();
    }
}
