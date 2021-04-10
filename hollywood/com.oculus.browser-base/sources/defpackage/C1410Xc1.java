package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextPaint;
import com.oculus.browser.R;
import java.util.Locale;

/* renamed from: Xc1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1410Xc1 extends C0636Ki1 {
    public final float b;
    public final float c;
    public final Rect d = new Rect();
    public final TextPaint e;
    public int f;
    public boolean g;

    public C1410Xc1(Context context, boolean z, Bitmap bitmap) {
        super(context, bitmap);
        c(AbstractC1300Vg1.b(context, z));
        this.b = context.getResources().getDimension(R.dimen.f26430_resource_name_obfuscated_RES_2131166262);
        this.c = context.getResources().getDimension(R.dimen.f26440_resource_name_obfuscated_RES_2131166263);
        TextPaint textPaint = new TextPaint();
        this.e = textPaint;
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTypeface(Typeface.create("sans-serif-condensed", 1));
        textPaint.setColor(f());
    }

    public static C1410Xc1 e(Context context, boolean z) {
        return new C1410Xc1(context, z, BitmapFactory.decodeResource(context.getResources(), R.drawable.f28650_resource_name_obfuscated_RES_2131230905));
    }

    @Override // defpackage.C0636Ki1
    public void c(ColorStateList colorStateList) {
        if (this.f8382a != colorStateList) {
            this.f8382a = colorStateList;
            d();
        }
        TextPaint textPaint = this.e;
        if (textPaint != null) {
            textPaint.setColor(f());
        }
    }

    public void draw(Canvas canvas) {
        String str;
        super.draw(canvas);
        int i = this.f;
        if (i <= 0) {
            str = "";
        } else if (i > 99) {
            str = this.g ? ";)" : ":D";
        } else {
            str = String.format(Locale.getDefault(), "%d", Integer.valueOf(this.f));
        }
        if (!str.isEmpty()) {
            this.e.getTextBounds(str, 0, str.length(), this.d);
            Rect bounds = getBounds();
            Rect rect = this.d;
            int i2 = rect.bottom;
            canvas.drawText(str, (float) (bounds.width() / 2), (float) ((((i2 - rect.top) / 2) + (bounds.height() / 2)) - i2), this.e);
        }
    }

    public final int f() {
        return this.f8382a.getColorForState(getState(), 0);
    }

    public void g(int i, boolean z) {
        if (i != this.f || z != this.g) {
            this.f = i;
            this.g = z;
            this.e.setTextSize(i > 9 ? this.c : this.b);
            invalidateSelf();
        }
    }

    @Override // defpackage.C0636Ki1
    public boolean onStateChange(int[] iArr) {
        boolean onStateChange = super.onStateChange(iArr);
        if (onStateChange) {
            this.e.setColor(f());
        }
        return onStateChange;
    }
}
