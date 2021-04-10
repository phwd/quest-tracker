package X;

import java.util.concurrent.ScheduledExecutorService;

/* renamed from: X.1x3  reason: invalid class name */
public final class AnonymousClass1x3 extends AbstractC12411xQ {
    public final AnonymousClass1xU A00 = new AnonymousClass1xU();
    public final ScheduledExecutorService A01;
    public volatile boolean A02;

    @Override // X.AbstractC12271xB
    public final void dispose() {
        if (!this.A02) {
            this.A02 = true;
            this.A00.dispose();
        }
    }

    public AnonymousClass1x3(ScheduledExecutorService scheduledExecutorService) {
        this.A01 = scheduledExecutorService;
    }
}
