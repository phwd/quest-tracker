package X;

import android.annotation.TargetApi;
import android.graphics.BitmapFactory;
import android.graphics.ColorSpace;
import com.facebook.soloader.DoNotOptimize;
import javax.annotation.Nullable;

@DoNotOptimize
/* renamed from: X.1sd  reason: invalid class name and case insensitive filesystem */
public class C10361sd {
    @TargetApi(26)
    public static void A00(BitmapFactory.Options options, @Nullable ColorSpace colorSpace) {
        if (colorSpace == null) {
            colorSpace = ColorSpace.get(ColorSpace.Named.SRGB);
        }
        options.inPreferredColorSpace = colorSpace;
    }
}
