package X;

import android.os.Handler;
import android.os.Looper;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0Vi  reason: invalid class name and case insensitive filesystem */
public final class C01240Vi extends AnonymousClass0sG {
    @Nullable
    public static C01240Vi A00;

    public C01240Vi() {
        super(new Handler(Looper.getMainLooper()));
    }

    @Override // X.AnonymousClass0sG
    public final void execute(Runnable runnable) {
        if (Thread.currentThread() == this.A00.getLooper().getThread()) {
            runnable.run();
        } else {
            super.execute(runnable);
        }
    }
}
