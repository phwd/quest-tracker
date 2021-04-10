package X;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import java.util.concurrent.Executor;

/* renamed from: X.2NF  reason: invalid class name */
public class AnonymousClass2NF implements Executor {
    public final Handler A00 = new Handler(Looper.getMainLooper());

    public final void execute(@NonNull Runnable runnable) {
        this.A00.post(runnable);
    }
}
