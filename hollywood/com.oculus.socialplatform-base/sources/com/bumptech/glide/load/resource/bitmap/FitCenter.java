package com.bumptech.glide.load.resource.bitmap;

import X.AbstractC06491aL;
import X.AbstractC08371eV;
import androidx.annotation.NonNull;
import java.security.MessageDigest;

public final class FitCenter extends AbstractC08371eV {
    public static final byte[] A00 = "com.bumptech.glide.load.resource.bitmap.FitCenter".getBytes(AbstractC06491aL.A00);

    @Override // X.AbstractC06491aL
    public final void AAv(@NonNull MessageDigest messageDigest) {
        messageDigest.update(A00);
    }

    @Override // X.AbstractC06491aL
    public final int hashCode() {
        return 1572326941;
    }

    @Override // X.AbstractC06491aL
    public final boolean equals(Object obj) {
        return obj instanceof FitCenter;
    }
}
