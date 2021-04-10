package com.bumptech.glide.load.resource.bitmap;

import X.AbstractC06491aL;
import X.AbstractC08371eV;
import androidx.annotation.NonNull;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

public final class GranularRoundedCorners extends AbstractC08371eV {
    public static final byte[] A04 = "com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners".getBytes(AbstractC06491aL.A00);
    public final float A00;
    public final float A01;
    public final float A02;
    public final float A03;

    @Override // X.AbstractC06491aL
    public final void AAv(@NonNull MessageDigest messageDigest) {
        messageDigest.update(A04);
        ByteBuffer allocate = ByteBuffer.allocate(16);
        allocate.putFloat(this.A02);
        allocate.putFloat(this.A03);
        allocate.putFloat(this.A01);
        allocate.putFloat(this.A00);
        messageDigest.update(allocate.array());
    }

    @Override // X.AbstractC06491aL
    public final boolean equals(Object obj) {
        if (!(obj instanceof GranularRoundedCorners)) {
            return false;
        }
        GranularRoundedCorners granularRoundedCorners = (GranularRoundedCorners) obj;
        if (this.A02 == granularRoundedCorners.A02 && this.A03 == granularRoundedCorners.A03 && this.A01 == granularRoundedCorners.A01 && this.A00 == granularRoundedCorners.A00) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC06491aL
    public final int hashCode() {
        int floatToIntBits = ((((((527 + Float.floatToIntBits(this.A02)) * 31) - 2013597734) * 31) + Float.floatToIntBits(this.A03)) * 31) + Float.floatToIntBits(this.A01);
        return (floatToIntBits * 31) + Float.floatToIntBits(this.A00);
    }

    public GranularRoundedCorners(float f, float f2, float f3, float f4) {
        this.A02 = f;
        this.A03 = f2;
        this.A01 = f3;
        this.A00 = f4;
    }
}
