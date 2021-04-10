package X;

import java.util.concurrent.Executor;

/* renamed from: X.12I  reason: invalid class name */
public enum AnonymousClass12I implements Executor {
    INSTANCE;

    public String toString() {
        return "MoreExecutors.directExecutor()";
    }

    public void execute(Runnable runnable) {
        runnable.run();
    }
}
