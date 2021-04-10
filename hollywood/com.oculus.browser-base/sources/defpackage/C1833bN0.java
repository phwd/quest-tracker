package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.util.StateSet;
import android.view.View;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: bN0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1833bN0 {

    /* renamed from: a  reason: collision with root package name */
    public static final int[] f9538a = {16842919};
    public static final int[] b = {16842913};
    public static final int[] c = {16842913, 16842919};
    public final View d;
    public ColorStateList e;
    public GradientDrawable f;

    public C1833bN0(View view, int i, int i2, int i3, int i4, int i5, int i6) {
        this.d = view;
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        view.getPaddingStart();
        view.getPaddingTop();
        view.getPaddingEnd();
        view.getPaddingBottom();
        Context context = view.getContext();
        ThreadLocal threadLocal = AbstractC5544x8.f11592a;
        ColorStateList colorStateList = context.getColorStateList(i2);
        ColorStateList colorStateList2 = view.getContext().getColorStateList(i4);
        int dimensionPixelSize = view.getResources().getDimensionPixelSize(i5);
        GradientDrawable gradientDrawable = new GradientDrawable();
        this.f = gradientDrawable;
        float f2 = (float) i3;
        gradientDrawable.setCornerRadius(f2);
        if (dimensionPixelSize > 0) {
            this.f.setStroke(dimensionPixelSize, colorStateList2);
        }
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setCornerRadius(f2);
        gradientDrawable2.setColor(-1);
        int[][] iArr = {b, StateSet.NOTHING};
        int colorForState = colorStateList.getColorForState(c, colorStateList.getDefaultColor());
        int colorForState2 = colorStateList.getColorForState(f9538a, colorStateList.getDefaultColor());
        Drawable rippleDrawable = new RippleDrawable(new ColorStateList(iArr, new int[]{AbstractC1331Vv.h(colorForState, Math.min(Color.alpha(colorForState) * 2, 255)), AbstractC1331Vv.h(colorForState2, Math.min(Color.alpha(colorForState2) * 2, 255))}), this.f, gradientDrawable2);
        view.setBackground(i6 != 0 ? new InsetDrawable(rippleDrawable, 0, i6, 0, i6) : rippleDrawable);
        ColorStateList colorStateList3 = view.getContext().getColorStateList(i);
        if (colorStateList3 != this.e) {
            this.e = colorStateList3;
            this.f.setColor(colorStateList3);
        }
    }
}
