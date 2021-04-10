package X;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

public class FK implements Executor {
    public final void execute(Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }
}
