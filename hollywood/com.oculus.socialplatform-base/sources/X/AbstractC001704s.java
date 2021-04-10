package X;

import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

/* renamed from: X.04s  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC001704s {
    public abstract void A02(@NonNull Typeface typeface);

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public final void A00() {
        new Handler(Looper.getMainLooper()).post(new RunnableC001604r(this));
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public final void A01(Typeface typeface) {
        new Handler(Looper.getMainLooper()).post(new AnonymousClass04q(this, typeface));
    }
}
