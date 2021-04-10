package com.oculus.ocui;

import android.content.res.Resources;
import android.util.Log;
import android.util.TypedValue;
import com.oculus.os.SettingsManager;

public final class OCTextUtils {
    private static int sFontSizeSetting;

    static {
        try {
            sFontSizeSetting = new SettingsManager().getInt(SettingsManager.FONT_SIZE, 0);
        } catch (Exception e) {
            Log.e("OCUI", "Could not get font scale from SettingsManager. Using default size instead.", e);
        }
    }

    public static float getFontScaledSizeSP(Resources resources, int i) {
        return getFontScaledSize(resources, 2, i);
    }

    public static float getFontScaledSize(Resources resources, int i, int i2) {
        return getFontScaledSize(resources, (float) ((int) TypedValue.applyDimension(i, (float) i2, resources.getDisplayMetrics())));
    }

    public static float getFontScaledSize(Resources resources, float f) {
        float pixels;
        int fontSizeSetting = getFontSizeSetting();
        if (fontSizeSetting == -1) {
            pixels = getPixels(resources, -2);
        } else if (fontSizeSetting == 1) {
            pixels = getPixels(resources, 2);
        } else if (fontSizeSetting == 2) {
            pixels = getPixels(resources, 4);
        } else if (fontSizeSetting != 3) {
            pixels = getPixels(resources, fontSizeSetting);
        } else {
            pixels = getPixels(resources, 6);
        }
        return f + pixels;
    }

    public static int getFontSizeSetting() {
        return sFontSizeSetting;
    }

    private static float getPixels(Resources resources, int i) {
        return TypedValue.applyDimension(2, (float) i, resources.getDisplayMetrics());
    }
}
