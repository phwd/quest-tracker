package defpackage;

import android.graphics.Matrix;
import android.view.View;

/* renamed from: uv1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC5166uv1 extends AbstractC4996tv1 {
    public static boolean d = true;
    public static boolean e = true;

    @Override // defpackage.AbstractC5846yv1
    public void g(View view, Matrix matrix) {
        if (d) {
            try {
                view.transformMatrixToGlobal(matrix);
            } catch (NoSuchMethodError unused) {
                d = false;
            }
        }
    }

    @Override // defpackage.AbstractC5846yv1
    public void h(View view, Matrix matrix) {
        if (e) {
            try {
                view.transformMatrixToLocal(matrix);
            } catch (NoSuchMethodError unused) {
                e = false;
            }
        }
    }
}
