package X;

import java.util.concurrent.Executor;

public enum g1 implements Executor {
    INSTANCE;

    public String toString() {
        return "MoreExecutors.directExecutor()";
    }

    public void execute(Runnable runnable) {
        runnable.run();
    }
}
