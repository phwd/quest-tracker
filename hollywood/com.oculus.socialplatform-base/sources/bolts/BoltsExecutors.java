package bolts;

import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public final class BoltsExecutors {
    public static final BoltsExecutors INSTANCE = new BoltsExecutors();
    public final ExecutorService background;
    public final Executor immediate;
    public final ScheduledExecutorService scheduled;

    public static class ImmediateExecutor implements Executor {
        public static final int MAX_DEPTH = 15;
        public ThreadLocal<Integer> executionDepth;

        private int decrementDepth() {
            Integer num = this.executionDepth.get();
            if (num == null) {
                num = 0;
            }
            int intValue = num.intValue() - 1;
            ThreadLocal<Integer> threadLocal = this.executionDepth;
            if (intValue == 0) {
                threadLocal.remove();
                return intValue;
            }
            threadLocal.set(Integer.valueOf(intValue));
            return intValue;
        }

        private int incrementDepth() {
            Integer num = this.executionDepth.get();
            if (num == null) {
                num = 0;
            }
            int intValue = num.intValue() + 1;
            this.executionDepth.set(Integer.valueOf(intValue));
            return intValue;
        }

        public void execute(Runnable runnable) {
            if (incrementDepth() <= 15) {
                try {
                    runnable.run();
                } catch (Throwable th) {
                    decrementDepth();
                    throw th;
                }
            } else {
                BoltsExecutors.INSTANCE.background.execute(runnable);
            }
            decrementDepth();
        }

        public ImmediateExecutor() {
            this.executionDepth = new ThreadLocal<>();
        }
    }

    public static ExecutorService background() {
        return INSTANCE.background;
    }

    public static Executor immediate() {
        return INSTANCE.immediate;
    }

    public static boolean isAndroidRuntime() {
        String property = System.getProperty("java.runtime.name");
        if (property == null) {
            return false;
        }
        return property.toLowerCase(Locale.US).contains(WebViewAppLinkResolver.KEY_ANDROID);
    }

    public static ScheduledExecutorService scheduled() {
        return INSTANCE.scheduled;
    }

    public BoltsExecutors() {
        ExecutorService newCachedThreadPool;
        if (!isAndroidRuntime()) {
            newCachedThreadPool = Executors.newCachedThreadPool();
        } else {
            newCachedThreadPool = AndroidExecutors.newCachedThreadPool();
        }
        this.background = newCachedThreadPool;
        this.scheduled = Executors.newSingleThreadScheduledExecutor();
        this.immediate = new ImmediateExecutor();
    }
}
