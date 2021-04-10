package defpackage;

import android.view.View;

/* renamed from: St1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class St1 {

    /* renamed from: a  reason: collision with root package name */
    public final Rt1 f8923a;
    public Qt1 b = new Qt1();

    public St1(Rt1 rt1) {
        this.f8923a = rt1;
    }

    public View a(int i, int i2, int i3, int i4) {
        int d = this.f8923a.d();
        int a2 = this.f8923a.a();
        int i5 = i2 > i ? 1 : -1;
        View view = null;
        while (i != i2) {
            View c = this.f8923a.c(i);
            int b2 = this.f8923a.b(c);
            int e = this.f8923a.e(c);
            Qt1 qt1 = this.b;
            qt1.b = d;
            qt1.c = a2;
            qt1.d = b2;
            qt1.e = e;
            if (i3 != 0) {
                qt1.f8791a = 0;
                qt1.f8791a = i3 | 0;
                if (qt1.a()) {
                    return c;
                }
            }
            if (i4 != 0) {
                Qt1 qt12 = this.b;
                qt12.f8791a = 0;
                qt12.f8791a = i4 | 0;
                if (qt12.a()) {
                    view = c;
                }
            }
            i += i5;
        }
        return view;
    }

    public boolean b(View view, int i) {
        Qt1 qt1 = this.b;
        int d = this.f8923a.d();
        int a2 = this.f8923a.a();
        int b2 = this.f8923a.b(view);
        int e = this.f8923a.e(view);
        qt1.b = d;
        qt1.c = a2;
        qt1.d = b2;
        qt1.e = e;
        if (i == 0) {
            return false;
        }
        Qt1 qt12 = this.b;
        qt12.f8791a = 0;
        qt12.f8791a = 0 | i;
        return qt12.a();
    }
}
