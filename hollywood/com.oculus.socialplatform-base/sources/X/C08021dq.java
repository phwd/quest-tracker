package X;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;

/* renamed from: X.1dq  reason: invalid class name and case insensitive filesystem */
public class C08021dq implements AbstractC07941di {
    @Override // X.AbstractC07941di
    public final void A27() {
    }

    @Override // X.AbstractC07941di
    public final void AAm(int i) {
    }

    @Override // X.AbstractC07941di
    public final void A8l(Bitmap bitmap) {
        if (!(this instanceof C08011dp)) {
            bitmap.recycle();
        }
    }

    @Override // X.AbstractC07941di
    @NonNull
    public final Bitmap A3J(int i, int i2, Bitmap.Config config) {
        return Bitmap.createBitmap(i, i2, config);
    }

    @Override // X.AbstractC07941di
    @NonNull
    public final Bitmap A3n(int i, int i2, Bitmap.Config config) {
        return A3J(i, i2, config);
    }
}
