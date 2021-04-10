package defpackage;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import org.chromium.base.SysUtils;

/* renamed from: ye0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC5794ye0 {
    public static Bitmap a(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        int b = b();
        Matrix matrix = new Matrix();
        int max = Math.max(bitmap.getWidth(), bitmap.getHeight());
        if (max < b()) {
            return bitmap;
        }
        matrix.postTranslate(((float) bitmap.getWidth()) / -2.0f, ((float) bitmap.getHeight()) / -2.0f);
        float f = (float) b;
        float f2 = (1.0f * f) / ((float) max);
        matrix.postScale(f2, f2);
        float f3 = f / 2.0f;
        matrix.postTranslate(f3, f3);
        Bitmap createBitmap = Bitmap.createBitmap(b, b, Bitmap.Config.ARGB_8888);
        new Canvas(createBitmap).drawBitmap(bitmap, matrix, new Paint(2));
        return createBitmap;
    }

    public static int b() {
        return SysUtils.isLowEndDevice() ? 256 : 512;
    }
}
