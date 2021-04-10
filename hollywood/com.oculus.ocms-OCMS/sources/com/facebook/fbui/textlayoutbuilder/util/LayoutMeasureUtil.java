package com.facebook.fbui.textlayoutbuilder.util;

import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import androidx.annotation.Nullable;

public class LayoutMeasureUtil {
    public static int getWidth(Layout layout) {
        if (layout == null) {
            return 0;
        }
        int lineCount = layout.getLineCount();
        int i = 0;
        for (int i2 = 0; i2 < lineCount; i2++) {
            i = Math.max(i, (int) layout.getLineRight(i2));
        }
        return i;
    }

    public static int getHeight(@Nullable Layout layout) {
        int i = 0;
        if (layout == null) {
            return 0;
        }
        if (Build.VERSION.SDK_INT < 20 && (layout instanceof StaticLayout)) {
            int max = Math.max(0, layout.getLineCount() - 1);
            float lineDescent = (float) (layout.getLineDescent(max) - layout.getLineAscent(max));
            float spacingAdd = lineDescent - ((lineDescent - layout.getSpacingAdd()) / layout.getSpacingMultiplier());
            if (spacingAdd >= 0.0f) {
                double d = (double) spacingAdd;
                Double.isNaN(d);
                i = (int) (d + 0.5d);
            } else {
                double d2 = (double) (-spacingAdd);
                Double.isNaN(d2);
                i = -((int) (d2 + 0.5d));
            }
        }
        return layout.getHeight() - i;
    }

    public static int getContentLeft(Layout layout) {
        if (layout == null || layout.getLineCount() == 0) {
            return 0;
        }
        int i = Integer.MAX_VALUE;
        for (int i2 = 0; i2 < layout.getLineCount(); i2++) {
            i = Math.min(i, (int) layout.getLineLeft(i2));
        }
        return i;
    }
}
