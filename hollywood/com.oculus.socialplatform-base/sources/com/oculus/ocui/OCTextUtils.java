package com.oculus.ocui;

import android.content.res.Resources;
import android.util.Log;
import android.util.TypedValue;
import com.oculus.os.SettingsManager;

public final class OCTextUtils {
    public static int sFontSizeSetting;

    public static float getFontScaledSizeSP(Resources resources, int i) {
        return getFontScaledSize(resources, 2, i);
    }

    public static float getPixels(Resources resources, int i) {
        return TypedValue.applyDimension(2, (float) i, resources.getDisplayMetrics());
    }

    static {
        try {
            sFontSizeSetting = new SettingsManager().getInt("font_size", 0);
        } catch (Exception e) {
            Log.e("OCUI", "Could not get font scale from SettingsManager. Using default size instead.", e);
        }
    }

    public static int getFontSizeSetting() {
        return sFontSizeSetting;
    }

    public static float getFontScaledSize(Resources resources, float f) {
        int i;
        float pixels;
        int i2 = sFontSizeSetting;
        if (i2 != -1) {
            i = 2;
            if (i2 != 1) {
                if (i2 == 2) {
                    i = 4;
                } else if (i2 != 3) {
                    pixels = getPixels(resources, i2);
                } else {
                    i = 6;
                }
            }
            pixels = getPixels(resources, i);
        } else {
            i = -2;
            pixels = getPixels(resources, i);
        }
        return f + pixels;
    }

    public static float getFontScaledSize(Resources resources, int i, int i2) {
        return getFontScaledSize(resources, (float) ((int) TypedValue.applyDimension(i, (float) i2, resources.getDisplayMetrics())));
    }
}
