package defpackage;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.oculus.browser.R;

/* renamed from: oO  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4055oO {
    public static KN0 a(Resources resources) {
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.f17330_resource_name_obfuscated_RES_2131165352);
        return new KN0(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize / 2, resources.getColor(R.color.f11180_resource_name_obfuscated_RES_2131099808), (float) resources.getDimensionPixelSize(R.dimen.f17340_resource_name_obfuscated_RES_2131165353));
    }

    public static IN0 b(Resources resources, Bitmap bitmap) {
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.f18010_resource_name_obfuscated_RES_2131165420);
        HN0 hn0 = new HN0(resources, bitmap);
        hn0.b((float) dimensionPixelSize);
        return hn0;
    }

    public static Drawable c(Bitmap bitmap, String str, int i, KN0 kn0, Resources resources, int i2) {
        if (bitmap != null) {
            return b(resources, Bitmap.createScaledBitmap(bitmap, i2, i2, false));
        }
        kn0.e.setColor(i);
        return new BitmapDrawable(resources, kn0.c(str, false));
    }
}
