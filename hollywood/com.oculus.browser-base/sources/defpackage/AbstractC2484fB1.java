package defpackage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: fB1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2484fB1 {

    /* renamed from: a  reason: collision with root package name */
    public static final ExecutorService f9904a;

    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ThreadFactoryC4299pq0("GAC_Executor"));
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        f9904a = Executors.unconfigurableExecutorService(threadPoolExecutor);
    }
}
