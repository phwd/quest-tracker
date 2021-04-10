package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.TypedValue;

/* renamed from: Of1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0870Of1 {

    /* renamed from: a  reason: collision with root package name */
    public final float f8640a;
    public final ColorStateList b;
    public final int c;
    public final int d;
    public final String e;
    public final ColorStateList f;
    public final float g;
    public final float h;
    public final float i;
    public final int j;
    public boolean k = false;
    public Typeface l;

    public C0870Of1(Context context, int i2) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i2, FJ0.I0);
        this.f8640a = obtainStyledAttributes.getDimension(0, 0.0f);
        this.b = AbstractC2722gd0.b(context, obtainStyledAttributes, 3);
        AbstractC2722gd0.b(context, obtainStyledAttributes, 4);
        AbstractC2722gd0.b(context, obtainStyledAttributes, 5);
        this.c = obtainStyledAttributes.getInt(2, 0);
        this.d = obtainStyledAttributes.getInt(1, 1);
        int i3 = !obtainStyledAttributes.hasValue(12) ? 10 : 12;
        this.j = obtainStyledAttributes.getResourceId(i3, 0);
        this.e = obtainStyledAttributes.getString(i3);
        obtainStyledAttributes.getBoolean(14, false);
        this.f = AbstractC2722gd0.b(context, obtainStyledAttributes, 6);
        this.g = obtainStyledAttributes.getFloat(7, 0.0f);
        this.h = obtainStyledAttributes.getFloat(8, 0.0f);
        this.i = obtainStyledAttributes.getFloat(9, 0.0f);
        obtainStyledAttributes.recycle();
    }

    public final void a() {
        String str;
        if (this.l == null && (str = this.e) != null) {
            this.l = Typeface.create(str, this.c);
        }
        if (this.l == null) {
            int i2 = this.d;
            if (i2 == 1) {
                this.l = Typeface.SANS_SERIF;
            } else if (i2 == 2) {
                this.l = Typeface.SERIF;
            } else if (i2 != 3) {
                this.l = Typeface.DEFAULT;
            } else {
                this.l = Typeface.MONOSPACE;
            }
            this.l = Typeface.create(this.l, this.c);
        }
    }

    public void b(Context context, AbstractC0931Pf1 pf1) {
        a();
        int i2 = this.j;
        if (i2 == 0) {
            this.k = true;
        }
        if (this.k) {
            pf1.b(this.l, true);
            return;
        }
        try {
            C0748Mf1 mf1 = new C0748Mf1(this, pf1);
            if (context.isRestricted()) {
                mf1.a(-4, null);
            } else {
                AbstractC5754yM0.a(context, i2, new TypedValue(), 0, mf1, null, false, false);
            }
        } catch (Resources.NotFoundException unused) {
            this.k = true;
            pf1.a(1);
        } catch (Exception unused2) {
            this.k = true;
            pf1.a(-3);
        }
    }

    public void c(Context context, TextPaint textPaint, AbstractC0931Pf1 pf1) {
        a();
        d(textPaint, this.l);
        b(context, new C0809Nf1(this, textPaint, pf1));
        ColorStateList colorStateList = this.b;
        textPaint.setColor(colorStateList != null ? colorStateList.getColorForState(textPaint.drawableState, colorStateList.getDefaultColor()) : -16777216);
        float f2 = this.i;
        float f3 = this.g;
        float f4 = this.h;
        ColorStateList colorStateList2 = this.f;
        textPaint.setShadowLayer(f2, f3, f4, colorStateList2 != null ? colorStateList2.getColorForState(textPaint.drawableState, colorStateList2.getDefaultColor()) : 0);
    }

    public void d(TextPaint textPaint, Typeface typeface) {
        textPaint.setTypeface(typeface);
        int i2 = (~typeface.getStyle()) & this.c;
        textPaint.setFakeBoldText((i2 & 1) != 0);
        textPaint.setTextSkewX((i2 & 2) != 0 ? -0.25f : 0.0f);
        textPaint.setTextSize(this.f8640a);
    }
}
