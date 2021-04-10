package com.facebook.imagepipeline.nativecode;

import X.AnonymousClass0lD;
import android.graphics.Bitmap;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.infer.annotation.Nullsafe;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.STRICT)
public class NativeRoundingFilter {
    @DoNotStrip
    public static native void nativeAddRoundedCornersFilter(Bitmap bitmap, int i, int i2, int i3, int i4);

    @DoNotStrip
    public static native void nativeToCircleFastFilter(Bitmap bitmap, boolean z);

    @DoNotStrip
    public static native void nativeToCircleFilter(Bitmap bitmap, boolean z);

    @DoNotStrip
    public static native void nativeToCircleWithBorderFilter(Bitmap bitmap, int i, int i2, boolean z);

    static {
        AnonymousClass0lD.A01("native-filters");
    }

    @DoNotStrip
    public static void toCircle(Bitmap bitmap, boolean z) {
        if (bitmap == null) {
            throw null;
        } else if (bitmap.getWidth() >= 3 && bitmap.getHeight() >= 3) {
            nativeToCircleFilter(bitmap, z);
        }
    }

    @DoNotStrip
    public static void toCircleFast(Bitmap bitmap, boolean z) {
        if (bitmap == null) {
            throw null;
        } else if (bitmap.getWidth() >= 3 && bitmap.getHeight() >= 3) {
            nativeToCircleFastFilter(bitmap, z);
        }
    }
}
