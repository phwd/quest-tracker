package X;

import android.graphics.Color;
import androidx.annotation.ColorInt;
import com.oculus.localmedia.MediaProviderUtils;

/* renamed from: X.056  reason: invalid class name */
public final class AnonymousClass056 {
    public static final ThreadLocal<double[]> A00 = new ThreadLocal<>();

    public static int A00(@ColorInt int i, @ColorInt int i2) {
        int alpha = Color.alpha(i2);
        int alpha2 = Color.alpha(i);
        int i3 = 255 - alpha2;
        int i4 = 255 - (((255 - alpha) * i3) / MediaProviderUtils.JPEG_HEADER);
        int red = Color.red(i);
        int red2 = Color.red(i2);
        int i5 = 0;
        if (i4 != 0) {
            i5 = (((red * MediaProviderUtils.JPEG_HEADER) * alpha2) + ((red2 * alpha) * i3)) / (i4 * MediaProviderUtils.JPEG_HEADER);
        }
        int green = Color.green(i);
        int green2 = Color.green(i2);
        int i6 = 0;
        if (i4 != 0) {
            i6 = (((green * MediaProviderUtils.JPEG_HEADER) * alpha2) + ((green2 * alpha) * i3)) / (i4 * MediaProviderUtils.JPEG_HEADER);
        }
        int blue = Color.blue(i);
        int blue2 = Color.blue(i2);
        int i7 = 0;
        if (i4 != 0) {
            i7 = (((blue * MediaProviderUtils.JPEG_HEADER) * alpha2) + ((blue2 * alpha) * i3)) / (i4 * MediaProviderUtils.JPEG_HEADER);
        }
        return Color.argb(i4, i5, i6, i7);
    }
}
