package X;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.ByteArrayOutputStream;

/* renamed from: X.1dE  reason: invalid class name */
public final class AnonymousClass1dE implements AbstractC08801fP<Bitmap, byte[]> {
    public final int A00 = 100;
    public final Bitmap.CompressFormat A01 = Bitmap.CompressFormat.JPEG;

    @Override // X.AbstractC08801fP
    @Nullable
    public final AnonymousClass1fR<byte[]> AAj(@NonNull AnonymousClass1fR<Bitmap> r5, @NonNull AnonymousClass1cO r6) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        r5.get().compress(this.A01, this.A00, byteArrayOutputStream);
        r5.A8u();
        return new AnonymousClass1dU(byteArrayOutputStream.toByteArray());
    }
}
