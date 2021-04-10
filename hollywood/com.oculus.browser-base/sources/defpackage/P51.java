package defpackage;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/* renamed from: P51  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class P51 implements AbstractC0853Oa {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f8666a;

    public P51(int i) {
        this.f8666a = i;
    }

    @Override // defpackage.AbstractC0853Oa
    public AbstractC3197jM0 a(int i) {
        int i2 = this.f8666a;
        if (i != 0) {
            return null;
        }
        float f = (((float) i2) * 0.5f) / 0.5f;
        float f2 = 0.866f * f;
        float f3 = -f;
        float f4 = f3 / 2.0f;
        float f5 = f3 - f2;
        float f6 = 2.0f * f;
        RectF rectF = new RectF(f4, f5, f4 + f6, f6 + f5);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setAlpha(187);
        paint.setStyle(Paint.Style.FILL);
        Bitmap createBitmap = Bitmap.createBitmap((int) f, (int) (f - f2), Bitmap.Config.ALPHA_8);
        new Canvas(createBitmap).drawArc(rectF, 45.0f, 90.0f, true, paint);
        return new F11(createBitmap);
    }
}
