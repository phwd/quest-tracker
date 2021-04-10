package X;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

public class Dv extends WE {
    public static final long IDLE_TIMEOUT_MILLIS;
    public static final long IDLE_TIMEOUT_NANOS;
    public static final int TIMEOUT_WRITE_SIZE = 65536;
    @Nullable
    public static Dv head;
    public boolean inQueue;
    @Nullable
    public Dv next;
    public long timeoutAt;

    public void timedOut() {
    }

    static {
        long millis = TimeUnit.SECONDS.toMillis(60);
        IDLE_TIMEOUT_MILLIS = millis;
        IDLE_TIMEOUT_NANOS = TimeUnit.MILLISECONDS.toNanos(millis);
    }

    @Nullable
    public static Dv awaitTimeout() throws InterruptedException {
        Dv dv = head;
        Dv dv2 = dv.next;
        long nanoTime = System.nanoTime();
        if (dv2 == null) {
            Dv.class.wait(IDLE_TIMEOUT_MILLIS);
            Dv dv3 = head;
            if (dv3.next != null || System.nanoTime() - nanoTime < IDLE_TIMEOUT_NANOS) {
                return null;
            }
            return dv3;
        }
        long j = dv2.timeoutAt - nanoTime;
        if (j > 0) {
            long j2 = j / 1000000;
            Dv.class.wait(j2, (int) (j - (1000000 * j2)));
            return null;
        }
        dv.next = dv2.next;
        dv2.next = null;
        return dv2;
    }

    public static synchronized boolean cancelScheduledTimeout(Dv dv) {
        boolean z;
        synchronized (Dv.class) {
            Dv dv2 = head;
            while (true) {
                if (dv2 == null) {
                    z = true;
                    break;
                }
                Dv dv3 = dv2.next;
                if (dv3 == dv) {
                    dv2.next = dv.next;
                    dv.next = null;
                    z = false;
                    break;
                }
                dv2 = dv3;
            }
        }
        return z;
    }

    private long remainingNanos(long j) {
        return this.timeoutAt - j;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x005d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void scheduleTimeout(X.Dv r8, long r9, boolean r11) {
        /*
        // Method dump skipped, instructions count: 101
        */
        throw new UnsupportedOperationException("Method not decompiled: X.Dv.scheduleTimeout(X.Dv, long, boolean):void");
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

    public final WG sink(WG wg) {
        return new Dx(this, wg);
    }

    public final WF source(WF wf) {
        return new Dw(this, wf);
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
