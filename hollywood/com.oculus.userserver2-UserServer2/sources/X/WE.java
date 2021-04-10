package X;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

public class WE {
    public static final WE NONE = new Df();
    public long deadlineNanoTime;
    public boolean hasDeadline;
    public long timeoutNanos;

    public WE clearDeadline() {
        this.hasDeadline = false;
        return this;
    }

    public WE clearTimeout() {
        this.timeoutNanos = 0;
        return this;
    }

    public final WE deadline(long j, TimeUnit timeUnit) {
        String str;
        if (j <= 0) {
            str = AnonymousClass06.A02("duration <= 0: ", j);
        } else if (timeUnit != null) {
            return deadlineNanoTime(System.nanoTime() + timeUnit.toNanos(j));
        } else {
            str = "unit == null";
        }
        throw new IllegalArgumentException(str);
    }

    public WE timeout(long j, TimeUnit timeUnit) {
        String str;
        if (j < 0) {
            str = AnonymousClass06.A02("timeout < 0: ", j);
        } else if (timeUnit != null) {
            this.timeoutNanos = timeUnit.toNanos(j);
            return this;
        } else {
            str = "unit == null";
        }
        throw new IllegalArgumentException(str);
    }

    public boolean hasDeadline() {
        return this.hasDeadline;
    }

    public void throwIfReached() throws IOException {
        String str;
        if (Thread.interrupted()) {
            str = "thread interrupted";
        } else if (this.hasDeadline && this.deadlineNanoTime - System.nanoTime() <= 0) {
            str = "deadline reached";
        } else {
            return;
        }
        throw new InterruptedIOException(str);
    }

    public long timeoutNanos() {
        return this.timeoutNanos;
    }

    public final void waitUntilNotified(Object obj) throws InterruptedIOException {
        try {
            boolean hasDeadline2 = hasDeadline();
            long timeoutNanos2 = timeoutNanos();
            long j = 0;
            if (hasDeadline2 || timeoutNanos2 != 0) {
                long nanoTime = System.nanoTime();
                if (hasDeadline2) {
                    if (timeoutNanos2 != 0) {
                        timeoutNanos2 = Math.min(timeoutNanos2, deadlineNanoTime() - nanoTime);
                    } else {
                        timeoutNanos2 = deadlineNanoTime() - nanoTime;
                    }
                }
                if (timeoutNanos2 > 0) {
                    long j2 = timeoutNanos2 / 1000000;
                    obj.wait(j2, (int) (timeoutNanos2 - (1000000 * j2)));
                    j = System.nanoTime() - nanoTime;
                }
                if (j >= timeoutNanos2) {
                    throw new InterruptedIOException("timeout");
                }
                return;
            }
            obj.wait();
        } catch (InterruptedException unused) {
            throw new InterruptedIOException("interrupted");
        }
    }

    public long deadlineNanoTime() {
        if (this.hasDeadline) {
            return this.deadlineNanoTime;
        }
        throw new IllegalStateException("No deadline");
    }

    public WE deadlineNanoTime(long j) {
        this.hasDeadline = true;
        this.deadlineNanoTime = j;
        return this;
    }
}
