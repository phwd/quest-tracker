package X;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/* renamed from: X.1aU  reason: invalid class name and case insensitive filesystem */
public final class RunnableC06521aU implements Runnable {
    public static final String __redex_internal_original_name = "io.reactivex.internal.schedulers.SchedulerPoolFactory$ScheduledTask";

    public final void run() {
        Map<ScheduledThreadPoolExecutor, Object> map = C06641an.A01;
        Iterator it = new ArrayList(map.keySet()).iterator();
        while (it.hasNext()) {
            ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) it.next();
            if (threadPoolExecutor.isShutdown()) {
                map.remove(threadPoolExecutor);
            } else {
                threadPoolExecutor.purge();
            }
        }
    }
}
