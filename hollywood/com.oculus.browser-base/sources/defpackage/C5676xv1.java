package defpackage;

import android.graphics.Matrix;
import android.view.View;

/* renamed from: xv1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5676xv1 extends C5506wv1 {
    @Override // defpackage.AbstractC5846yv1, defpackage.AbstractC4996tv1
    public float b(View view) {
        return view.getTransitionAlpha();
    }

    @Override // defpackage.AbstractC5336vv1, defpackage.AbstractC5846yv1
    public void d(View view, int i, int i2, int i3, int i4) {
        view.setLeftTopRightBottom(i, i2, i3, i4);
    }

    @Override // defpackage.AbstractC5846yv1, defpackage.AbstractC4996tv1
    public void e(View view, float f) {
        view.setTransitionAlpha(f);
    }

    @Override // defpackage.C5506wv1, defpackage.AbstractC5846yv1
    public void f(View view, int i) {
        view.setTransitionVisibility(i);
    }

    @Override // defpackage.AbstractC5166uv1, defpackage.AbstractC5846yv1
    public void g(View view, Matrix matrix) {
        view.transformMatrixToGlobal(matrix);
    }

    @Override // defpackage.AbstractC5166uv1, defpackage.AbstractC5846yv1
    public void h(View view, Matrix matrix) {
        view.transformMatrixToLocal(matrix);
    }
}
