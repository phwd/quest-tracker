package com.facebook.drawee.backends.pipeline.debug;

import android.util.SparseIntArray;
import androidx.core.view.InputDeviceCompat;

public class DebugOverlayImageOriginColor {
    private static final SparseIntArray IMAGE_ORIGIN_COLOR_MAP = new SparseIntArray(7);

    static {
        IMAGE_ORIGIN_COLOR_MAP.append(1, -7829368);
        IMAGE_ORIGIN_COLOR_MAP.append(2, -65536);
        IMAGE_ORIGIN_COLOR_MAP.append(3, InputDeviceCompat.SOURCE_ANY);
        IMAGE_ORIGIN_COLOR_MAP.append(4, InputDeviceCompat.SOURCE_ANY);
        IMAGE_ORIGIN_COLOR_MAP.append(5, -16711936);
        IMAGE_ORIGIN_COLOR_MAP.append(6, -16711936);
        IMAGE_ORIGIN_COLOR_MAP.append(7, -16711936);
    }

    public static int getImageOriginColor(int i) {
        return IMAGE_ORIGIN_COLOR_MAP.get(i, -1);
    }
}
