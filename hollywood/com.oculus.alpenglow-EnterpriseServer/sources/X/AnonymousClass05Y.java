package X;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
/* renamed from: X.05Y  reason: invalid class name */
public final class AnonymousClass05Y {
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
        return AnonymousClass17E.A00(this.A01, resourceId);
    }

    public final Drawable A03(int i) {
        int resourceId;
        TypedArray typedArray = this.A02;
        if (!typedArray.hasValue(i) || (resourceId = typedArray.getResourceId(i, 0)) == 0) {
            return null;
        }
        return AnonymousClass04E.A01().A04(this.A01, resourceId);
    }

    public final void A04() {
        this.A02.recycle();
    }

    public AnonymousClass05Y(Context context, TypedArray typedArray) {
        this.A01 = context;
        this.A02 = typedArray;
    }

    public static AnonymousClass05Y A00(Context context, AttributeSet attributeSet, int[] iArr, int i, int i2) {
        return new AnonymousClass05Y(context, context.obtainStyledAttributes(attributeSet, iArr, i, i2));
    }
}
