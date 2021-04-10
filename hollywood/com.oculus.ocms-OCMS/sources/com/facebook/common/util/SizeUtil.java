package com.facebook.common.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import androidx.annotation.DimenRes;
import androidx.annotation.Dimension;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.ipc.activity.ActivityConstants;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class SizeUtil {
    @Dimension
    public static int convertDipsToPixels(Context context, @Dimension(unit = 0) float f) {
        float f2 = f * context.getResources().getDisplayMetrics().density;
        return (int) (f2 >= 0.0f ? f2 + 0.5f : f2 - 0.5f);
    }

    @Dimension
    public static int convertDipsToPixels(Resources resources, @Dimension(unit = 0) float f) {
        float f2 = f * resources.getDisplayMetrics().density;
        return (int) (f2 >= 0.0f ? f2 + 0.5f : f2 - 0.5f);
    }

    @Dimension
    public static int convertSpsToPixels(Context context, @Dimension(unit = 2) float f) {
        float f2 = f * context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (f2 >= 0.0f ? f2 + 0.5f : f2 - 0.5f);
    }

    @Dimension(unit = 2)
    public static int convertPixelsToSps(Context context, @Dimension float f) {
        return (int) (f / context.getResources().getDisplayMetrics().scaledDensity);
    }

    @Dimension
    public static int convertSpsToPixels(Resources resources, @Dimension(unit = 2) float f) {
        float f2 = f * resources.getDisplayMetrics().scaledDensity;
        return (int) (f2 >= 0.0f ? f2 + 0.5f : f2 - 0.5f);
    }

    @Dimension(unit = 0)
    public static int convertPixelsToDips(Context context, @Dimension float f) {
        return (int) (f / context.getResources().getDisplayMetrics().density);
    }

    @Dimension(unit = 0)
    public static int convertPixelsToDips(Resources resources, @Dimension float f) {
        return (int) (f / resources.getDisplayMetrics().density);
    }

    public static String getScaleString(Resources resources) {
        int i;
        if (resources != null && (i = resources.getDisplayMetrics().densityDpi) > 160) {
            return i >= 320 ? "2" : "1.5";
        }
        return ActivityConstants.Extras.WATCH_FEED_INJECTION;
    }

    @Dimension
    public static int getFontSizePx(Resources resources, @DimenRes int i) {
        return Math.round(((float) resources.getDimensionPixelSize(i)) * resources.getConfiguration().fontScale);
    }

    @Dimension(unit = 0)
    public static int getDimenInDips(Resources resources, @DimenRes int i) {
        return convertPixelsToDips(resources, resources.getDimension(i));
    }

    @Dimension(unit = 2)
    public static int getFontSizeSp(Resources resources, @DimenRes int i) {
        TypedValue typedValue = new TypedValue();
        resources.getValue(i, typedValue, false);
        return Math.round(TypedValue.complexToFloat(typedValue.data));
    }
}
