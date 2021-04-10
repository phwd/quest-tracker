package X;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public final class K4 extends ca {
    public ca A00;

    @Override // X.ca
    public final ca clearDeadline() {
        return this.A00.clearDeadline();
    }

    @Override // X.ca
    public final ca clearTimeout() {
        return this.A00.clearTimeout();
    }

    @Override // X.ca
    public final boolean hasDeadline() {
        return this.A00.hasDeadline();
    }

    @Override // X.ca
    public final void throwIfReached() throws IOException {
        this.A00.throwIfReached();
    }

    @Override // X.ca
    public final ca timeout(long j, TimeUnit timeUnit) {
        return this.A00.timeout(j, timeUnit);
    }

    @Override // X.ca
    public final long timeoutNanos() {
        return this.A00.timeoutNanos();
    }

    public K4(ca caVar) {
        if (caVar != null) {
            this.A00 = caVar;
            return;
        }
        throw new IllegalArgumentException("delegate == null");
    }

    @Override // X.ca
    public final long deadlineNanoTime() {
        return this.A00.deadlineNanoTime();
    }

    @Override // X.ca
    public final ca deadlineNanoTime(long j) {
        return this.A00.deadlineNanoTime(j);
    }
}
