package X;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import java.util.concurrent.Executor;

/* renamed from: X.1d8  reason: invalid class name */
public class AnonymousClass1d8 implements Executor {
    public final Handler A00 = new Handler(Looper.getMainLooper());

    public final void execute(@NonNull Runnable runnable) {
        this.A00.post(runnable);
    }
}
