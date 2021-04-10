package X;

import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

/* renamed from: X.08A  reason: invalid class name */
public abstract class AnonymousClass08A {
    public abstract void A02(@NonNull Typeface typeface);

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public final void A00() {
        new Handler(Looper.getMainLooper()).post(new AnonymousClass089(this));
    }

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public final void A01(Typeface typeface) {
        new Handler(Looper.getMainLooper()).post(new AnonymousClass088(this, typeface));
    }
}
