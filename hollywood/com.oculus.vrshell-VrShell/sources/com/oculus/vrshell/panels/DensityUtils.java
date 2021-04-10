package com.oculus.vrshell.panels;

import android.util.DisplayMetrics;
import android.util.TypedValue;

public final class DensityUtils {
    public static int dipToPixelsInt(float f, DisplayMetrics displayMetrics) {
        if (f >= 0.0f) {
            return Math.round(TypedValue.applyDimension(1, f, displayMetrics));
        }
        return -dipToPixelsInt(-f, displayMetrics);
    }
}
