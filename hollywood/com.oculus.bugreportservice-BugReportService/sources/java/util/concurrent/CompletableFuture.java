package java.util.concurrent;

import sun.misc.Unsafe;

public class CompletableFuture implements Future, CompletionStage {
    private static final Executor ASYNC_POOL;
    private static final long NEXT;
    static final AltResult NIL = new AltResult(null);
    private static final long RESULT;
    static final int SPINS;
    private static final long STACK;
    private static final Unsafe U = Unsafe.getUnsafe();
    private static final boolean USE_COMMON_POOL = (ForkJoinPool.getCommonPoolParallelism() > 1);
    volatile Object result;
    volatile Completion stack;

    public interface AsynchronousCompletionTask {
    }

    static final class AltResult {
        final Throwable ex;

        AltResult(Throwable th) {
            this.ex = th;
        }
    }

    static {
        Executor executor;
        int i = 0;
        if (USE_COMMON_POOL) {
            executor = ForkJoinPool.commonPool();
        } else {
            executor = new ThreadPerTaskExecutor();
        }
        ASYNC_POOL = executor;
        if (Runtime.getRuntime().availableProcessors() > 1) {
            i = 256;
        }
        SPINS = i;
        try {
            RESULT = U.objectFieldOffset(CompletableFuture.class.getDeclaredField("result"));
            STACK = U.objectFieldOffset(CompletableFuture.class.getDeclaredField("stack"));
            NEXT = U.objectFieldOffset(Completion.class.getDeclaredField("next"));
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }

    static final class ThreadPerTaskExecutor implements Executor {
        ThreadPerTaskExecutor() {
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            new Thread(runnable).start();
        }
    }

    static abstract class Completion extends ForkJoinTask implements Runnable, AsynchronousCompletionTask {
        volatile Completion next;

        @Override // java.util.concurrent.ForkJoinTask
        public final Void getRawResult() {
            return null;
        }

        /* access modifiers changed from: package-private */
        public abstract CompletableFuture tryFire(int i);

        Completion() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            tryFire(1);
        }

        @Override // java.util.concurrent.ForkJoinTask
        public final boolean exec() {
            tryFire(1);
            return false;
        }
    }

    public String toString() {
        for (Completion completion = this.stack; completion != null; completion = completion.next) {
        }
        new StringBuilder();
        super.toString();
        throw null;
    }
}
