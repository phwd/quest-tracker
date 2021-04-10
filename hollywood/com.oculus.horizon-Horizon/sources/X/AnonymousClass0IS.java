package X;

import android.os.Handler;
import android.os.Looper;
import com.facebook.infer.annotation.Nullsafe;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0IS  reason: invalid class name */
public abstract class AnonymousClass0IS<V> extends AnonymousClass0n9<V> {
    public final Handler A00;

    public AnonymousClass0IS(Handler handler) {
        this.A00 = handler;
    }

    @Override // java.util.concurrent.Future, X.AnonymousClass0n9
    public final V get() throws InterruptedException, ExecutionException {
        if (Looper.myLooper() != this.A00.getLooper() || isDone()) {
            return (V) super.get();
        }
        throw new IllegalStateException("Must not call get() function from this Handler thread. Will deadlock!");
    }

    @Override // java.util.concurrent.Future, X.AnonymousClass0n9
    public final V get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        if (Looper.myLooper() != this.A00.getLooper() || isDone()) {
            return (V) super.get(j, timeUnit);
        }
        throw new IllegalStateException("Must not call get() function from this Handler thread. Will deadlock!");
    }
}
