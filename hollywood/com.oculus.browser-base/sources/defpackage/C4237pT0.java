package defpackage;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import java.util.Objects;

/* renamed from: pT0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4237pT0 extends AbstractC4918tT0 {
    public final C4578rT0 b;
    public final float c;
    public final float d;

    public C4237pT0(C4578rT0 rt0, float f, float f2) {
        this.b = rt0;
        this.c = f;
        this.d = f2;
    }

    @Override // defpackage.AbstractC4918tT0
    public void a(Matrix matrix, C3041iT0 it0, int i, Canvas canvas) {
        C4578rT0 rt0 = this.b;
        RectF rectF = new RectF(0.0f, 0.0f, (float) Math.hypot((double) (rt0.c - this.d), (double) (rt0.b - this.c)), 0.0f);
        Matrix matrix2 = new Matrix(matrix);
        matrix2.preTranslate(this.c, this.d);
        matrix2.preRotate(b());
        Objects.requireNonNull(it0);
        rectF.bottom += (float) i;
        rectF.offset(0.0f, (float) (-i));
        int[] iArr = C3041iT0.f10141a;
        iArr[0] = it0.j;
        iArr[1] = it0.i;
        iArr[2] = it0.h;
        Paint paint = it0.g;
        float f = rectF.left;
        paint.setShader(new LinearGradient(f, rectF.top, f, rectF.bottom, iArr, C3041iT0.b, Shader.TileMode.CLAMP));
        canvas.save();
        canvas.concat(matrix2);
        canvas.drawRect(rectF, it0.g);
        canvas.restore();
    }

    public float b() {
        C4578rT0 rt0 = this.b;
        return (float) Math.toDegrees(Math.atan((double) ((rt0.c - this.d) / (rt0.b - this.c))));
    }
}
