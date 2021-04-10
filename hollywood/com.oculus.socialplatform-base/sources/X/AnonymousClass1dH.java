package X;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import java.security.MessageDigest;

/* renamed from: X.1dH  reason: invalid class name */
public final class AnonymousClass1dH implements AnonymousClass1eU<Drawable> {
    public final AnonymousClass1eU<Bitmap> A00;
    public final boolean A01;

    @Override // X.AbstractC06491aL
    public final void AAv(@NonNull MessageDigest messageDigest) {
        this.A00.AAv(messageDigest);
    }

    @Override // X.AbstractC06491aL
    public final boolean equals(Object obj) {
        if (obj instanceof AnonymousClass1dH) {
            return this.A00.equals(((AnonymousClass1dH) obj).A00);
        }
        return false;
    }

    @Override // X.AbstractC06491aL
    public final int hashCode() {
        return this.A00.hashCode();
    }

    public AnonymousClass1dH(AnonymousClass1eU<Bitmap> r1, boolean z) {
        this.A00 = r1;
        this.A01 = z;
    }

    @Override // X.AnonymousClass1eU
    @NonNull
    public final AnonymousClass1fR<Drawable> AAk(@NonNull Context context, @NonNull AnonymousClass1fR<Drawable> r5, int i, int i2) {
        AbstractC07941di r0 = ComponentCallbacks2C07631cl.A01(context).A02;
        Drawable drawable = r5.get();
        AnonymousClass1fR<Bitmap> A002 = C08321eO.A00(r0, drawable, i, i2);
        if (A002 != null) {
            AnonymousClass1fR<Bitmap> AAk = this.A00.AAk(context, A002, i, i2);
            if (!AAk.equals(A002)) {
                return new C07831dT(context.getResources(), AAk);
            }
            AAk.A8u();
        } else if (this.A01) {
            StringBuilder sb = new StringBuilder("Unable to convert ");
            sb.append(drawable);
            sb.append(" to a Bitmap");
            throw new IllegalArgumentException(sb.toString());
        }
        return r5;
    }
}
