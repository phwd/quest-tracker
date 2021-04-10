package libraries.marauder.analytics.utils.concurrent;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

public class SerialExecutor implements Executor {
    public final Executor mBackingExecutor;
    public boolean mIsExecuting;
    public final Queue<RunnableWrapper> mRunnables;

    public static class Builder {
        public final Executor mBackingExecutor;

        public static Builder getInstance() {
            return new Builder(AppExecutor.THREAD_POOL_EXECUTOR);
        }

        public SerialExecutor build() {
            return new SerialExecutor(this);
        }

        public Builder(Executor executor) {
            this.mBackingExecutor = executor;
        }
    }

    public class RunnableWrapper implements Runnable {
        public final Runnable mRunnable;

        public RunnableWrapper(Runnable runnable) {
            this.mRunnable = runnable;
        }

        public void run() {
            this.mRunnable.run();
            synchronized (SerialExecutor.this) {
                SerialExecutor.this.mIsExecuting = false;
            }
            SerialExecutor.this.scheduleNext();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void scheduleNext() {
        RunnableWrapper poll;
        synchronized (this) {
            if (!this.mIsExecuting && (poll = this.mRunnables.poll()) != null) {
                this.mIsExecuting = true;
                this.mBackingExecutor.execute(poll);
            }
        }
    }

    public synchronized void execute(Runnable runnable) {
        this.mRunnables.add(new RunnableWrapper(runnable));
        scheduleNext();
    }

    public SerialExecutor(Builder builder) {
        this.mBackingExecutor = builder.mBackingExecutor;
        this.mRunnables = new ConcurrentLinkedQueue();
        this.mIsExecuting = false;
    }
}
