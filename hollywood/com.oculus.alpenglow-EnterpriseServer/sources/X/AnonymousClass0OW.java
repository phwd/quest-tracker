package X;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/* renamed from: X.0OW  reason: invalid class name */
public final class AnonymousClass0OW extends C04540gz {
    public C04540gz A00;

    @Override // X.C04540gz
    public final C04540gz clearDeadline() {
        return this.A00.clearDeadline();
    }

    @Override // X.C04540gz
    public final C04540gz clearTimeout() {
        return this.A00.clearTimeout();
    }

    @Override // X.C04540gz
    public final boolean hasDeadline() {
        return this.A00.hasDeadline();
    }

    @Override // X.C04540gz
    public final void throwIfReached() throws IOException {
        this.A00.throwIfReached();
    }

    @Override // X.C04540gz
    public final C04540gz timeout(long j, TimeUnit timeUnit) {
        return this.A00.timeout(j, timeUnit);
    }

    @Override // X.C04540gz
    public final long timeoutNanos() {
        return this.A00.timeoutNanos();
    }

    public AnonymousClass0OW(C04540gz r3) {
        if (r3 != null) {
            this.A00 = r3;
            return;
        }
        throw new IllegalArgumentException("delegate == null");
    }

    @Override // X.C04540gz
    public final long deadlineNanoTime() {
        return this.A00.deadlineNanoTime();
    }

    @Override // X.C04540gz
    public final C04540gz deadlineNanoTime(long j) {
        return this.A00.deadlineNanoTime(j);
    }
}
