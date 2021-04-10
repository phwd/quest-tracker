package X;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import androidx.annotation.NonNull;
import java.io.File;

/* renamed from: X.1dF  reason: invalid class name */
public final class AnonymousClass1dF implements AnonymousClass1dV<BitmapDrawable> {
    public final AnonymousClass1dV<Bitmap> A00;
    public final AbstractC07941di A01;

    @Override // X.AbstractC06701at
    public final boolean A2m(@NonNull Object obj, @NonNull File file, @NonNull AnonymousClass1cO r7) {
        return this.A00.A2m(new C08121e1(((BitmapDrawable) ((AnonymousClass1fR) obj).get()).getBitmap(), this.A01), file, r7);
    }

    @Override // X.AnonymousClass1dV
    @NonNull
    public final Integer A3s(@NonNull AnonymousClass1cO r2) {
        return this.A00.A3s(r2);
    }

    public AnonymousClass1dF(AbstractC07941di r1, AnonymousClass1dV<Bitmap> r2) {
        this.A01 = r1;
        this.A00 = r2;
    }
}
