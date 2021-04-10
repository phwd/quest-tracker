package com.facebook.common.util.lite;

import android.graphics.Color;
import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.core.graphics.ColorUtils;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ColorDarkener {
    @ColorInt
    public static int darkenColor(@ColorInt int i, @FloatRange(from = 0.0d, to = 1.0d) float f) {
        return Color.rgb(Math.round(((float) Color.red(i)) * f), Math.round(((float) Color.green(i)) * f), Math.round(f * ((float) Color.blue(i))));
    }

    @ColorInt
    public static int lightenColor(@ColorInt int i, @FloatRange(from = 0.0d, to = 1.0d) float f) {
        int red = Color.red(i);
        int green = Color.green(i);
        int blue = Color.blue(i);
        float f2 = 1.0f - f;
        return Color.rgb(Math.round(((float) red) + (((float) (255 - red)) * f2)), Math.round(((float) green) + (((float) (255 - green)) * f2)), Math.round(((float) blue) + (((float) (255 - blue)) * f2)));
    }

    public static boolean isDark(@ColorInt int i) {
        return ColorUtils.calculateLuminance(i) < 0.7d;
    }

    public static boolean isVeryDark(@ColorInt int i) {
        return ColorUtils.calculateLuminance(i) < 0.1d;
    }
}
