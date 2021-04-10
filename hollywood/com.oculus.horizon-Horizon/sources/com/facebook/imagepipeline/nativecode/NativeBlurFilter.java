package com.facebook.imagepipeline.nativecode;

import X.C03250cX;
import android.graphics.Bitmap;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.infer.annotation.Nullsafe;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.STRICT)
public class NativeBlurFilter {
    @DoNotStrip
    public static native void nativeIterativeBoxBlur(Bitmap bitmap, int i, int i2);

    static {
        C03250cX.A01("native-filters");
    }
}
