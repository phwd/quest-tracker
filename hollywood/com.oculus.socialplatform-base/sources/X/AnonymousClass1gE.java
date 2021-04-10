package X;

import androidx.annotation.NonNull;
import java.security.MessageDigest;

/* renamed from: X.1gE  reason: invalid class name */
public final class AnonymousClass1gE extends AbstractC08371eV {
    public static final byte[] A00 = "com.bumptech.glide.load.resource.bitmap.CircleCrop.1".getBytes(AbstractC06491aL.A00);

    @Override // X.AbstractC06491aL
    public final void AAv(@NonNull MessageDigest messageDigest) {
        messageDigest.update(A00);
    }

    @Override // X.AbstractC06491aL
    public final int hashCode() {
        return 1101716364;
    }

    @Override // X.AbstractC06491aL
    public final boolean equals(Object obj) {
        return obj instanceof AnonymousClass1gE;
    }
}
