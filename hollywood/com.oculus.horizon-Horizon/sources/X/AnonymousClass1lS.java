package X;

import android.os.Handler;
import android.os.Looper;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1lS  reason: invalid class name */
public final class AnonymousClass1lS extends AnonymousClass1lQ {
    @Nullable
    public static AnonymousClass1lS A00;

    public AnonymousClass1lS() {
        super(new Handler(Looper.getMainLooper()));
    }

    @Override // X.AnonymousClass1lQ
    public final void execute(Runnable runnable) {
        if (Thread.currentThread() == this.A00.getLooper().getThread()) {
            runnable.run();
        } else {
            super.execute(runnable);
        }
    }
}
