package X;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;

/* renamed from: X.1e1  reason: invalid class name and case insensitive filesystem */
public final class C08121e1 implements AnonymousClass1fR<Bitmap>, AbstractC08111dz {
    public final Bitmap A00;
    public final AbstractC07941di A01;

    @Override // X.AnonymousClass1fR
    @NonNull
    public final Class<Bitmap> A4o() {
        return Bitmap.class;
    }

    @Override // X.AnonymousClass1fR
    public final void A8u() {
        this.A01.A8l(this.A00);
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

    @Override // X.AbstractC08111dz
    public final void initialize() {
        this.A00.prepareToDraw();
    }

    public C08121e1(@NonNull Bitmap bitmap, @NonNull AbstractC07941di r4) {
        if (bitmap != null) {
            this.A00 = bitmap;
            if (r4 != null) {
                this.A01 = r4;
                return;
            }
            throw new NullPointerException("BitmapPool must not be null");
        }
        throw new NullPointerException("Bitmap must not be null");
    }
}
