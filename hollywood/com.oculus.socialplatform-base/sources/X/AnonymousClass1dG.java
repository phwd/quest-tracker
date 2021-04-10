package X;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* renamed from: X.1dG  reason: invalid class name */
public final class AnonymousClass1dG implements AbstractC08801fP<Bitmap, BitmapDrawable> {
    public final Resources A00;

    @Override // X.AbstractC08801fP
    @Nullable
    public final AnonymousClass1fR<BitmapDrawable> AAj(@NonNull AnonymousClass1fR<Bitmap> r3, @NonNull AnonymousClass1cO r4) {
        Resources resources = this.A00;
        if (r3 == null) {
            return null;
        }
        return new C07831dT(resources, r3);
    }

    public AnonymousClass1dG(@NonNull Resources resources) {
        AnonymousClass1S2.A00(resources);
        this.A00 = resources;
    }
}
