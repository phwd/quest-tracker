package defpackage;

import android.graphics.Paint;

/* renamed from: ys1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5837ys1 extends Bs1 {
    public int[] e;
    public C2265dw f;
    public float g = 0.0f;
    public C2265dw h;
    public float i = 1.0f;
    public float j = 1.0f;
    public float k = 0.0f;
    public float l = 1.0f;
    public float m = 0.0f;
    public Paint.Cap n = Paint.Cap.BUTT;
    public Paint.Join o = Paint.Join.MITER;
    public float p = 4.0f;

    public C5837ys1() {
    }

    @Override // defpackage.As1
    public boolean a() {
        return this.h.c() || this.f.c();
    }

    @Override // defpackage.As1
    public boolean b(int[] iArr) {
        return this.f.d(iArr) | this.h.d(iArr);
    }

    public float getFillAlpha() {
        return this.j;
    }

    public int getFillColor() {
        return this.h.c;
    }

    public float getStrokeAlpha() {
        return this.i;
    }

    public int getStrokeColor() {
        return this.f.c;
    }

    public float getStrokeWidth() {
        return this.g;
    }

    public float getTrimPathEnd() {
        return this.l;
    }

    public float getTrimPathOffset() {
        return this.m;
    }

    public float getTrimPathStart() {
        return this.k;
    }

    public void setFillAlpha(float f2) {
        this.j = f2;
    }

    public void setFillColor(int i2) {
        this.h.c = i2;
    }

    public void setStrokeAlpha(float f2) {
        this.i = f2;
    }

    public void setStrokeColor(int i2) {
        this.f.c = i2;
    }

    public void setStrokeWidth(float f2) {
        this.g = f2;
    }

    public void setTrimPathEnd(float f2) {
        this.l = f2;
    }

    public void setTrimPathOffset(float f2) {
        this.m = f2;
    }

    public void setTrimPathStart(float f2) {
        this.k = f2;
    }

    public C5837ys1(C5837ys1 ys1) {
        super(ys1);
        this.e = ys1.e;
        this.f = ys1.f;
        this.g = ys1.g;
        this.i = ys1.i;
        this.h = ys1.h;
        this.c = ys1.c;
        this.j = ys1.j;
        this.k = ys1.k;
        this.l = ys1.l;
        this.m = ys1.m;
        this.n = ys1.n;
        this.o = ys1.o;
        this.p = ys1.p;
    }
}
