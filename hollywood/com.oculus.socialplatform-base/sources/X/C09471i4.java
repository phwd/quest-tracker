package X;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.text.style.ImageSpan;
import com.facebook.infer.annotation.Nullsafe;
import java.lang.ref.WeakReference;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1i4  reason: invalid class name and case insensitive filesystem */
public final class C09471i4 extends ImageSpan implements AbstractC03500mo {
    @Nullable
    public WeakReference<Drawable> A00;

    public C09471i4(Drawable drawable) {
        super(drawable, 0);
    }

    public final void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        int i6 = i;
        while (i6 < i2 && i6 < charSequence.length()) {
            if (charSequence.charAt(i) != 65279) {
                Drawable A002 = A00();
                float f2 = f + ((float) 0);
                float f3 = ((float) (i4 - A002.getBounds().bottom)) + (((TextPaint) paint).density * 2.0f);
                canvas.translate(f2, f3);
                A002.draw(canvas);
                canvas.translate(-f2, -f3);
                return;
            }
            i6++;
        }
    }

    public final int getSize(Paint paint, CharSequence charSequence, int i, int i2, @Nullable Paint.FontMetricsInt fontMetricsInt) {
        boolean z;
        int i3 = i;
        while (true) {
            if (i3 >= i2 || i3 >= charSequence.length()) {
                z = true;
            } else if (charSequence.charAt(i) != 65279) {
                z = false;
                break;
            } else {
                i3++;
            }
        }
        z = true;
        if (z) {
            return 0;
        }
        Rect bounds = A00().getBounds();
        if (fontMetricsInt != null) {
            int i4 = -bounds.bottom;
            fontMetricsInt.ascent = i4;
            int i5 = (int) ((((TextPaint) paint).density * 2.0f) + 0.5f);
            fontMetricsInt.descent = i5;
            fontMetricsInt.top = i4;
            fontMetricsInt.bottom = i5;
        }
        return bounds.right + 0;
    }

    private Drawable A00() {
        Drawable drawable;
        WeakReference<Drawable> weakReference = this.A00;
        if (weakReference == null || (drawable = weakReference.get()) == null) {
            drawable = getDrawable();
            if (drawable != null) {
                this.A00 = new WeakReference<>(drawable);
            } else {
                throw new AssertionError("Unable to load drawable!");
            }
        }
        return drawable;
    }
}
