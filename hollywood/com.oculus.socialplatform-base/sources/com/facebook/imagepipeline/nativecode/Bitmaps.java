package com.facebook.imagepipeline.nativecode;

import X.AnonymousClass0lD;
import X.C00740Ii;
import android.graphics.Bitmap;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.infer.annotation.Nullsafe;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.STRICT)
public class Bitmaps {
    @DoNotStrip
    public static native void nativeCopyBitmap(Bitmap bitmap, int i, Bitmap bitmap2, int i2, int i3);

    static {
        AnonymousClass0lD.A01("imagepipeline");
    }

    @DoNotStrip
    public static void copyBitmap(Bitmap bitmap, Bitmap bitmap2) {
        boolean z = true;
        boolean z2 = false;
        if (bitmap2.getConfig() == bitmap.getConfig()) {
            z2 = true;
        }
        C00740Ii.A01(Boolean.valueOf(z2));
        C00740Ii.A01(Boolean.valueOf(bitmap.isMutable()));
        boolean z3 = false;
        if (bitmap.getWidth() == bitmap2.getWidth()) {
            z3 = true;
        }
        C00740Ii.A01(Boolean.valueOf(z3));
        if (bitmap.getHeight() != bitmap2.getHeight()) {
            z = false;
        }
        C00740Ii.A01(Boolean.valueOf(z));
        nativeCopyBitmap(bitmap, bitmap.getRowBytes(), bitmap2, bitmap2.getRowBytes(), bitmap.getHeight());
    }
}
