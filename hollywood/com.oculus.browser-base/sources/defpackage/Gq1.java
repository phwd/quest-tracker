package defpackage;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.ReplacementSpan;

/* renamed from: Gq1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Gq1 extends ReplacementSpan {
    public static final Gq1 F = new Gq1();

    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        canvas.drawText("...", f, (float) i4, paint);
    }

    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
        return (int) paint.measureText("...");
    }
}
