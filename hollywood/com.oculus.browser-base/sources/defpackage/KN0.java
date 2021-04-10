package defpackage;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.text.TextUtils;
import java.util.Locale;

/* renamed from: KN0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class KN0 {

    /* renamed from: a  reason: collision with root package name */
    public final int f8362a;
    public final int b;
    public final int c;
    public final RectF d;
    public final Paint e;
    public final TextPaint f;
    public final float g;
    public final float h;

    public KN0(Resources resources, int i, int i2, int i3, int i4, int i5) {
        this((int) (resources.getDisplayMetrics().density * ((float) i)), (int) (resources.getDisplayMetrics().density * ((float) i2)), (int) (resources.getDisplayMetrics().density * ((float) i3)), i4, resources.getDisplayMetrics().density * ((float) i5));
    }

    public Bitmap a(String str) {
        Bitmap createBitmap = Bitmap.createBitmap(this.f8362a, this.b, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        RectF rectF = this.d;
        int i = this.c;
        canvas.drawRoundRect(rectF, (float) i, (float) i, this.e);
        String upperCase = str.substring(0, Math.min(1, str.length())).toUpperCase(Locale.getDefault());
        canvas.drawText(upperCase, (((float) this.f8362a) - this.f.measureText(upperCase)) / 2.0f, (float) Math.round(((Math.max((float) this.b, this.g) - this.g) / 2.0f) + this.h), this.f);
        return createBitmap;
    }

    public Bitmap b(String str) {
        return c(str, false);
    }

    public Bitmap c(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String b2 = AbstractC5154ur1.b(str, z);
        if (!TextUtils.isEmpty(b2)) {
            str = b2;
        } else if (str.startsWith("chrome://") || str.startsWith("chrome-native://")) {
            str = "chrome";
        } else {
            try {
                Vo1 vo1 = new Vo1(str);
                if (!TextUtils.isEmpty(vo1.d())) {
                    str = vo1.d();
                }
            } catch (Exception unused) {
                AbstractC1220Ua0.f("RoundedIconGenerator", AbstractC2531fV.f("Unable to parse the URL for generating an icon: ", str), new Object[0]);
            }
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return a(str);
    }

    public KN0(int i, int i2, int i3, int i4, float f2) {
        this.f8362a = i;
        this.b = i2;
        this.c = i3;
        this.d = new RectF(0.0f, 0.0f, (float) i, (float) i2);
        Paint paint = new Paint(1);
        this.e = paint;
        paint.setColor(i4);
        TextPaint textPaint = new TextPaint(1);
        this.f = textPaint;
        textPaint.setColor(-1);
        textPaint.setFakeBoldText(true);
        textPaint.setTextSize(f2);
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        this.g = (float) Math.ceil((double) (fontMetrics.bottom - fontMetrics.top));
        this.h = -fontMetrics.top;
    }
}
