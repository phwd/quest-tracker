package defpackage;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.Size;

/* renamed from: cE0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1980cE0 {

    /* renamed from: a  reason: collision with root package name */
    public Size f9592a = new Size(0, 0);
    public final Matrix b = new Matrix();
    public final Rect c = new Rect();
    public final float[] d = new float[9];

    public Rect a() {
        this.b.getValues(this.d);
        int round = Math.round(this.d[2]);
        int round2 = Math.round(this.d[5]);
        this.c.set(round, round2, this.f9592a.getWidth() + round, this.f9592a.getHeight() + round2);
        return this.c;
    }

    public int b() {
        return this.f9592a.getHeight();
    }

    public float c() {
        this.b.getValues(this.d);
        return this.d[0];
    }

    public float d() {
        this.b.getValues(this.d);
        return this.d[2];
    }

    public float e() {
        this.b.getValues(this.d);
        return this.d[5];
    }

    public int f() {
        return this.f9592a.getWidth();
    }

    public void g(float f) {
        this.b.getValues(this.d);
        float[] fArr = this.d;
        fArr[0] = f;
        fArr[4] = f;
        this.b.setValues(fArr);
    }

    public void h(float f, float f2) {
        this.b.getValues(this.d);
        float[] fArr = this.d;
        fArr[2] = f;
        fArr[5] = f2;
        this.b.setValues(fArr);
    }
}
