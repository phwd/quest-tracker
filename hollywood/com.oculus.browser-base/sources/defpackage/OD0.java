package defpackage;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.Size;
import java.util.List;

/* renamed from: OD0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class OD0 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        C1629aE0 ae0 = (C1629aE0) obj2;
        KH0 kh0 = (KH0) obj3;
        TH0 th0 = UD0.f9013a;
        if (kh0.equals(th0)) {
            ED0 ed0 = ae0.F;
            ed0.b = (C2952hx[][]) uh0.g(th0);
            ed0.f.run();
            return;
        }
        TH0 th02 = UD0.b;
        if (kh0.equals(th02)) {
            ae0.F.f7945a = (Size) uh0.g(th02);
            return;
        }
        TH0 th03 = UD0.c;
        if (kh0.equals(th03)) {
            Rect rect = (Rect) uh0.g(th03);
            int i = rect.left;
            int i2 = rect.top;
            int i3 = rect.right;
            int i4 = rect.bottom;
            ED0 ed02 = ae0.F;
            ed02.c.set(i, i2, i3, i4);
            ed02.f.run();
            ae0.a();
            return;
        }
        TH0 th04 = UD0.d;
        if (kh0.equals(th04)) {
            ae0.I = (List) uh0.g(th04);
            return;
        }
        TH0 th05 = UD0.e;
        if (kh0.equals(th05)) {
            ae0.f9421J = (List) uh0.g(th05);
            return;
        }
        TH0 th06 = UD0.f;
        if (kh0.equals(th06)) {
            Matrix matrix = (Matrix) uh0.g(th06);
            ae0.K = matrix;
            if (!matrix.isIdentity()) {
                ae0.postInvalidate();
                ae0.a();
            }
        }
    }
}
