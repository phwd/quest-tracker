package X;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public final class Dl extends WE {
    public WE A00;

    @Override // X.WE
    public final WE clearDeadline() {
        return this.A00.clearDeadline();
    }

    @Override // X.WE
    public final WE clearTimeout() {
        return this.A00.clearTimeout();
    }

    @Override // X.WE
    public final boolean hasDeadline() {
        return this.A00.hasDeadline();
    }

    @Override // X.WE
    public final void throwIfReached() throws IOException {
        this.A00.throwIfReached();
    }

    @Override // X.WE
    public final WE timeout(long j, TimeUnit timeUnit) {
        return this.A00.timeout(j, timeUnit);
    }

    @Override // X.WE
    public final long timeoutNanos() {
        return this.A00.timeoutNanos();
    }

    public Dl(WE we) {
        if (we != null) {
            this.A00 = we;
            return;
        }
        throw new IllegalArgumentException("delegate == null");
    }

    @Override // X.WE
    public final long deadlineNanoTime() {
        return this.A00.deadlineNanoTime();
    }

    @Override // X.WE
    public final WE deadlineNanoTime(long j) {
        return this.A00.deadlineNanoTime(j);
    }
}
