package X;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

/* renamed from: X.0Cv  reason: invalid class name and case insensitive filesystem */
public class ExecutorC00740Cv implements Executor {
    public final void execute(Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }
}
