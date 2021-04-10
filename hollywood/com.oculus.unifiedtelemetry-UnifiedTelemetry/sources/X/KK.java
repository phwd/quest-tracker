package X;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

public class KK extends ca {
    public static final long IDLE_TIMEOUT_MILLIS;
    public static final long IDLE_TIMEOUT_NANOS;
    public static final int TIMEOUT_WRITE_SIZE = 65536;
    @Nullable
    public static KK head;
    public boolean inQueue;
    @Nullable
    public KK next;
    public long timeoutAt;

    public void timedOut() {
    }

    static {
        long millis = TimeUnit.SECONDS.toMillis(60);
        IDLE_TIMEOUT_MILLIS = millis;
        IDLE_TIMEOUT_NANOS = TimeUnit.MILLISECONDS.toNanos(millis);
    }

    @Nullable
    public static KK awaitTimeout() throws InterruptedException {
        KK kk = head;
        KK kk2 = kk.next;
        long nanoTime = System.nanoTime();
        if (kk2 == null) {
            KK.class.wait(IDLE_TIMEOUT_MILLIS);
            KK kk3 = head;
            if (kk3.next != null || System.nanoTime() - nanoTime < IDLE_TIMEOUT_NANOS) {
                return null;
            }
            return kk3;
        }
        long j = kk2.timeoutAt - nanoTime;
        if (j > 0) {
            long j2 = j / 1000000;
            KK.class.wait(j2, (int) (j - (1000000 * j2)));
            return null;
        }
        kk.next = kk2.next;
        kk2.next = null;
        return kk2;
    }

    public static synchronized boolean cancelScheduledTimeout(KK kk) {
        boolean z;
        synchronized (KK.class) {
            KK kk2 = head;
            while (true) {
                if (kk2 == null) {
                    z = true;
                    break;
                }
                KK kk3 = kk2.next;
                if (kk3 == kk) {
                    kk2.next = kk.next;
                    kk.next = null;
                    z = false;
                    break;
                }
                kk2 = kk3;
            }
        }
        return z;
    }

    private long remainingNanos(long j) {
        return this.timeoutAt - j;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x005d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void scheduleTimeout(X.KK r8, long r9, boolean r11) {
        /*
        // Method dump skipped, instructions count: 101
        */
        throw new UnsupportedOperationException("Method not decompiled: X.KK.scheduleTimeout(X.KK, long, boolean):void");
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

    public final AbstractC0313cc sink(AbstractC0313cc ccVar) {
        return new KM(this, ccVar);
    }

    public final AbstractC0312cb source(AbstractC0312cb cbVar) {
        return new KL(this, cbVar);
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
