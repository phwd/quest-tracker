package X;

import java.util.concurrent.Executor;

/* renamed from: X.0xT  reason: invalid class name and case insensitive filesystem */
public enum EnumC08710xT implements Executor {
    INSTANCE;

    public String toString() {
        return "MoreExecutors.directExecutor()";
    }

    public void execute(Runnable runnable) {
        runnable.run();
    }
}
