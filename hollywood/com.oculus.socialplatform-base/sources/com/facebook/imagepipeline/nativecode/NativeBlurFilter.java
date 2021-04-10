package com.facebook.imagepipeline.nativecode;

import X.AnonymousClass0lD;
import android.graphics.Bitmap;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.infer.annotation.Nullsafe;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.STRICT)
public class NativeBlurFilter {
    @DoNotStrip
    public static native void nativeIterativeBoxBlur(Bitmap bitmap, int i, int i2);

    static {
        AnonymousClass0lD.A01("native-filters");
    }
}
