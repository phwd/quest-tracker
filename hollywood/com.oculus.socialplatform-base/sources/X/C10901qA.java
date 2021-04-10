package X;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
/* renamed from: X.1qA  reason: invalid class name and case insensitive filesystem */
public final class C10901qA {
    public TypedValue A00;
    public final Context A01;
    public final TypedArray A02;

    public final ColorStateList A01(int i) {
        int resourceId;
        ColorStateList colorStateList;
        TypedArray typedArray = this.A02;
        if (!typedArray.hasValue(i) || (resourceId = typedArray.getResourceId(i, 0)) == 0 || (colorStateList = this.A01.getColorStateList(resourceId)) == null) {
            return typedArray.getColorStateList(i);
        }
        return colorStateList;
    }

    public final Drawable A02(int i) {
        int resourceId;
        TypedArray typedArray = this.A02;
        if (!typedArray.hasValue(i) || (resourceId = typedArray.getResourceId(i, 0)) == 0) {
            return typedArray.getDrawable(i);
        }
        return AnonymousClass1pW.A00(this.A01, resourceId);
    }

    public final Drawable A03(int i) {
        int resourceId;
        Drawable A05;
        TypedArray typedArray = this.A02;
        if (!typedArray.hasValue(i) || (resourceId = typedArray.getResourceId(i, 0)) == 0) {
            return null;
        }
        C10911qB A002 = C10911qB.A00();
        Context context = this.A01;
        synchronized (A002) {
            A05 = A002.A00.A05(context, resourceId, true);
        }
        return A05;
    }

    public final void A04() {
        this.A02.recycle();
    }

    public C10901qA(Context context, TypedArray typedArray) {
        this.A01 = context;
        this.A02 = typedArray;
    }

    public static C10901qA A00(Context context, AttributeSet attributeSet, int[] iArr, int i, int i2) {
        return new C10901qA(context, context.obtainStyledAttributes(attributeSet, iArr, i, i2));
    }
}
