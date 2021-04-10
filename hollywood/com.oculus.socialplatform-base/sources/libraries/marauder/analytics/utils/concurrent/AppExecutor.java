package libraries.marauder.analytics.utils.concurrent;

import X.AnonymousClass006;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class AppExecutor {
    public static final int CORE_POOL_SIZE = 5;
    public static final int KEEP_ALIVE = 1;
    public static final int MAXIMUM_POOL_SIZE = 128;
    public static final Executor THREAD_POOL_EXECUTOR;
    public static final BlockingQueue<Runnable> sPoolWorkQueue;
    public static final ThreadFactory sThreadFactory = new ThreadFactory() {
        /* class libraries.marauder.analytics.utils.concurrent.AppExecutor.AnonymousClass1 */
        public final AtomicInteger mCount = new AtomicInteger(1);

        public Thread newThread(Runnable runnable) {
            return new AppThread(runnable, AnonymousClass006.A03("AppExecutor #", this.mCount.getAndIncrement()), 9);
        }
    };

    static {
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(10);
        sPoolWorkQueue = linkedBlockingQueue;
        THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(5, 128, 1, TimeUnit.SECONDS, linkedBlockingQueue, sThreadFactory);
    }

    public static Executor getInstance() {
        return THREAD_POOL_EXECUTOR;
    }
}
