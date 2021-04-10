package defpackage;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

/* renamed from: Ds1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Ds1 extends Drawable.ConstantState {

    /* renamed from: a  reason: collision with root package name */
    public int f7918a;
    public Cs1 b;
    public ColorStateList c;
    public PorterDuff.Mode d;
    public boolean e;
    public Bitmap f;
    public ColorStateList g;
    public PorterDuff.Mode h;
    public int i;
    public boolean j;
    public boolean k;
    public Paint l;

    public Ds1(Ds1 ds1) {
        this.c = null;
        this.d = Fs1.G;
        if (ds1 != null) {
            this.f7918a = ds1.f7918a;
            Cs1 cs1 = new Cs1(ds1.b);
            this.b = cs1;
            if (ds1.b.f != null) {
                cs1.f = new Paint(ds1.b.f);
            }
            if (ds1.b.e != null) {
                this.b.e = new Paint(ds1.b.e);
            }
            this.c = ds1.c;
            this.d = ds1.d;
            this.e = ds1.e;
        }
    }

    public boolean a() {
        Cs1 cs1 = this.b;
        if (cs1.p == null) {
            cs1.p = Boolean.valueOf(cs1.i.a());
        }
        return cs1.p.booleanValue();
    }

    public void b(int i2, int i3) {
        this.f.eraseColor(0);
        Canvas canvas = new Canvas(this.f);
        Cs1 cs1 = this.b;
        cs1.a(cs1.i, Cs1.f7845a, canvas, i2, i3, null);
    }

    public int getChangingConfigurations() {
        return this.f7918a;
    }

    public Drawable newDrawable() {
        return new Fs1(this);
    }

    public Drawable newDrawable(Resources resources) {
        return new Fs1(this);
    }

    public Ds1() {
        this.c = null;
        this.d = Fs1.G;
        this.b = new Cs1();
    }
}
