package X;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/* renamed from: X.0Ls  reason: invalid class name */
public final class AnonymousClass0Ls extends C07620v5 {
    public C07620v5 A00;

    @Override // X.C07620v5
    public final C07620v5 clearDeadline() {
        return this.A00.clearDeadline();
    }

    @Override // X.C07620v5
    public final C07620v5 clearTimeout() {
        return this.A00.clearTimeout();
    }

    @Override // X.C07620v5
    public final boolean hasDeadline() {
        return this.A00.hasDeadline();
    }

    @Override // X.C07620v5
    public final void throwIfReached() throws IOException {
        this.A00.throwIfReached();
    }

    @Override // X.C07620v5
    public final C07620v5 timeout(long j, TimeUnit timeUnit) {
        return this.A00.timeout(j, timeUnit);
    }

    @Override // X.C07620v5
    public final long timeoutNanos() {
        return this.A00.timeoutNanos();
    }

    public AnonymousClass0Ls(C07620v5 r3) {
        if (r3 != null) {
            this.A00 = r3;
            return;
        }
        throw new IllegalArgumentException("delegate == null");
    }

    @Override // X.C07620v5
    public final long deadlineNanoTime() {
        return this.A00.deadlineNanoTime();
    }

    @Override // X.C07620v5
    public final C07620v5 deadlineNanoTime(long j) {
        return this.A00.deadlineNanoTime(j);
    }
}
