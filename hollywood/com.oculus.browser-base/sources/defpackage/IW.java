package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;
import androidx.gridlayout.widget.GridLayout;

/* renamed from: IW  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class IW extends ViewGroup.MarginLayoutParams {

    /* renamed from: a  reason: collision with root package name */
    public static final HW f8229a;
    public static final int b;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    public static final int g = 6;
    public static final int h = 7;
    public static final int i = 8;
    public static final int j = 9;
    public static final int k = 11;
    public static final int l = 12;
    public static final int m = 13;
    public static final int n = 10;
    public LW o;
    public LW p;

    static {
        HW hw = new HW(Integer.MIN_VALUE, -2147483647);
        f8229a = hw;
        b = hw.a();
    }

    public IW(LW lw, LW lw2) {
        super(-2, -2);
        LW lw3 = LW.f8420a;
        this.o = lw3;
        this.p = lw3;
        setMargins(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
        this.o = lw;
        this.p = lw2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || IW.class != obj.getClass()) {
            return false;
        }
        IW iw = (IW) obj;
        return this.p.equals(iw.p) && this.o.equals(iw.o);
    }

    public int hashCode() {
        return this.p.hashCode() + (this.o.hashCode() * 31);
    }

    public void setBaseAttributes(TypedArray typedArray, int i2, int i3) {
        ((ViewGroup.MarginLayoutParams) this).width = typedArray.getLayoutDimension(i2, -2);
        ((ViewGroup.MarginLayoutParams) this).height = typedArray.getLayoutDimension(i3, -2);
    }

    public IW(ViewGroup.LayoutParams layoutParams) {
        super(layoutParams);
        LW lw = LW.f8420a;
        this.o = lw;
        this.p = lw;
    }

    public IW(ViewGroup.MarginLayoutParams marginLayoutParams) {
        super(marginLayoutParams);
        LW lw = LW.f8420a;
        this.o = lw;
        this.p = lw;
    }

    public IW(IW iw) {
        super((ViewGroup.MarginLayoutParams) iw);
        LW lw = LW.f8420a;
        this.o = lw;
        this.p = lw;
        this.o = iw.o;
        this.p = iw.p;
    }

    /* JADX INFO: finally extract failed */
    public IW(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LW lw = LW.f8420a;
        this.o = lw;
        this.p = lw;
        int[] iArr = FJ0.V;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr);
        try {
            int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(c, Integer.MIN_VALUE);
            ((ViewGroup.MarginLayoutParams) this).leftMargin = obtainStyledAttributes.getDimensionPixelSize(d, dimensionPixelSize);
            ((ViewGroup.MarginLayoutParams) this).topMargin = obtainStyledAttributes.getDimensionPixelSize(e, dimensionPixelSize);
            ((ViewGroup.MarginLayoutParams) this).rightMargin = obtainStyledAttributes.getDimensionPixelSize(f, dimensionPixelSize);
            ((ViewGroup.MarginLayoutParams) this).bottomMargin = obtainStyledAttributes.getDimensionPixelSize(g, dimensionPixelSize);
            obtainStyledAttributes.recycle();
            TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, iArr);
            try {
                int i2 = obtainStyledAttributes2.getInt(n, 0);
                int i3 = obtainStyledAttributes2.getInt(h, Integer.MIN_VALUE);
                int i4 = i;
                int i5 = b;
                this.p = GridLayout.s(i3, obtainStyledAttributes2.getInt(i4, i5), GridLayout.d(i2, true), obtainStyledAttributes2.getFloat(j, 0.0f));
                this.o = GridLayout.s(obtainStyledAttributes2.getInt(k, Integer.MIN_VALUE), obtainStyledAttributes2.getInt(l, i5), GridLayout.d(i2, false), obtainStyledAttributes2.getFloat(m, 0.0f));
            } finally {
                obtainStyledAttributes2.recycle();
            }
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }
}
