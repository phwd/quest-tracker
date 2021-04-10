package X;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* renamed from: X.0Of  reason: invalid class name */
public class AnonymousClass0Of extends C04540gz {
    public static final long IDLE_TIMEOUT_MILLIS;
    public static final long IDLE_TIMEOUT_NANOS;
    public static final int TIMEOUT_WRITE_SIZE = 65536;
    @Nullable
    public static AnonymousClass0Of head;
    public boolean inQueue;
    @Nullable
    public AnonymousClass0Of next;
    public long timeoutAt;

    public void timedOut() {
    }

    static {
        long millis = TimeUnit.SECONDS.toMillis(60);
        IDLE_TIMEOUT_MILLIS = millis;
        IDLE_TIMEOUT_NANOS = TimeUnit.MILLISECONDS.toNanos(millis);
    }

    @Nullable
    public static AnonymousClass0Of awaitTimeout() throws InterruptedException {
        AnonymousClass0Of r4 = head;
        AnonymousClass0Of r3 = r4.next;
        long nanoTime = System.nanoTime();
        if (r3 == null) {
            AnonymousClass0Of.class.wait(IDLE_TIMEOUT_MILLIS);
            AnonymousClass0Of r5 = head;
            if (r5.next != null || System.nanoTime() - nanoTime < IDLE_TIMEOUT_NANOS) {
                return null;
            }
            return r5;
        }
        long j = r3.timeoutAt - nanoTime;
        if (j > 0) {
            long j2 = j / 1000000;
            AnonymousClass0Of.class.wait(j2, (int) (j - (1000000 * j2)));
            return null;
        }
        r4.next = r3.next;
        r3.next = null;
        return r3;
    }

    public static synchronized boolean cancelScheduledTimeout(AnonymousClass0Of r3) {
        boolean z;
        synchronized (AnonymousClass0Of.class) {
            AnonymousClass0Of r1 = head;
            while (true) {
                if (r1 == null) {
                    z = true;
                    break;
                }
                AnonymousClass0Of r0 = r1.next;
                if (r0 == r3) {
                    r1.next = r3.next;
                    r3.next = null;
                    z = false;
                    break;
                }
                r1 = r0;
            }
        }
        return z;
    }

    private long remainingNanos(long j) {
        return this.timeoutAt - j;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x005d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void scheduleTimeout(X.AnonymousClass0Of r8, long r9, boolean r11) {
        /*
        // Method dump skipped, instructions count: 101
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0Of.scheduleTimeout(X.0Of, long, boolean):void");
    }

    public final void enter() {
        if (!this.inQueue) {
            long timeoutNanos = timeoutNanos();
            boolean hasDeadline = hasDeadline();
            if (timeoutNanos != 0 || hasDeadline) {
                this.inQueue = true;
                scheduleTimeout(this, timeoutNanos, hasDeadline);
                return;
            }
            return;
        }
        throw new IllegalStateException("Unbalanced enter/exit");
    }

    public IOException newTimeoutException(@Nullable IOException iOException) {
        InterruptedIOException interruptedIOException = new InterruptedIOException("timeout");
        if (iOException != null) {
            interruptedIOException.initCause(iOException);
        }
        return interruptedIOException;
    }

    public final AnonymousClass0h1 sink(AnonymousClass0h1 r2) {
        return new AnonymousClass0Oh(this, r2);
    }

    public final AbstractC04550h0 source(AbstractC04550h0 r2) {
        return new AnonymousClass0Og(this, r2);
    }

    public final IOException exit(IOException iOException) throws IOException {
        if (exit()) {
            return newTimeoutException(iOException);
        }
        return iOException;
    }

    public final void exit(boolean z) throws IOException {
        if (exit() && z) {
            throw newTimeoutException(null);
        }
    }

    public final boolean exit() {
        if (!this.inQueue) {
            return false;
        }
        this.inQueue = false;
        return cancelScheduledTimeout(this);
    }
}
