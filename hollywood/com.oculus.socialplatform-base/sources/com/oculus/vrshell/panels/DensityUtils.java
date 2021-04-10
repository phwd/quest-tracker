package com.oculus.vrshell.panels;

import android.util.DisplayMetrics;
import android.util.TypedValue;
import com.oculus.vrshell.panels.AndroidPanelLayer;

public final class DensityUtils {
    public static int dipToPixelsInt(float f, DisplayMetrics displayMetrics) {
        if (f >= AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) {
            return Math.round(TypedValue.applyDimension(1, f, displayMetrics));
        }
        return -dipToPixelsInt(-f, displayMetrics);
    }
}
