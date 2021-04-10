package X;

import java.util.concurrent.Executor;

/* renamed from: X.Bj  reason: case insensitive filesystem */
public enum EnumC0063Bj implements Executor {
    INSTANCE;

    public String toString() {
        return "MoreExecutors.directExecutor()";
    }

    public void execute(Runnable runnable) {
        runnable.run();
    }
}
