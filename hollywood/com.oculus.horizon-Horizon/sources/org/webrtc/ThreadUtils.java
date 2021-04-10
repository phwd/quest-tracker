package org.webrtc;

import android.os.Handler;
import android.os.SystemClock;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ThreadUtils {

    public interface BlockingOperation {
        void run() throws InterruptedException;
    }

    public static class ThreadChecker {
        public Thread thread = Thread.currentThread();

        public void detachThread() {
            this.thread = null;
        }

        public void checkIsOnValidThread() {
            Thread thread2 = this.thread;
            if (thread2 == null) {
                thread2 = Thread.currentThread();
                this.thread = thread2;
            }
            if (Thread.currentThread() != thread2) {
                throw new IllegalStateException("Wrong thread");
            }
        }
    }

    public static void executeUninterruptibly(BlockingOperation blockingOperation) {
        boolean z = false;
        while (true) {
            try {
                blockingOperation.run();
                break;
            } catch (InterruptedException unused) {
                z = true;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
    }

    public static void awaitUninterruptibly(final CountDownLatch countDownLatch) {
        executeUninterruptibly(new BlockingOperation() {
            /* class org.webrtc.ThreadUtils.AnonymousClass2 */

            @Override // org.webrtc.ThreadUtils.BlockingOperation
            public void run() throws InterruptedException {
                countDownLatch.await();
            }
        });
    }

    public static boolean awaitUninterruptibly(CountDownLatch countDownLatch, long j) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        boolean z = false;
        long j2 = j;
        boolean z2 = false;
        while (true) {
            try {
                z = countDownLatch.await(j2, TimeUnit.MILLISECONDS);
                if (z2) {
                }
            } catch (InterruptedException unused) {
                z2 = true;
                j2 = j - (SystemClock.elapsedRealtime() - elapsedRealtime);
                if (j2 <= 0) {
                    break;
                }
            }
        }
        Thread.currentThread().interrupt();
        return z;
    }

    public static <V> V invokeUninterruptibly(Handler handler, final Callable<V> callable) {
        final AnonymousClass1Result r2 = new Object() {
            /* class org.webrtc.ThreadUtils.AnonymousClass1Result */
            public V value;
        };
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        handler.post(new Runnable() {
            /* class org.webrtc.ThreadUtils.AnonymousClass3 */

            public void run() {
                try {
                    r2.value = (V) callable.call();
                    countDownLatch.countDown();
                } catch (Exception e) {
                    StringBuilder sb = new StringBuilder("Callable threw exception: ");
                    sb.append(e);
                    throw new RuntimeException(sb.toString());
                }
            }
        });
        awaitUninterruptibly(countDownLatch);
        return r2.value;
    }

    public static void invokeUninterruptibly(Handler handler, final Runnable runnable) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        handler.post(new Runnable() {
            /* class org.webrtc.ThreadUtils.AnonymousClass4 */

            public void run() {
                runnable.run();
                countDownLatch.countDown();
            }
        });
        awaitUninterruptibly(countDownLatch);
    }

    public static void joinUninterruptibly(final Thread thread) {
        executeUninterruptibly(new BlockingOperation() {
            /* class org.webrtc.ThreadUtils.AnonymousClass1 */

            @Override // org.webrtc.ThreadUtils.BlockingOperation
            public void run() throws InterruptedException {
                thread.join();
            }
        });
    }

    public static boolean joinUninterruptibly(Thread thread, long j) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j2 = j;
        boolean z = false;
        while (j2 > 0) {
            try {
                thread.join(j2);
                break;
            } catch (InterruptedException unused) {
                j2 = j - (SystemClock.elapsedRealtime() - elapsedRealtime);
                z = true;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        return !thread.isAlive();
    }
}
