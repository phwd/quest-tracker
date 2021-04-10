package defpackage;

import J.N;

/* renamed from: jZ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3223jZ0 {

    /* renamed from: a  reason: collision with root package name */
    public final float f10213a;
    public final float b;
    public final float c;
    public final float d;
    public final float e;
    public final AbstractC2882hZ0 f;
    public final int g;

    public C3223jZ0(AbstractC2882hZ0 hz0, float f2, float f3, float f4, float f5, float f6, float f7, int i) {
        this.f = hz0;
        this.f10213a = f2;
        this.b = f3;
        this.c = f4;
        this.g = i;
        this.d = f5;
        this.e = f6;
    }

    public final void a(C3053iZ0 iz0, J70 j70, float f2, int i) {
        if (this.g == 2) {
            iz0.a(j70, J70.i, j70.y(), f2, (long) i);
        } else {
            iz0.a(j70, J70.h, j70.x(), f2, (long) i);
        }
    }

    public final float b(IZ0 iz0) {
        if (this.g == 2) {
            return iz0.C.B();
        }
        return iz0.C.C();
    }

    public final float c() {
        return this.c - this.e;
    }

    public final boolean d() {
        return N.M09VlOh_("HorizontalTabSwitcherAndroid");
    }
}
