package X;

import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: X.Cx  reason: case insensitive filesystem */
public final class C0078Cx {
    public static final C0078Cx A03 = new C0078Cx();
    public final Executor A00;
    public final ExecutorService A01;
    public final ScheduledExecutorService A02;

    public C0078Cx() {
        ThreadPoolExecutor threadPoolExecutor;
        String property = System.getProperty("java.runtime.name");
        if (property == null || !property.toLowerCase(Locale.US).contains("android")) {
            threadPoolExecutor = Executors.newCachedThreadPool();
        } else {
            ThreadPoolExecutor threadPoolExecutor2 = new ThreadPoolExecutor(C0076Cv.A01, C0076Cv.A02, 1, TimeUnit.SECONDS, new LinkedBlockingQueue());
            threadPoolExecutor2.allowCoreThreadTimeOut(true);
            threadPoolExecutor = threadPoolExecutor2;
        }
        this.A01 = threadPoolExecutor;
        this.A02 = Executors.newSingleThreadScheduledExecutor();
        this.A00 = new ExecutorC0077Cw();
    }
}
