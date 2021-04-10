package defpackage;

import android.graphics.Matrix;
import java.util.ArrayList;

/* renamed from: zs1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C6007zs1 extends As1 {

    /* renamed from: a  reason: collision with root package name */
    public final Matrix f11776a;
    public final ArrayList b;
    public float c;
    public float d;
    public float e;
    public float f;
    public float g;
    public float h;
    public float i;
    public final Matrix j;
    public int k;
    public int[] l;
    public String m;

    public C6007zs1(C6007zs1 zs1, C4931ta taVar) {
        super(null);
        Bs1 bs1;
        this.f11776a = new Matrix();
        this.b = new ArrayList();
        this.c = 0.0f;
        this.d = 0.0f;
        this.e = 0.0f;
        this.f = 1.0f;
        this.g = 1.0f;
        this.h = 0.0f;
        this.i = 0.0f;
        Matrix matrix = new Matrix();
        this.j = matrix;
        this.m = null;
        this.c = zs1.c;
        this.d = zs1.d;
        this.e = zs1.e;
        this.f = zs1.f;
        this.g = zs1.g;
        this.h = zs1.h;
        this.i = zs1.i;
        this.l = zs1.l;
        String str = zs1.m;
        this.m = str;
        this.k = zs1.k;
        if (str != null) {
            taVar.put(str, this);
        }
        matrix.set(zs1.j);
        ArrayList arrayList = zs1.b;
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            Object obj = arrayList.get(i2);
            if (obj instanceof C6007zs1) {
                this.b.add(new C6007zs1((C6007zs1) obj, taVar));
            } else {
                if (obj instanceof C5837ys1) {
                    bs1 = new C5837ys1((C5837ys1) obj);
                } else if (obj instanceof C5667xs1) {
                    bs1 = new C5667xs1((C5667xs1) obj);
                } else {
                    throw new IllegalStateException("Unknown object in the tree!");
                }
                this.b.add(bs1);
                Object obj2 = bs1.b;
                if (obj2 != null) {
                    taVar.put(obj2, bs1);
                }
            }
        }
    }

    @Override // defpackage.As1
    public boolean a() {
        for (int i2 = 0; i2 < this.b.size(); i2++) {
            if (((As1) this.b.get(i2)).a()) {
                return true;
            }
        }
        return false;
    }

    @Override // defpackage.As1
    public boolean b(int[] iArr) {
        boolean z = false;
        for (int i2 = 0; i2 < this.b.size(); i2++) {
            z |= ((As1) this.b.get(i2)).b(iArr);
        }
        return z;
    }

    public final void c() {
        this.j.reset();
        this.j.postTranslate(-this.d, -this.e);
        this.j.postScale(this.f, this.g);
        this.j.postRotate(this.c, 0.0f, 0.0f);
        this.j.postTranslate(this.h + this.d, this.i + this.e);
    }

    public String getGroupName() {
        return this.m;
    }

    public Matrix getLocalMatrix() {
        return this.j;
    }

    public float getPivotX() {
        return this.d;
    }

    public float getPivotY() {
        return this.e;
    }

    public float getRotation() {
        return this.c;
    }

    public float getScaleX() {
        return this.f;
    }

    public float getScaleY() {
        return this.g;
    }

    public float getTranslateX() {
        return this.h;
    }

    public float getTranslateY() {
        return this.i;
    }

    public void setPivotX(float f2) {
        if (f2 != this.d) {
            this.d = f2;
            c();
        }
    }

    public void setPivotY(float f2) {
        if (f2 != this.e) {
            this.e = f2;
            c();
        }
    }

    public void setRotation(float f2) {
        if (f2 != this.c) {
            this.c = f2;
            c();
        }
    }

    public void setScaleX(float f2) {
        if (f2 != this.f) {
            this.f = f2;
            c();
        }
    }

    public void setScaleY(float f2) {
        if (f2 != this.g) {
            this.g = f2;
            c();
        }
    }

    public void setTranslateX(float f2) {
        if (f2 != this.h) {
            this.h = f2;
            c();
        }
    }

    public void setTranslateY(float f2) {
        if (f2 != this.i) {
            this.i = f2;
            c();
        }
    }

    public C6007zs1() {
        super(null);
        this.f11776a = new Matrix();
        this.b = new ArrayList();
        this.c = 0.0f;
        this.d = 0.0f;
        this.e = 0.0f;
        this.f = 1.0f;
        this.g = 1.0f;
        this.h = 0.0f;
        this.i = 0.0f;
        this.j = new Matrix();
        this.m = null;
    }
}
