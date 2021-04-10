package defpackage;

import android.graphics.Matrix;
import android.graphics.Path;

/* renamed from: rT0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4578rT0 extends AbstractC4748sT0 {
    public float b;
    public float c;

    @Override // defpackage.AbstractC4748sT0
    public void a(Matrix matrix, Path path) {
        Matrix matrix2 = this.f11277a;
        matrix.invert(matrix2);
        path.transform(matrix2);
        path.lineTo(this.b, this.c);
        path.transform(matrix);
    }
}
