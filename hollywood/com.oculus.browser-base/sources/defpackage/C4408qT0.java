package defpackage;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;

/* renamed from: qT0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4408qT0 extends AbstractC4748sT0 {
    public static final RectF b = new RectF();
    @Deprecated
    public float c;
    @Deprecated
    public float d;
    @Deprecated
    public float e;
    @Deprecated
    public float f;
    @Deprecated
    public float g;
    @Deprecated
    public float h;

    public C4408qT0(float f2, float f3, float f4, float f5) {
        this.c = f2;
        this.d = f3;
        this.e = f4;
        this.f = f5;
    }

    @Override // defpackage.AbstractC4748sT0
    public void a(Matrix matrix, Path path) {
        Matrix matrix2 = this.f11277a;
        matrix.invert(matrix2);
        path.transform(matrix2);
        RectF rectF = b;
        rectF.set(this.c, this.d, this.e, this.f);
        path.arcTo(rectF, this.g, this.h, false);
        path.transform(matrix);
    }
}
