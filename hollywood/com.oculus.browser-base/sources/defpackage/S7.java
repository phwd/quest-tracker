package defpackage;

import android.os.Build;
import android.view.View;

/* renamed from: S7  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class S7 implements AbstractC0290Es0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ LayoutInflater$Factory2C3156j8 f8878a;

    public S7(LayoutInflater$Factory2C3156j8 j8Var) {
        this.f8878a = j8Var;
    }

    @Override // defpackage.AbstractC0290Es0
    public C3985nz1 a(View view, C3985nz1 nz1) {
        AbstractC2789gz1 gz1;
        int d = nz1.d();
        int N = this.f8878a.N(nz1, null);
        if (d != N) {
            int b = nz1.b();
            int c = nz1.c();
            int a2 = nz1.a();
            int i = Build.VERSION.SDK_INT;
            if (i >= 30) {
                gz1 = new C2618fz1(nz1);
            } else if (i >= 29) {
                gz1 = new C2447ez1(nz1);
            } else {
                gz1 = new C2276dz1(nz1);
            }
            gz1.c(X10.a(b, N, c, a2));
            nz1 = gz1.a();
        }
        return AbstractC1920bu1.i(view, nz1);
    }
}
