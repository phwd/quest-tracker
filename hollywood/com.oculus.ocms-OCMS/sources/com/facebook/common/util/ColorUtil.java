package com.facebook.common.util;

import android.graphics.Color;
import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.core.graphics.ColorUtils;
import com.facebook.common.string.StringUtil;
import com.facebook.common.util.lite.ColorDarkener;
import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.Nullsafe;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class ColorUtil {
    private static final Pattern RGBA_COLOR_PATTERN = Pattern.compile("^([a-fA-F0-9]{2})([a-fA-F0-9]{2})([a-fA-F0-9]{2})([a-fA-F0-9]{2})$");
    private static final String TAG = "ColorUtil";

    @ColorInt
    public static int darkenColor(@ColorInt int i, @FloatRange(from = 0.0d, to = 1.0d) float f) {
        return ColorDarkener.darkenColor(i, f);
    }

    @ColorInt
    public static int lightenColor(@ColorInt int i, @FloatRange(from = 0.0d, to = 1.0d) float f) {
        return ColorDarkener.lightenColor(i, f);
    }

    @ColorInt
    public static int getColorWithAlphaMultiplier(@ColorInt int i, float f) {
        return ColorUtils.setAlphaComponent(i, Math.round(((float) Color.alpha(i)) * f));
    }

    @ColorInt
    public static int setAlphaComponent(@ColorInt int i, @FloatRange(from = 0.0d, to = 1.0d) float f) {
        return ColorUtils.setAlphaComponent(i, (int) (f * 255.0f));
    }

    @ColorInt
    public static int blendColor(@ColorInt int i, @ColorInt int i2, @FloatRange(from = 0.0d, to = 1.0d) float f) {
        float f2 = 1.0f - f;
        return Color.argb(Math.round((((float) Color.alpha(i)) * f2) + (((float) Color.alpha(i2)) * f)), Math.round((((float) Color.red(i)) * f2) + (((float) Color.red(i2)) * f)), Math.round((((float) Color.green(i)) * f2) + (((float) Color.green(i2)) * f)), Math.round((((float) Color.blue(i)) * f2) + (((float) Color.blue(i2)) * f)));
    }

    public static int getColorFromHex(String str, @ColorInt int i) {
        try {
            if (!StringUtil.isBlank(str) && !str.startsWith("#")) {
                str = '#' + str;
            }
            return Color.parseColor(str);
        } catch (Exception unused) {
            return i;
        }
    }

    public static boolean isDark(@ColorInt int i) {
        return ColorDarkener.isDark(i);
    }

    public static boolean isVeryDark(@ColorInt int i) {
        return ColorDarkener.isVeryDark(i);
    }

    public static boolean isLuminanceLessThanThreshold(@ColorInt int i, float f) {
        return ColorUtils.calculateLuminance(i) < ((double) f);
    }

    public static int alphaComposite(int i, int i2) {
        float alpha = ((float) Color.alpha(i)) / 255.0f;
        float f = 1.0f - alpha;
        return Color.rgb((int) ((((float) Color.red(i2)) * f) + (((float) Color.red(i)) * alpha)), (int) ((((float) Color.green(i2)) * f) + (((float) Color.green(i)) * alpha)), (int) ((f * ((float) Color.blue(i2))) + (alpha * ((float) Color.blue(i)))));
    }

    public static String getColorStringFromColor(@ColorInt int i) {
        return "#" + Integer.toHexString(i);
    }

    @Nullable
    public static String convertRGBAToARGB(@Nullable String str) {
        if (str == null) {
            return null;
        }
        Matcher matcher = RGBA_COLOR_PATTERN.matcher(str);
        if (!matcher.matches()) {
            return null;
        }
        return matcher.group(4) + matcher.group(1) + matcher.group(2) + matcher.group(3);
    }

    @Nullable
    public static String convertARGBToRGBA(@Nullable String str) {
        if (str == null) {
            return null;
        }
        Matcher matcher = RGBA_COLOR_PATTERN.matcher(str);
        if (!matcher.matches()) {
            return null;
        }
        return matcher.group(2) + matcher.group(3) + matcher.group(4) + matcher.group(1);
    }

    @ColorInt
    public static int parseColorString(@Nullable String str, @ColorInt int i) {
        if (StringUtil.isEmptyOrNull(str)) {
            return i;
        }
        try {
            if (str.charAt(0) != '#') {
                str = "#" + str;
            }
            return Color.parseColor(str);
        } catch (IllegalArgumentException e) {
            BLog.e(TAG, "color", e);
            return i;
        }
    }

    @ColorInt
    public static int pickForeground(@ColorInt int i, @ColorInt int i2, @ColorInt int i3) {
        return ((((float) Color.red(i)) * 0.299f) + (((float) Color.green(i)) * 0.587f)) + (((float) Color.blue(i)) * 0.114f) > 150.0f ? i3 : i2;
    }

    @ColorInt
    public static int pickForegroundWithHigherContrast(@ColorInt int i, @ColorInt int i2, @ColorInt int i3) {
        return ColorUtils.calculateContrast(i2, i) > ColorUtils.calculateContrast(i3, i) ? i2 : i3;
    }
}
