package org.chromium.components.webapps;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Build;
import com.oculus.browser.R;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class WebappsIconUtils {
    public static boolean a() {
        return Build.VERSION.SDK_INT >= 26;
    }

    public static Bitmap b(Bitmap bitmap) {
        AdaptiveIconDrawable adaptiveIconDrawable = (AdaptiveIconDrawable) Icon.createWithAdaptiveBitmap(createHomeScreenIconFromWebIcon(bitmap, true)).loadDrawable(ContextUtils.getApplicationContext());
        Bitmap createBitmap = Bitmap.createBitmap(adaptiveIconDrawable.getIntrinsicWidth(), adaptiveIconDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        adaptiveIconDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        adaptiveIconDrawable.draw(canvas);
        return createBitmap;
    }

    public static Bitmap createHomeScreenIconFromWebIcon(Bitmap bitmap, boolean z) {
        int i;
        int min = Math.min(Math.round(((float) ((ActivityManager) ContextUtils.getApplicationContext().getSystemService("activity")).getLauncherLargeIconSize()) * 1.25f), Math.max(bitmap.getWidth(), bitmap.getHeight()));
        Rect rect = new Rect(0, 0, min, min);
        if (z) {
            i = Math.round(((float) min) * 0.15454549f);
        } else {
            int width = bitmap.getWidth() - 1;
            int height = bitmap.getHeight() - 1;
            i = Color.alpha(bitmap.getPixel(0, 0)) != 0 && Color.alpha(bitmap.getPixel(width, height)) != 0 && Color.alpha(bitmap.getPixel(0, height)) != 0 && Color.alpha(bitmap.getPixel(width, 0)) != 0 ? Math.round(((float) min) * 0.045454547f) : 0;
        }
        int i2 = (i * 2) + min;
        rect.offset(i, i);
        try {
            Bitmap createBitmap = Bitmap.createBitmap(i2, i2, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            Paint paint = new Paint(1);
            paint.setFilterBitmap(true);
            canvas.drawBitmap(bitmap, (Rect) null, rect, paint);
            return createBitmap;
        } catch (OutOfMemoryError unused) {
            AbstractC1220Ua0.a("WebappsIconUtils", "OutOfMemoryError while creating bitmap for home screen icon.", new Object[0]);
            return bitmap;
        }
    }

    public static Bitmap generateHomeScreenIcon(String str, int i, int i2, int i3) {
        Context applicationContext = ContextUtils.getApplicationContext();
        ActivityManager activityManager = (ActivityManager) applicationContext.getSystemService("activity");
        int launcherLargeIconSize = activityManager.getLauncherLargeIconSize();
        int launcherLargeIconDensity = activityManager.getLauncherLargeIconDensity();
        try {
            Bitmap createBitmap = Bitmap.createBitmap(launcherLargeIconSize, launcherLargeIconSize, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            float f = (float) launcherLargeIconSize;
            int i4 = (int) (0.083333336f * f);
            Rect rect = new Rect(0, 0, launcherLargeIconSize, launcherLargeIconSize);
            Drawable d = AbstractC3153j7.d(applicationContext.getResources(), R.mipmap.shortcut_icon_shadow, launcherLargeIconDensity);
            canvas.drawBitmap(d instanceof BitmapDrawable ? ((BitmapDrawable) d).getBitmap() : null, (Rect) null, rect, new Paint(2));
            int i5 = launcherLargeIconSize - (i4 * 2);
            Bitmap c = new KN0(i5, i5, Math.round(0.0625f * f), Color.rgb(i, i2, i3), (float) Math.round(f * 0.33333334f)).c(str, false);
            if (c == null) {
                return null;
            }
            float f2 = (float) i4;
            canvas.drawBitmap(c, f2, f2, (Paint) null);
            return createBitmap;
        } catch (OutOfMemoryError unused) {
            AbstractC1220Ua0.f("WebappsIconUtils", "OutOfMemoryError while trying to draw bitmap on canvas.", new Object[0]);
            return null;
        }
    }

    public static int[] getIconSizes() {
        Context applicationContext = ContextUtils.getApplicationContext();
        float dimension = applicationContext.getResources().getDimension(R.dimen.f26740_resource_name_obfuscated_RES_2131166293);
        float f = applicationContext.getResources().getDisplayMetrics().density;
        return new int[]{Math.round(applicationContext.getResources().getDimension(R.dimen.f26740_resource_name_obfuscated_RES_2131166293)), Math.round((f - 1.0f) * (dimension / f)), Math.round(applicationContext.getResources().getDimension(R.dimen.f26750_resource_name_obfuscated_RES_2131166294)), Math.round(applicationContext.getResources().getDimension(R.dimen.f26760_resource_name_obfuscated_RES_2131166295)), Math.round(applicationContext.getResources().getDimension(R.dimen.f26720_resource_name_obfuscated_RES_2131166291)), Math.round(applicationContext.getResources().getDimension(R.dimen.f26710_resource_name_obfuscated_RES_2131166290)), AbstractC4656rv1.b(applicationContext.getResources().getDisplayMetrics(), 48.0f)};
    }

    public static boolean isIconLargeEnoughForLauncher(int i, int i2) {
        int launcherLargeIconSize = ((ActivityManager) ContextUtils.getApplicationContext().getSystemService("activity")).getLauncherLargeIconSize() / 2;
        return i >= launcherLargeIconSize && i2 >= launcherLargeIconSize;
    }
}
