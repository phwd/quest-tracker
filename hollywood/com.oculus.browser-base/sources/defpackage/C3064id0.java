package defpackage;

import android.content.res.ColorStateList;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/* renamed from: id0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3064id0 extends Drawable.ConstantState {

    /* renamed from: a  reason: collision with root package name */
    public C3553lT0 f10150a;
    public EK b;
    public ColorFilter c;
    public ColorStateList d = null;
    public ColorStateList e = null;
    public ColorStateList f = null;
    public ColorStateList g = null;
    public PorterDuff.Mode h = PorterDuff.Mode.SRC_IN;
    public Rect i = null;
    public float j = 1.0f;
    public float k = 1.0f;
    public float l;
    public int m = 255;
    public float n = 0.0f;
    public float o = 0.0f;
    public float p = 0.0f;
    public int q = 0;
    public int r = 0;
    public int s = 0;
    public int t = 0;
    public boolean u = false;
    public Paint.Style v = Paint.Style.FILL_AND_STROKE;

    public C3064id0(C3553lT0 lt0, EK ek) {
        this.f10150a = lt0;
        this.b = null;
    }

    public int getChangingConfigurations() {
        return 0;
    }

    public Drawable newDrawable() {
        C3234jd0 jd0 = new C3234jd0(this);
        jd0.L = true;
        return jd0;
    }

    public C3064id0(C3064id0 id0) {
        this.f10150a = id0.f10150a;
        this.b = id0.b;
        this.l = id0.l;
        this.c = id0.c;
        this.d = id0.d;
        this.e = id0.e;
        this.h = id0.h;
        this.g = id0.g;
        this.m = id0.m;
        this.j = id0.j;
        this.s = id0.s;
        this.q = id0.q;
        this.u = id0.u;
        this.k = id0.k;
        this.n = id0.n;
        this.o = id0.o;
        this.p = id0.p;
        this.r = id0.r;
        this.t = id0.t;
        this.f = id0.f;
        this.v = id0.v;
        if (id0.i != null) {
            this.i = new Rect(id0.i);
        }
    }
}
