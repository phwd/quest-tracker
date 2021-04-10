package X;

import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass2O.LIBRARY_GROUP_PREFIX})
/* renamed from: X.2e  reason: invalid class name */
public abstract class AnonymousClass2e {
    public void A02(@NonNull Runnable runnable) {
        ((UH) this).A00.A02(runnable);
    }

    public final void A01(@NonNull Runnable runnable) {
        if (!(this instanceof UG)) {
            ((UH) this).A00.A01(runnable);
        } else {
            ((UG) this).A00.execute(runnable);
        }
    }

    public final boolean A03() {
        if (!(this instanceof UG)) {
            return ((UH) this).A00.A03();
        }
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            return true;
        }
        return false;
    }
}
