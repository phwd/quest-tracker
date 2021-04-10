package java.util.concurrent;

public class Executors {
    public static Callable callable(Runnable runnable, Object obj) {
        if (runnable != null) {
            return new RunnableAdapter(runnable, obj);
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public static final class RunnableAdapter implements Callable {
        private final Object result;
        private final Runnable task;

        RunnableAdapter(Runnable runnable, Object obj) {
            this.task = runnable;
            this.result = obj;
        }

        @Override // java.util.concurrent.Callable
        public Object call() {
            this.task.run();
            return this.result;
        }
    }
}
