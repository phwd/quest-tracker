package org.chromium.ui.gfx;

import android.graphics.Bitmap;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BitmapHelper {
    public static Bitmap createBitmap(int i, int i2, int i3, boolean z) {
        Bitmap.Config config;
        if (i3 == 4) {
            config = Bitmap.Config.RGB_565;
        } else if (i3 == 7) {
            config = Bitmap.Config.ARGB_4444;
        } else if (i3 != 8) {
            config = Bitmap.Config.ARGB_8888;
        } else {
            config = Bitmap.Config.ALPHA_8;
        }
        try {
            return Bitmap.createBitmap(i, i2, config);
        } catch (OutOfMemoryError e) {
            if (z) {
                AbstractC1220Ua0.f("BitmapHelper", "createBitmap OOM-ed", e);
                return null;
            }
            throw e;
        }
    }

    public static int getBitmapFormatForConfig(Bitmap.Config config) {
        int i = AbstractC0448Hh.f8173a[config.ordinal()];
        int i2 = 1;
        if (i == 1) {
            return 8;
        }
        if (i == 2) {
            return 7;
        }
        if (i != 3) {
            i2 = 4;
            if (i != 4) {
                return 0;
            }
        }
        return i2;
    }

    public static int getByteCount(Bitmap bitmap) {
        return bitmap.getByteCount();
    }
}
