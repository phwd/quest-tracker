package X;

import java.util.concurrent.Executor;

/* renamed from: X.08h  reason: invalid class name */
public enum AnonymousClass08h implements Executor {
    INSTANCE;

    public String toString() {
        return "MoreExecutors.directExecutor()";
    }

    public void execute(Runnable runnable) {
        runnable.run();
    }
}
