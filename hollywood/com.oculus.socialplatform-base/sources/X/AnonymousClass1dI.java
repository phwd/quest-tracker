package X;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* renamed from: X.1dI  reason: invalid class name */
public final class AnonymousClass1dI implements AbstractC08801fP<Drawable, byte[]> {
    public final AbstractC07941di A00;
    public final AbstractC08801fP<Bitmap, byte[]> A01;
    public final AbstractC08801fP<AnonymousClass1gA, byte[]> A02;

    public AnonymousClass1dI(@NonNull AbstractC07941di r1, @NonNull AbstractC08801fP<Bitmap, byte[]> r2, @NonNull AbstractC08801fP<AnonymousClass1gA, byte[]> r3) {
        this.A00 = r1;
        this.A01 = r2;
        this.A02 = r3;
    }

    @Override // X.AbstractC08801fP
    @Nullable
    public final AnonymousClass1fR<byte[]> AAj(@NonNull AnonymousClass1fR<Drawable> r5, @NonNull AnonymousClass1cO r6) {
        C08121e1 r0;
        Drawable drawable = r5.get();
        if (drawable instanceof BitmapDrawable) {
            AbstractC08801fP<Bitmap, byte[]> r3 = this.A01;
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            AbstractC07941di r1 = this.A00;
            if (bitmap == null) {
                r0 = null;
            } else {
                r0 = new C08121e1(bitmap, r1);
            }
            return r3.AAj(r0, r6);
        } else if (drawable instanceof AnonymousClass1gA) {
            return this.A02.AAj(r5, r6);
        } else {
            return null;
        }
    }
}
