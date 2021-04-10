package X;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import androidx.annotation.NonNull;
import java.io.IOException;

/* renamed from: X.1dK  reason: invalid class name */
public final class AnonymousClass1dK<DataType> implements AnonymousClass1dN<DataType, BitmapDrawable> {
    public final Resources A00;
    public final AnonymousClass1dN<DataType, Bitmap> A01;

    @Override // X.AnonymousClass1dN
    public final AnonymousClass1fR<BitmapDrawable> A2V(@NonNull DataType datatype, int i, int i2, @NonNull AnonymousClass1cO r7) throws IOException {
        AnonymousClass1fR<Bitmap> A2V = this.A01.A2V(datatype, i, i2, r7);
        Resources resources = this.A00;
        if (A2V == null) {
            return null;
        }
        return new C07831dT(resources, A2V);
    }

    @Override // X.AnonymousClass1dN
    public final boolean A5O(@NonNull DataType datatype, @NonNull AnonymousClass1cO r3) throws IOException {
        return this.A01.A5O(datatype, r3);
    }

    public AnonymousClass1dK(@NonNull Resources resources, @NonNull AnonymousClass1dN<DataType, Bitmap> r2) {
        AnonymousClass1S2.A00(resources);
        this.A00 = resources;
        AnonymousClass1S2.A00(r2);
        this.A01 = r2;
    }
}
