package com.oculus.vrshell.panels;

import android.util.DisplayMetrics;
import android.util.TypedValue;

public final class DensityUtils {
    public static int dipToPixelsInt(float valueInDip, DisplayMetrics displayMetrics) {
        if (valueInDip >= 0.0f) {
            return Math.round(TypedValue.applyDimension(1, valueInDip, displayMetrics));
        }
        return -dipToPixelsInt(-valueInDip, displayMetrics);
    }
}
