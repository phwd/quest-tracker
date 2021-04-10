package defpackage;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Shader;

/* renamed from: oT0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4066oT0 extends AbstractC4918tT0 {
    public final C4408qT0 b;

    public C4066oT0(C4408qT0 qt0) {
        this.b = qt0;
    }

    @Override // defpackage.AbstractC4918tT0
    public void a(Matrix matrix, C3041iT0 it0, int i, Canvas canvas) {
        C4408qT0 qt0 = this.b;
        float f = qt0.g;
        float f2 = qt0.h;
        C4408qT0 qt02 = this.b;
        RectF rectF = new RectF(qt02.c, qt02.d, qt02.e, qt02.f);
        boolean z = f2 < 0.0f;
        Path path = it0.k;
        if (z) {
            int[] iArr = C3041iT0.c;
            iArr[0] = 0;
            iArr[1] = it0.j;
            iArr[2] = it0.i;
            iArr[3] = it0.h;
        } else {
            path.rewind();
            path.moveTo(rectF.centerX(), rectF.centerY());
            path.arcTo(rectF, f, f2);
            path.close();
            float f3 = (float) (-i);
            rectF.inset(f3, f3);
            int[] iArr2 = C3041iT0.c;
            iArr2[0] = 0;
            iArr2[1] = it0.h;
            iArr2[2] = it0.i;
            iArr2[3] = it0.j;
        }
        float width = 1.0f - (((float) i) / (rectF.width() / 2.0f));
        float[] fArr = C3041iT0.d;
        fArr[1] = width;
        fArr[2] = ((1.0f - width) / 2.0f) + width;
        it0.f.setShader(new RadialGradient(rectF.centerX(), rectF.centerY(), rectF.width() / 2.0f, C3041iT0.c, fArr, Shader.TileMode.CLAMP));
        canvas.save();
        canvas.concat(matrix);
        if (!z) {
            canvas.clipPath(path, Region.Op.DIFFERENCE);
            canvas.drawPath(path, it0.l);
        }
        canvas.drawArc(rectF, f, f2, true, it0.f);
        canvas.restore();
    }
}
