package X;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
/* renamed from: X.17F  reason: invalid class name */
public final class AnonymousClass17F {
    public static AnonymousClass17F A05;
    public static final PorterDuff.Mode A06 = PorterDuff.Mode.SRC_IN;
    public static final C099917j A07 = new C099917j();
    public TypedValue A00;
    public AnonymousClass17H A01;
    public WeakHashMap<Context, AnonymousClass06E<ColorStateList>> A02;
    public boolean A03;
    public final WeakHashMap<Context, AnonymousClass065<WeakReference<Drawable.ConstantState>>> A04 = new WeakHashMap<>(0);

    public final synchronized ColorStateList A03(@NonNull Context context, @DrawableRes int i) {
        ColorStateList colorStateList;
        AnonymousClass06E<ColorStateList> r0;
        WeakHashMap<Context, AnonymousClass06E<ColorStateList>> weakHashMap = this.A02;
        colorStateList = null;
        if (!(weakHashMap == null || (r0 = weakHashMap.get(context)) == null)) {
            colorStateList = r0.A02(i);
        }
        if (colorStateList == null) {
            AnonymousClass17H r02 = this.A01;
            if (r02 == null) {
                colorStateList = null;
            } else {
                colorStateList = r02.A4f(context, i);
            }
            if (colorStateList != null) {
                WeakHashMap<Context, AnonymousClass06E<ColorStateList>> weakHashMap2 = this.A02;
                if (weakHashMap2 == null) {
                    weakHashMap2 = new WeakHashMap<>();
                    this.A02 = weakHashMap2;
                }
                AnonymousClass06E<ColorStateList> r1 = weakHashMap2.get(context);
                if (r1 == null) {
                    r1 = new AnonymousClass06E<>();
                    this.A02.put(context, r1);
                }
                r1.A03(i, colorStateList);
            }
        }
        return colorStateList;
    }

    public final synchronized Drawable A04(@NonNull Context context, @DrawableRes int i) {
        return A05(context, i, false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0025, code lost:
        if ("android.graphics.drawable.VectorDrawable".equals(r1.getClass().getName()) != false) goto L_0x0027;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0028, code lost:
        if (r0 != false) goto L_0x002a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized android.graphics.drawable.Drawable A05(@androidx.annotation.NonNull android.content.Context r13, @androidx.annotation.DrawableRes int r14, boolean r15) {
        /*
        // Method dump skipped, instructions count: 268
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass17F.A05(android.content.Context, int, boolean):android.graphics.drawable.Drawable");
    }

    public final synchronized void A06(@NonNull Context context) {
        AnonymousClass065<WeakReference<Drawable.ConstantState>> r5 = this.A04.get(context);
        if (r5 != null) {
            int i = r5.A00;
            Object[] objArr = r5.A03;
            for (int i2 = 0; i2 < i; i2++) {
                objArr[i2] = null;
            }
            r5.A00 = 0;
            r5.A01 = false;
        }
    }

    public final synchronized void A07(AnonymousClass17H r2) {
        this.A01 = r2;
    }

    public static synchronized PorterDuffColorFilter A00(int i, PorterDuff.Mode mode) {
        PorterDuffColorFilter porterDuffColorFilter;
        synchronized (AnonymousClass17F.class) {
            C099917j r3 = A07;
            int i2 = (31 + i) * 31;
            porterDuffColorFilter = (PorterDuffColorFilter) r3.A00(Integer.valueOf(mode.hashCode() + i2));
            if (porterDuffColorFilter == null) {
                porterDuffColorFilter = new PorterDuffColorFilter(i, mode);
                r3.A01(Integer.valueOf(i2 + mode.hashCode()), porterDuffColorFilter);
            }
        }
        return porterDuffColorFilter;
    }

    public static synchronized AnonymousClass17F A01() {
        AnonymousClass17F r0;
        synchronized (AnonymousClass17F.class) {
            r0 = A05;
            if (r0 == null) {
                r0 = new AnonymousClass17F();
                A05 = r0;
            }
        }
        return r0;
    }

    public static void A02(Drawable drawable, AnonymousClass05X r4, int[] iArr) {
        ColorStateList colorStateList;
        PorterDuff.Mode mode;
        PorterDuffColorFilter porterDuffColorFilter;
        if (!C002704d.A02(drawable) || drawable.mutate() == drawable) {
            boolean z = r4.A02;
            if (z || r4.A03) {
                if (z) {
                    colorStateList = r4.A00;
                } else {
                    colorStateList = null;
                }
                if (r4.A03) {
                    mode = r4.A01;
                } else {
                    mode = A06;
                }
                if (colorStateList == null || mode == null) {
                    porterDuffColorFilter = null;
                } else {
                    porterDuffColorFilter = A00(colorStateList.getColorForState(iArr, 0), mode);
                }
                drawable.setColorFilter(porterDuffColorFilter);
                return;
            }
            drawable.clearColorFilter();
        }
    }
}
