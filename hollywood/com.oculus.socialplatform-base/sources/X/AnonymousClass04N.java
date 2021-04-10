package X;

import android.os.Handler;
import androidx.annotation.NonNull;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

/* renamed from: X.04N  reason: invalid class name */
public class AnonymousClass04N implements Executor {
    public final Handler A00;

    public final void execute(Runnable runnable) {
        Handler handler = this.A00;
        if (!handler.post(runnable)) {
            StringBuilder sb = new StringBuilder();
            sb.append(handler);
            sb.append(" is shutting down");
            throw new RejectedExecutionException(sb.toString());
        }
    }

    public AnonymousClass04N(@NonNull Handler handler) {
        this.A00 = handler;
    }
}
