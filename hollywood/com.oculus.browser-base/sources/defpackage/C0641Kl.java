package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.widget.TextView;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: Kl  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0641Kl {

    /* renamed from: a  reason: collision with root package name */
    public final Rect f8384a;
    public final ColorStateList b;
    public final ColorStateList c;
    public final ColorStateList d;
    public final int e;
    public final C3553lT0 f;

    public C0641Kl(ColorStateList colorStateList, ColorStateList colorStateList2, ColorStateList colorStateList3, int i, C3553lT0 lt0, Rect rect) {
        TE0.a(rect.left);
        TE0.a(rect.top);
        TE0.a(rect.right);
        TE0.a(rect.bottom);
        this.f8384a = rect;
        this.b = colorStateList2;
        this.c = colorStateList;
        this.d = colorStateList3;
        this.e = i;
        this.f = lt0;
    }

    public static C0641Kl a(Context context, int i) {
        if (i != 0) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i, FJ0.e0);
            Rect rect = new Rect(obtainStyledAttributes.getDimensionPixelOffset(0, 0), obtainStyledAttributes.getDimensionPixelOffset(2, 0), obtainStyledAttributes.getDimensionPixelOffset(1, 0), obtainStyledAttributes.getDimensionPixelOffset(3, 0));
            ColorStateList b2 = AbstractC2722gd0.b(context, obtainStyledAttributes, 4);
            ColorStateList b3 = AbstractC2722gd0.b(context, obtainStyledAttributes, 9);
            ColorStateList b4 = AbstractC2722gd0.b(context, obtainStyledAttributes, 7);
            int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(8, 0);
            C3553lT0 a2 = C3553lT0.a(context, obtainStyledAttributes.getResourceId(5, 0), obtainStyledAttributes.getResourceId(6, 0), new C2107d((float) 0)).a();
            obtainStyledAttributes.recycle();
            return new C0641Kl(b2, b3, b4, dimensionPixelSize, a2, rect);
        }
        throw new IllegalArgumentException("Cannot create a CalendarItemStyle with a styleResId of 0");
    }

    public void b(TextView textView) {
        C3234jd0 jd0 = new C3234jd0();
        C3234jd0 jd02 = new C3234jd0();
        jd0.a(this.f);
        jd02.a(this.f);
        jd0.o(this.c);
        ColorStateList colorStateList = this.d;
        jd0.H.l = (float) this.e;
        jd0.invalidateSelf();
        jd0.p(colorStateList);
        textView.setTextColor(this.b);
        RippleDrawable rippleDrawable = new RippleDrawable(this.b.withAlpha(30), jd0, jd02);
        Rect rect = this.f8384a;
        InsetDrawable insetDrawable = new InsetDrawable((Drawable) rippleDrawable, rect.left, rect.top, rect.right, rect.bottom);
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        textView.setBackground(insetDrawable);
    }
}
