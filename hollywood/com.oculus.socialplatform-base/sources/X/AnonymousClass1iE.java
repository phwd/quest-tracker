package X;

import android.annotation.TargetApi;
import android.graphics.BitmapFactory;
import android.graphics.ColorSpace;
import com.facebook.soloader.DoNotOptimize;
import javax.annotation.Nullable;

@DoNotOptimize
/* renamed from: X.1iE  reason: invalid class name */
public class AnonymousClass1iE {
    @TargetApi(26)
    public static void A00(BitmapFactory.Options options, @Nullable ColorSpace colorSpace) {
        if (colorSpace == null) {
            colorSpace = ColorSpace.get(ColorSpace.Named.SRGB);
        }
        options.inPreferredColorSpace = colorSpace;
    }
}
