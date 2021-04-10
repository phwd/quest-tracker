package defpackage;

import android.content.Context;

/* renamed from: AZ0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AZ0 {

    /* renamed from: a  reason: collision with root package name */
    public static float f7677a;
    public static float b;
    public int c;
    public final C5950zZ0 d;
    public final C5950zZ0 e;

    public AZ0(Context context) {
        this.d = new C5950zZ0(context);
        this.e = new C5950zZ0(context);
        f7677a = 8.0f;
        b = 1.0f;
        b = 1.0f / f(1.0f);
    }

    public static float f(float f) {
        float f2;
        float f3 = f * f7677a;
        if (f3 < 1.0f) {
            f2 = f3 - (1.0f - ((float) Math.exp((double) (-f3))));
        } else {
            f2 = AbstractC2531fV.a(1.0f, (float) Math.exp((double) (1.0f - f3)), 0.63212055f, 0.36787945f);
        }
        return f2 * b;
    }

    public boolean a(long j) {
        if (d()) {
            return false;
        }
        int i = this.c;
        if (i == 0) {
            C5950zZ0 zz0 = this.d;
            long j2 = j - zz0.j;
            int i2 = zz0.k;
            if (j2 < ((long) i2)) {
                float f = f(((float) j2) / ((float) i2));
                C5950zZ0 zz02 = this.d;
                int i3 = zz02.d;
                zz02.e = Math.round(((float) (zz02.f - i3)) * f) + i3;
                C5950zZ0 zz03 = this.e;
                int i4 = zz03.d;
                zz03.e = Math.round(f * ((float) (zz03.f - i4))) + i4;
            } else {
                zz0.c();
                this.e.c();
            }
        } else if (i == 1) {
            C5950zZ0 zz04 = this.d;
            if (!zz04.n && !zz04.o(j) && !this.d.b(j)) {
                this.d.c();
            }
            C5950zZ0 zz05 = this.e;
            if (!zz05.n && !zz05.o(j) && !this.e.b(j)) {
                this.e.c();
            }
        }
        return true;
    }

    public void b(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, long j) {
        int i11;
        int i12;
        int i13;
        int i14;
        if (!d()) {
            float f = this.d.h;
            float f2 = this.e.h;
            i14 = i3;
            float f3 = (float) i14;
            if (Math.signum(f3) == Math.signum(f)) {
                i13 = i4;
                float f4 = (float) i13;
                if (Math.signum(f4) == Math.signum(f2)) {
                    i12 = (int) (f4 + f2);
                    i11 = (int) (f3 + f);
                    this.c = 1;
                    this.d.d(i, i11, i5, i6, i9, j);
                    this.e.d(i2, i12, i7, i8, i10, j);
                }
                i12 = i13;
                i11 = i14;
                this.c = 1;
                this.d.d(i, i11, i5, i6, i9, j);
                this.e.d(i2, i12, i7, i8, i10, j);
            }
        } else {
            i14 = i3;
        }
        i13 = i4;
        i12 = i13;
        i11 = i14;
        this.c = 1;
        this.d.d(i, i11, i5, i6, i9, j);
        this.e.d(i2, i12, i7, i8, i10, j);
    }

    public final void c(boolean z) {
        C5950zZ0 zz0 = this.d;
        this.e.n = z;
        zz0.n = z;
    }

    public final boolean d() {
        return this.d.n && this.e.n;
    }

    public void e(int i, int i2, int i3, int i4, long j, int i5) {
        this.c = 0;
        this.d.m(i, i3, j, i5);
        this.e.m(i2, i4, j, i5);
    }
}
