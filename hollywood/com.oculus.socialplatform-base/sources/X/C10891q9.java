package X;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass02C.LIBRARY})
/* renamed from: X.1q9  reason: invalid class name and case insensitive filesystem */
public final class C10891q9 {
    public static final ThreadLocal<TypedValue> A00 = new ThreadLocal<>();
    public static final int[] A01 = {16842912};
    public static final int[] A02 = {-16842910};
    public static final int[] A03 = new int[0];
    public static final int[] A04 = {16842908};
    public static final int[] A05 = {16842919};
    public static final int[] A06 = new int[1];

    public static int A01(@NonNull Context context, int i) {
        int[] iArr = A06;
        iArr[0] = i;
        C10901qA r1 = new C10901qA(context, context.obtainStyledAttributes((AttributeSet) null, iArr));
        try {
            return r1.A02.getColor(0, 0);
        } finally {
            r1.A04();
        }
    }

    @Nullable
    public static ColorStateList A02(@NonNull Context context, int i) {
        int[] iArr = A06;
        iArr[0] = i;
        C10901qA r1 = new C10901qA(context, context.obtainStyledAttributes((AttributeSet) null, iArr));
        try {
            return r1.A01(0);
        } finally {
            r1.A04();
        }
    }

    public static void A03(@NonNull View view, @NonNull Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(C11081qa.A09);
        try {
            if (!obtainStyledAttributes.hasValue(115)) {
                StringBuilder sb = new StringBuilder();
                sb.append("View ");
                sb.append(view.getClass());
                sb.append(" is an AppCompat widget that can only be used with a Theme.AppCompat theme (or descendant).");
                Log.e("ThemeUtils", sb.toString());
            }
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public static int A00(@NonNull Context context, int i) {
        ColorStateList A022 = A02(context, i);
        if (A022 != null && A022.isStateful()) {
            return A022.getColorForState(A02, A022.getDefaultColor());
        }
        ThreadLocal<TypedValue> threadLocal = A00;
        TypedValue typedValue = threadLocal.get();
        if (typedValue == null) {
            typedValue = new TypedValue();
            threadLocal.set(typedValue);
        }
        context.getTheme().resolveAttribute(16842803, typedValue, true);
        float f = typedValue.getFloat();
        int A012 = A01(context, i);
        int round = Math.round(((float) Color.alpha(A012)) * f);
        if (round >= 0 && round <= 255) {
            return (A012 & 16777215) | (round << 24);
        }
        throw new IllegalArgumentException("alpha must be between 0 and 255.");
    }
}
