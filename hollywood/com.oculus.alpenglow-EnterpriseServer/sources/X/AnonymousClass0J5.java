package X;

import java.util.concurrent.Executor;

/* renamed from: X.0J5  reason: invalid class name */
public class AnonymousClass0J5 implements Executor {
    public final void execute(Runnable runnable) {
        new Thread(runnable).start();
    }
}
