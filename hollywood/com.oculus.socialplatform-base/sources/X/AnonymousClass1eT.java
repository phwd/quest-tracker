package X;

import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;

/* renamed from: X.1eT  reason: invalid class name */
public final class AnonymousClass1eT extends AbstractC08591et<Drawable> {
    @Override // X.AnonymousClass1fR
    public final void A8u() {
    }

    /* JADX DEBUG: Type inference failed for r0v1. Raw type applied. Possible types: java.lang.Class<?>, java.lang.Class<android.graphics.drawable.Drawable> */
    @Override // X.AnonymousClass1fR
    @NonNull
    public final Class<Drawable> A4o() {
        return this.A00.getClass();
    }

    @Override // X.AnonymousClass1fR
    public final int getSize() {
        T t = this.A00;
        return Math.max(1, (t.getIntrinsicWidth() * t.getIntrinsicHeight()) << 2);
    }

    public AnonymousClass1eT(Drawable drawable) {
        super(drawable);
    }
}
