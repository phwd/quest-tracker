package X;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* renamed from: X.04A  reason: invalid class name */
public final class AnonymousClass04A {
    public AnonymousClass05X A00;
    public AnonymousClass05X A01;
    public AnonymousClass05X A02;
    @NonNull
    public final View A03;
    public final AnonymousClass04E A04 = AnonymousClass04E.A01();

    public static final void A00(AnonymousClass04A r2, ColorStateList colorStateList) {
        if (colorStateList != null) {
            AnonymousClass05X r1 = r2.A00;
            if (r1 == null) {
                r1 = new AnonymousClass05X();
                r2.A00 = r1;
            }
            r1.A00 = colorStateList;
            r1.A02 = true;
        } else {
            r2.A00 = null;
        }
        r2.A04();
    }

    private boolean A01(@NonNull Drawable drawable) {
        AnonymousClass05X r4 = this.A02;
        if (r4 == null) {
            r4 = new AnonymousClass05X();
            this.A02 = r4;
        }
        r4.A00 = null;
        r4.A02 = false;
        r4.A01 = null;
        r4.A03 = false;
        View view = this.A03;
        ColorStateList backgroundTintList = view.getBackgroundTintList();
        if (backgroundTintList != null) {
            r4.A02 = true;
            r4.A00 = backgroundTintList;
        }
        PorterDuff.Mode backgroundTintMode = view.getBackgroundTintMode();
        if (backgroundTintMode != null) {
            r4.A03 = true;
            r4.A01 = backgroundTintMode;
        }
        if (!r4.A02 && !r4.A03) {
            return false;
        }
        AnonymousClass17F.A02(drawable, r4, view.getDrawableState());
        return true;
    }

    public final ColorStateList A02() {
        AnonymousClass05X r0 = this.A01;
        if (r0 != null) {
            return r0.A00;
        }
        return null;
    }

    public final PorterDuff.Mode A03() {
        AnonymousClass05X r0 = this.A01;
        if (r0 != null) {
            return r0.A01;
        }
        return null;
    }

    public final void A04() {
        View view = this.A03;
        Drawable background = view.getBackground();
        if (background == null) {
            return;
        }
        if (this.A00 == null || !A01(background)) {
            AnonymousClass05X r1 = this.A01;
            if (r1 != null || (r1 = this.A00) != null) {
                AnonymousClass17F.A02(background, r1, view.getDrawableState());
            }
        }
    }

    public final void A05(int i) {
        ColorStateList colorStateList;
        AnonymousClass04E r1 = this.A04;
        if (r1 != null) {
            colorStateList = r1.A03(this.A03.getContext(), i);
        } else {
            colorStateList = null;
        }
        A00(this, colorStateList);
        A04();
    }

    public final void A06(ColorStateList colorStateList) {
        AnonymousClass05X r1 = this.A01;
        if (r1 == null) {
            r1 = new AnonymousClass05X();
            this.A01 = r1;
        }
        r1.A00 = colorStateList;
        r1.A02 = true;
        A04();
    }

    public final void A07(PorterDuff.Mode mode) {
        AnonymousClass05X r1 = this.A01;
        if (r1 == null) {
            r1 = new AnonymousClass05X();
            this.A01 = r1;
        }
        r1.A01 = mode;
        r1.A03 = true;
        A04();
    }

    public final void A08(@Nullable AttributeSet attributeSet, int i) {
        ColorStateList A032;
        View view = this.A03;
        Context context = view.getContext();
        int[] iArr = AnonymousClass18N.A0P;
        AnonymousClass05Y A002 = AnonymousClass05Y.A00(context, attributeSet, iArr, i, 0);
        TypedArray typedArray = A002.A02;
        AnonymousClass0Aw.A01(view, context, iArr, attributeSet, typedArray, i);
        try {
            if (typedArray.hasValue(0) && (A032 = this.A04.A03(context, typedArray.getResourceId(0, -1))) != null) {
                A00(this, A032);
            }
            if (typedArray.hasValue(1)) {
                AnonymousClass0Aw.A02(view, A002.A01(1));
            }
            if (typedArray.hasValue(2)) {
                AnonymousClass0Aw.A03(view, C002704d.A00(typedArray.getInt(2, -1), null));
            }
        } finally {
            A002.A04();
        }
    }

    public AnonymousClass04A(@NonNull View view) {
        this.A03 = view;
    }
}
