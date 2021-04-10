package X;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import androidx.annotation.NonNull;

/* renamed from: X.1dT  reason: invalid class name and case insensitive filesystem */
public final class C07831dT implements AnonymousClass1fR<BitmapDrawable>, AbstractC08111dz {
    public final Resources A00;
    public final AnonymousClass1fR<Bitmap> A01;

    @Override // X.AnonymousClass1fR
    @NonNull
    public final Class<BitmapDrawable> A4o() {
        return BitmapDrawable.class;
    }

    @Override // X.AnonymousClass1fR
    public final void A8u() {
        this.A01.A8u();
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass1fR
    @NonNull
    public final BitmapDrawable get() {
        return new BitmapDrawable(this.A00, this.A01.get());
    }

    @Override // X.AnonymousClass1fR
    public final int getSize() {
        return this.A01.getSize();
    }

    @Override // X.AbstractC08111dz
    public final void initialize() {
        AnonymousClass1fR<Bitmap> r1 = this.A01;
        if (r1 instanceof AbstractC08111dz) {
            ((AbstractC08111dz) r1).initialize();
        }
    }

    public C07831dT(@NonNull Resources resources, @NonNull AnonymousClass1fR<Bitmap> r2) {
        AnonymousClass1S2.A00(resources);
        this.A00 = resources;
        AnonymousClass1S2.A00(r2);
        this.A01 = r2;
    }
}
