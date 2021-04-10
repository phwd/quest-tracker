package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;

/* renamed from: lT0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3553lT0 {

    /* renamed from: a  reason: collision with root package name */
    public XA f10347a;
    public XA b;
    public XA c;
    public XA d;
    public WA e;
    public WA f;
    public WA g;
    public WA h;
    public TJ i;
    public TJ j;
    public TJ k;
    public TJ l;

    public C3553lT0(C3382kT0 kt0, AbstractC3211jT0 jt0) {
        this.f10347a = kt0.f10281a;
        this.b = kt0.b;
        this.c = kt0.c;
        this.d = kt0.d;
        this.e = kt0.e;
        this.f = kt0.f;
        this.g = kt0.g;
        this.h = kt0.h;
        this.i = kt0.i;
        this.j = kt0.j;
        this.k = kt0.k;
        this.l = kt0.l;
    }

    public static C3382kT0 a(Context context, int i2, int i3, WA wa) {
        if (i3 != 0) {
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, i2);
            i2 = i3;
            context = contextThemeWrapper;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i2, FJ0.A0);
        try {
            int i4 = obtainStyledAttributes.getInt(0, 0);
            int i5 = obtainStyledAttributes.getInt(3, i4);
            int i6 = obtainStyledAttributes.getInt(4, i4);
            int i7 = obtainStyledAttributes.getInt(2, i4);
            int i8 = obtainStyledAttributes.getInt(1, i4);
            WA c2 = c(obtainStyledAttributes, 5, wa);
            WA c3 = c(obtainStyledAttributes, 8, c2);
            WA c4 = c(obtainStyledAttributes, 9, c2);
            WA c5 = c(obtainStyledAttributes, 7, c2);
            WA c6 = c(obtainStyledAttributes, 6, c2);
            C3382kT0 kt0 = new C3382kT0();
            XA a2 = AbstractC3405kd0.a(i5);
            kt0.f10281a = a2;
            C3382kT0.b(a2);
            kt0.e = c3;
            XA a3 = AbstractC3405kd0.a(i6);
            kt0.b = a3;
            C3382kT0.b(a3);
            kt0.f = c4;
            XA a4 = AbstractC3405kd0.a(i7);
            kt0.c = a4;
            C3382kT0.b(a4);
            kt0.g = c5;
            XA a5 = AbstractC3405kd0.a(i8);
            kt0.d = a5;
            C3382kT0.b(a5);
            kt0.h = c6;
            return kt0;
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public static C3382kT0 b(Context context, AttributeSet attributeSet, int i2, int i3) {
        C2107d dVar = new C2107d((float) 0);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, FJ0.g0, i2, i3);
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        int resourceId2 = obtainStyledAttributes.getResourceId(1, 0);
        obtainStyledAttributes.recycle();
        return a(context, resourceId, resourceId2, dVar);
    }

    public static WA c(TypedArray typedArray, int i2, WA wa) {
        TypedValue peekValue = typedArray.peekValue(i2);
        if (peekValue == null) {
            return wa;
        }
        int i3 = peekValue.type;
        if (i3 == 5) {
            return new C2107d((float) TypedValue.complexToDimensionPixelSize(peekValue.data, typedArray.getResources().getDisplayMetrics()));
        }
        return i3 == 6 ? new BL0(peekValue.getFraction(1.0f, 1.0f)) : wa;
    }

    public boolean d(RectF rectF) {
        boolean z = this.l.getClass().equals(TJ.class) && this.j.getClass().equals(TJ.class) && this.i.getClass().equals(TJ.class) && this.k.getClass().equals(TJ.class);
        float a2 = this.e.a(rectF);
        return z && ((this.f.a(rectF) > a2 ? 1 : (this.f.a(rectF) == a2 ? 0 : -1)) == 0 && (this.h.a(rectF) > a2 ? 1 : (this.h.a(rectF) == a2 ? 0 : -1)) == 0 && (this.g.a(rectF) > a2 ? 1 : (this.g.a(rectF) == a2 ? 0 : -1)) == 0) && ((this.b instanceof JN0) && (this.f10347a instanceof JN0) && (this.c instanceof JN0) && (this.d instanceof JN0));
    }

    public C3553lT0 e(float f2) {
        C3382kT0 kt0 = new C3382kT0(this);
        kt0.e = new C2107d(f2);
        kt0.f = new C2107d(f2);
        kt0.g = new C2107d(f2);
        kt0.h = new C2107d(f2);
        return kt0.a();
    }

    public C3553lT0() {
        this.f10347a = new JN0();
        this.b = new JN0();
        this.c = new JN0();
        this.d = new JN0();
        this.e = new C2107d(0.0f);
        this.f = new C2107d(0.0f);
        this.g = new C2107d(0.0f);
        this.h = new C2107d(0.0f);
        this.i = new TJ();
        this.j = new TJ();
        this.k = new TJ();
        this.l = new TJ();
    }
}
