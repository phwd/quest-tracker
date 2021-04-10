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

/* renamed from: X.1qO  reason: invalid class name and case insensitive filesystem */
public final class C10991qO {
    public C11101qc A00;
    public C11101qc A01;
    public C11101qc A02;
    @NonNull
    public final View A03;
    public final C10911qB A04 = C10911qB.A00();

    public static final void A00(C10991qO r2, ColorStateList colorStateList) {
        if (colorStateList != null) {
            C11101qc r1 = r2.A00;
            if (r1 == null) {
                r1 = new C11101qc();
                r2.A00 = r1;
            }
            r1.A00 = colorStateList;
            r1.A02 = true;
        } else {
            r2.A00 = null;
        }
        r2.A03();
    }

    public final ColorStateList A01() {
        C11101qc r0 = this.A02;
        if (r0 != null) {
            return r0.A00;
        }
        return null;
    }

    public final PorterDuff.Mode A02() {
        C11101qc r0 = this.A02;
        if (r0 != null) {
            return r0.A01;
        }
        return null;
    }

    public final void A03() {
        View view = this.A03;
        Drawable background = view.getBackground();
        if (background != null) {
            if (this.A00 != null) {
                C11101qc r2 = this.A01;
                if (r2 == null) {
                    r2 = new C11101qc();
                    this.A01 = r2;
                }
                r2.A00 = null;
                r2.A02 = false;
                r2.A01 = null;
                r2.A03 = false;
                ColorStateList backgroundTintList = view.getBackgroundTintList();
                if (backgroundTintList != null) {
                    r2.A02 = true;
                    r2.A00 = backgroundTintList;
                }
                PorterDuff.Mode backgroundTintMode = view.getBackgroundTintMode();
                if (backgroundTintMode != null) {
                    r2.A03 = true;
                    r2.A01 = backgroundTintMode;
                }
                if (r2.A02 || r2.A03) {
                    C10821pj.A02(background, r2, view.getDrawableState());
                    return;
                }
            }
            C11101qc r1 = this.A02;
            if (r1 != null || (r1 = this.A00) != null) {
                C10821pj.A02(background, r1, view.getDrawableState());
            }
        }
    }

    public final void A04(int i) {
        ColorStateList colorStateList;
        C10911qB r1 = this.A04;
        if (r1 != null) {
            colorStateList = r1.A02(this.A03.getContext(), i);
        } else {
            colorStateList = null;
        }
        A00(this, colorStateList);
        A03();
    }

    public final void A05(ColorStateList colorStateList) {
        C11101qc r1 = this.A02;
        if (r1 == null) {
            r1 = new C11101qc();
            this.A02 = r1;
        }
        r1.A00 = colorStateList;
        r1.A02 = true;
        A03();
    }

    public final void A06(PorterDuff.Mode mode) {
        C11101qc r1 = this.A02;
        if (r1 == null) {
            r1 = new C11101qc();
            this.A02 = r1;
        }
        r1.A01 = mode;
        r1.A03 = true;
        A03();
    }

    public final void A07(@Nullable AttributeSet attributeSet, int i) {
        ColorStateList A022;
        View view = this.A03;
        Context context = view.getContext();
        int[] iArr = C11081qa.A0P;
        C10901qA A002 = C10901qA.A00(context, attributeSet, iArr, i, 0);
        TypedArray typedArray = A002.A02;
        AnonymousClass07f.A04(view, context, iArr, attributeSet, typedArray, i);
        try {
            if (typedArray.hasValue(0) && (A022 = this.A04.A02(context, typedArray.getResourceId(0, -1))) != null) {
                A00(this, A022);
            }
            if (typedArray.hasValue(1)) {
                AnonymousClass07f.A05(view, A002.A01(1));
            }
            if (typedArray.hasValue(2)) {
                AnonymousClass07f.A06(view, C10811pi.A00(typedArray.getInt(2, -1), null));
            }
        } finally {
            A002.A04();
        }
    }

    public C10991qO(@NonNull View view) {
        this.A03 = view;
    }
}
