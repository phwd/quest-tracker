package defpackage;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Objects;

/* renamed from: mT0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3724mT0 {

    /* renamed from: a  reason: collision with root package name */
    public final C5088uT0[] f10423a = new C5088uT0[4];
    public final Matrix[] b = new Matrix[4];
    public final Matrix[] c = new Matrix[4];
    public final PointF d = new PointF();
    public final Path e = new Path();
    public final Path f = new Path();
    public final C5088uT0 g = new C5088uT0();
    public final float[] h = new float[2];
    public final float[] i = new float[2];
    public boolean j = true;

    public C3724mT0() {
        for (int i2 = 0; i2 < 4; i2++) {
            this.f10423a[i2] = new C5088uT0();
            this.b[i2] = new Matrix();
            this.c[i2] = new Matrix();
        }
    }

    public void a(C3553lT0 lt0, float f2, RectF rectF, C2893hd0 hd0, Path path) {
        int i2;
        TJ tj;
        char c2;
        char c3;
        WA wa;
        XA xa;
        path.rewind();
        this.e.rewind();
        this.f.rewind();
        this.f.addRect(rectF, Path.Direction.CW);
        int i3 = 0;
        while (true) {
            if (i3 >= 4) {
                break;
            }
            if (i3 == 1) {
                wa = lt0.g;
            } else if (i3 == 2) {
                wa = lt0.h;
            } else if (i3 != 3) {
                wa = lt0.f;
            } else {
                wa = lt0.e;
            }
            if (i3 == 1) {
                xa = lt0.c;
            } else if (i3 == 2) {
                xa = lt0.d;
            } else if (i3 != 3) {
                xa = lt0.b;
            } else {
                xa = lt0.f10347a;
            }
            C5088uT0 ut0 = this.f10423a[i3];
            Objects.requireNonNull(xa);
            xa.a(ut0, 90.0f, f2, wa.a(rectF));
            int i4 = i3 + 1;
            float f3 = (float) (i4 * 90);
            this.b[i3].reset();
            PointF pointF = this.d;
            if (i3 == 1) {
                pointF.set(rectF.right, rectF.bottom);
            } else if (i3 == 2) {
                pointF.set(rectF.left, rectF.bottom);
            } else if (i3 != 3) {
                pointF.set(rectF.right, rectF.top);
            } else {
                pointF.set(rectF.left, rectF.top);
            }
            Matrix matrix = this.b[i3];
            PointF pointF2 = this.d;
            matrix.setTranslate(pointF2.x, pointF2.y);
            this.b[i3].preRotate(f3);
            float[] fArr = this.h;
            C5088uT0[] ut0Arr = this.f10423a;
            fArr[0] = ut0Arr[i3].c;
            fArr[1] = ut0Arr[i3].d;
            this.b[i3].mapPoints(fArr);
            this.c[i3].reset();
            Matrix matrix2 = this.c[i3];
            float[] fArr2 = this.h;
            matrix2.setTranslate(fArr2[0], fArr2[1]);
            this.c[i3].preRotate(f3);
            i3 = i4;
        }
        int i5 = 0;
        for (i2 = 4; i5 < i2; i2 = 4) {
            float[] fArr3 = this.h;
            C5088uT0[] ut0Arr2 = this.f10423a;
            fArr3[0] = ut0Arr2[i5].f11411a;
            fArr3[1] = ut0Arr2[i5].b;
            this.b[i5].mapPoints(fArr3);
            if (i5 == 0) {
                float[] fArr4 = this.h;
                path.moveTo(fArr4[0], fArr4[1]);
            } else {
                float[] fArr5 = this.h;
                path.lineTo(fArr5[0], fArr5[1]);
            }
            this.f10423a[i5].b(this.b[i5], path);
            if (hd0 != null) {
                C5088uT0 ut02 = this.f10423a[i5];
                Matrix matrix3 = this.b[i5];
                BitSet bitSet = hd0.f10086a.K;
                Objects.requireNonNull(ut02);
                bitSet.set(i5, false);
                AbstractC4918tT0[] tt0Arr = hd0.f10086a.I;
                ut02.a(ut02.f);
                tt0Arr[i5] = new C3895nT0(ut02, new ArrayList(ut02.h), matrix3);
            }
            int i6 = i5 + 1;
            int i7 = i6 % 4;
            float[] fArr6 = this.h;
            C5088uT0[] ut0Arr3 = this.f10423a;
            fArr6[0] = ut0Arr3[i5].c;
            fArr6[1] = ut0Arr3[i5].d;
            this.b[i5].mapPoints(fArr6);
            float[] fArr7 = this.i;
            C5088uT0[] ut0Arr4 = this.f10423a;
            fArr7[0] = ut0Arr4[i7].f11411a;
            fArr7[1] = ut0Arr4[i7].b;
            this.b[i7].mapPoints(fArr7);
            float[] fArr8 = this.h;
            float f4 = fArr8[0];
            float[] fArr9 = this.i;
            float max = Math.max(((float) Math.hypot((double) (f4 - fArr9[0]), (double) (fArr8[1] - fArr9[1]))) - 0.001f, 0.0f);
            float[] fArr10 = this.h;
            C5088uT0[] ut0Arr5 = this.f10423a;
            fArr10[0] = ut0Arr5[i5].c;
            fArr10[1] = ut0Arr5[i5].d;
            this.b[i5].mapPoints(fArr10);
            if (i5 == 1 || i5 == 3) {
                Math.abs(rectF.centerX() - this.h[0]);
            } else {
                Math.abs(rectF.centerY() - this.h[1]);
            }
            this.g.d(0.0f, 0.0f, 270.0f, 0.0f);
            if (i5 != 1) {
                c3 = 2;
                if (i5 != 2) {
                    c2 = 3;
                    if (i5 != 3) {
                        tj = lt0.j;
                    } else {
                        tj = lt0.i;
                    }
                } else {
                    c2 = 3;
                    tj = lt0.l;
                }
            } else {
                c3 = 2;
                c2 = 3;
                tj = lt0.k;
            }
            C5088uT0 ut03 = this.g;
            Objects.requireNonNull(tj);
            ut03.c(max, 0.0f);
            Path path2 = new Path();
            this.g.b(this.c[i5], path2);
            if (!this.j || (!b(path2, i5) && !b(path2, i7))) {
                this.g.b(this.c[i5], path);
            } else {
                path2.op(path2, this.f, Path.Op.DIFFERENCE);
                float[] fArr11 = this.h;
                C5088uT0 ut04 = this.g;
                fArr11[0] = ut04.f11411a;
                fArr11[1] = ut04.b;
                this.c[i5].mapPoints(fArr11);
                Path path3 = this.e;
                float[] fArr12 = this.h;
                path3.moveTo(fArr12[0], fArr12[1]);
                this.g.b(this.c[i5], this.e);
            }
            if (hd0 != null) {
                C5088uT0 ut05 = this.g;
                Matrix matrix4 = this.c[i5];
                Objects.requireNonNull(ut05);
                hd0.f10086a.K.set(i5 + 4, false);
                AbstractC4918tT0[] tt0Arr2 = hd0.f10086a.f10220J;
                ut05.a(ut05.f);
                tt0Arr2[i5] = new C3895nT0(ut05, new ArrayList(ut05.h), matrix4);
            }
            i5 = i6;
        }
        path.close();
        this.e.close();
        if (!this.e.isEmpty()) {
            path.op(this.e, Path.Op.UNION);
        }
    }

    public final boolean b(Path path, int i2) {
        Path path2 = new Path();
        this.f10423a[i2].b(this.b[i2], path2);
        RectF rectF = new RectF();
        path.computeBounds(rectF, true);
        path2.computeBounds(rectF, true);
        path.op(path2, Path.Op.INTERSECT);
        path.computeBounds(rectF, true);
        if (!rectF.isEmpty()) {
            return true;
        }
        if (rectF.width() <= 1.0f || rectF.height() <= 1.0f) {
            return false;
        }
        return true;
    }
}
