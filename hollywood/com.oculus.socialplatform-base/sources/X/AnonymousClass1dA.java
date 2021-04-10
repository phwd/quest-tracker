package X;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;

/* renamed from: X.1dA  reason: invalid class name */
public final class AnonymousClass1dA implements AnonymousClass1fR<Bitmap> {
    public final Bitmap A00;

    @Override // X.AnonymousClass1fR
    public final void A8u() {
    }

    @Override // X.AnonymousClass1fR
    @NonNull
    public final Class<Bitmap> A4o() {
        return Bitmap.class;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass1fR
    @NonNull
    public final Bitmap get() {
        return this.A00;
    }

    @Override // X.AnonymousClass1fR
    public final int getSize() {
        return C08381eW.A01(this.A00);
    }

    public AnonymousClass1dA(@NonNull Bitmap bitmap) {
        this.A00 = bitmap;
    }
}
