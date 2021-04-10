package X;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

public final class Sm implements Executor {
    public final Handler A00 = new SZ(Looper.getMainLooper());

    public final void execute(Runnable runnable) {
        this.A00.post(runnable);
    }
}
