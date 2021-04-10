package X;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import java.security.MessageDigest;

/* renamed from: X.1eq  reason: invalid class name and case insensitive filesystem */
public final class C08561eq implements AnonymousClass1eU<AnonymousClass1gA> {
    public final AnonymousClass1eU<Bitmap> A00;

    @Override // X.AbstractC06491aL
    public final void AAv(@NonNull MessageDigest messageDigest) {
        this.A00.AAv(messageDigest);
    }

    @Override // X.AbstractC06491aL
    public final boolean equals(Object obj) {
        if (obj instanceof C08561eq) {
            return this.A00.equals(((C08561eq) obj).A00);
        }
        return false;
    }

    @Override // X.AbstractC06491aL
    public final int hashCode() {
        return this.A00.hashCode();
    }

    public C08561eq(AnonymousClass1eU<Bitmap> r1) {
        AnonymousClass1S2.A00(r1);
        this.A00 = r1;
    }

    @Override // X.AnonymousClass1eU
    @NonNull
    public final AnonymousClass1fR<AnonymousClass1gA> AAk(@NonNull Context context, @NonNull AnonymousClass1fR<AnonymousClass1gA> r7, int i, int i2) {
        AbstractC07941di r1 = ComponentCallbacks2C07631cl.A01(context).A02;
        AnonymousClass1g5 r4 = r7.get().A09.A00;
        C08121e1 r3 = new C08121e1(r4.A03, r1);
        AnonymousClass1eU<Bitmap> r2 = this.A00;
        AnonymousClass1fR<Bitmap> AAk = r2.AAk(context, r3, i, i2);
        if (!r3.equals(AAk)) {
            r3.A8u();
        }
        r4.A01(r2, AAk.get());
        return r7;
    }
}
