package okio;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

public class AsyncTimeout extends Timeout {
    public static final long IDLE_TIMEOUT_MILLIS;
    public static final long IDLE_TIMEOUT_NANOS;
    public static final int TIMEOUT_WRITE_SIZE = 65536;
    @Nullable
    public static AsyncTimeout head;
    public boolean inQueue;
    @Nullable
    public AsyncTimeout next;
    public long timeoutAt;

    public static final class Watchdog extends Thread {
        public Watchdog() {
            super("Okio Watchdog");
            setDaemon(true);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0015, code lost:
            r1.timedOut();
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r3 = this;
            L_0x0000:
                java.lang.Class<okio.AsyncTimeout> r2 = okio.AsyncTimeout.class
                monitor-enter(r2)     // Catch:{ InterruptedException -> 0x0000 }
                okio.AsyncTimeout r1 = okio.AsyncTimeout.awaitTimeout()     // Catch:{ all -> 0x001a }
                if (r1 != 0) goto L_0x000b
                monitor-exit(r2)     // Catch:{ all -> 0x001a }
                goto L_0x0000
            L_0x000b:
                okio.AsyncTimeout r0 = okio.AsyncTimeout.head     // Catch:{ all -> 0x001a }
                if (r1 != r0) goto L_0x0014
                r0 = 0
                okio.AsyncTimeout.head = r0     // Catch:{ all -> 0x001a }
                monitor-exit(r2)     // Catch:{ all -> 0x001a }
                goto L_0x0019
            L_0x0014:
                monitor-exit(r2)     // Catch:{ all -> 0x001a }
                r1.timedOut()
                goto L_0x0000
            L_0x0019:
                return
            L_0x001a:
                r0 = move-exception
                monitor-exit(r2)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: okio.AsyncTimeout.Watchdog.run():void");
        }
    }

    public void timedOut() {
    }

    static {
        long millis = TimeUnit.SECONDS.toMillis(60);
        IDLE_TIMEOUT_MILLIS = millis;
        IDLE_TIMEOUT_NANOS = TimeUnit.MILLISECONDS.toNanos(millis);
    }

    @Nullable
    public static AsyncTimeout awaitTimeout() throws InterruptedException {
        AsyncTimeout asyncTimeout = head;
        AsyncTimeout asyncTimeout2 = asyncTimeout.next;
        long nanoTime = System.nanoTime();
        if (asyncTimeout2 == null) {
            AsyncTimeout.class.wait(IDLE_TIMEOUT_MILLIS);
            AsyncTimeout asyncTimeout3 = head;
            if (asyncTimeout3.next != null || System.nanoTime() - nanoTime < IDLE_TIMEOUT_NANOS) {
                return null;
            }
            return asyncTimeout3;
        }
        long j = asyncTimeout2.timeoutAt - nanoTime;
        if (j > 0) {
            long j2 = j / 1000000;
            AsyncTimeout.class.wait(j2, (int) (j - (1000000 * j2)));
            return null;
        }
        asyncTimeout.next = asyncTimeout2.next;
        asyncTimeout2.next = null;
        return asyncTimeout2;
    }

    public static synchronized boolean cancelScheduledTimeout(AsyncTimeout asyncTimeout) {
        boolean z;
        synchronized (AsyncTimeout.class) {
            AsyncTimeout asyncTimeout2 = head;
            while (true) {
                if (asyncTimeout2 == null) {
                    z = true;
                    break;
                }
                AsyncTimeout asyncTimeout3 = asyncTimeout2.next;
                if (asyncTimeout3 == asyncTimeout) {
                    asyncTimeout2.next = asyncTimeout.next;
                    asyncTimeout.next = null;
                    z = false;
                    break;
                }
                asyncTimeout2 = asyncTimeout3;
            }
        }
        return z;
    }

    private long remainingNanos(long j) {
        return this.timeoutAt - j;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x005d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void scheduleTimeout(okio.AsyncTimeout r8, long r9, boolean r11) {
        /*
        // Method dump skipped, instructions count: 101
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.AsyncTimeout.scheduleTimeout(okio.AsyncTimeout, long, boolean):void");
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

    public final Sink sink(final Sink sink) {
        return new Sink() {
            /* class okio.AsyncTimeout.AnonymousClass1 */

            @Override // okio.Sink
            public void write(Buffer buffer, long j) throws IOException {
                long j2 = j;
                Util.checkOffsetAndCount(buffer.size, 0, j2);
                while (true) {
                    long j3 = 0;
                    if (j2 > 0) {
                        Segment segment = buffer.head;
                        while (true) {
                            j3 += (long) (segment.limit - segment.pos);
                            if (j3 < j2) {
                                segment = segment.next;
                                if (j3 >= SegmentPool.MAX_SIZE) {
                                    break;
                                }
                            } else {
                                j3 = j2;
                                break;
                            }
                        }
                        AsyncTimeout.this.enter();
                        try {
                            sink.write(buffer, j3);
                            j2 -= j3;
                            AsyncTimeout.this.exit(true);
                        } catch (IOException e) {
                            throw AsyncTimeout.this.exit(e);
                        } catch (Throwable th) {
                            AsyncTimeout.this.exit(false);
                            throw th;
                        }
                    } else {
                        return;
                    }
                }
            }

            @Override // java.io.Closeable, java.lang.AutoCloseable, okio.Sink
            public void close() throws IOException {
                AsyncTimeout.this.enter();
                try {
                    sink.close();
                    AsyncTimeout.this.exit(true);
                } catch (IOException e) {
                    throw AsyncTimeout.this.exit(e);
                } catch (Throwable th) {
                    AsyncTimeout.this.exit(false);
                    throw th;
                }
            }

            @Override // okio.Sink, java.io.Flushable
            public void flush() throws IOException {
                AsyncTimeout.this.enter();
                try {
                    sink.flush();
                    AsyncTimeout.this.exit(true);
                } catch (IOException e) {
                    throw AsyncTimeout.this.exit(e);
                } catch (Throwable th) {
                    AsyncTimeout.this.exit(false);
                    throw th;
                }
            }

            public String toString() {
                StringBuilder sb = new StringBuilder("AsyncTimeout.sink(");
                sb.append(sink);
                sb.append(")");
                return sb.toString();
            }

            @Override // okio.Sink
            public Timeout timeout() {
                return AsyncTimeout.this;
            }
        };
    }

    public final Source source(final Source source) {
        return new Source() {
            /* class okio.AsyncTimeout.AnonymousClass2 */

            @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                try {
                    source.close();
                    AsyncTimeout.this.exit(true);
                } catch (IOException e) {
                    throw AsyncTimeout.this.exit(e);
                } catch (Throwable th) {
                    AsyncTimeout.this.exit(false);
                    throw th;
                }
            }

            @Override // okio.Source
            public long read(Buffer buffer, long j) throws IOException {
                AsyncTimeout.this.enter();
                try {
                    long read = source.read(buffer, j);
                    AsyncTimeout.this.exit(true);
                    return read;
                } catch (IOException e) {
                    throw AsyncTimeout.this.exit(e);
                } catch (Throwable th) {
                    AsyncTimeout.this.exit(false);
                    throw th;
                }
            }

            public String toString() {
                StringBuilder sb = new StringBuilder("AsyncTimeout.source(");
                sb.append(source);
                sb.append(")");
                return sb.toString();
            }

            @Override // okio.Source
            public Timeout timeout() {
                return AsyncTimeout.this;
            }
        };
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
