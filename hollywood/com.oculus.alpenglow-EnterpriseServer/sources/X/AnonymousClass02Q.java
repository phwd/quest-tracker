package X;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;
import androidx.annotation.NonNull;

/* renamed from: X.02Q  reason: invalid class name */
public class AnonymousClass02Q extends ViewGroup.MarginLayoutParams {
    public int A00;

    public AnonymousClass02Q(AnonymousClass02Q r2) {
        super((ViewGroup.MarginLayoutParams) r2);
        this.A00 = 0;
        this.A00 = r2.A00;
    }

    public AnonymousClass02Q() {
        super(-2, -2);
        this.A00 = 0;
        this.A00 = 8388627;
    }

    public AnonymousClass02Q(ViewGroup.LayoutParams layoutParams) {
        super(layoutParams);
        this.A00 = 0;
    }

    public AnonymousClass02Q(@NonNull Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.A00 = 0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AnonymousClass18N.A01);
        this.A00 = obtainStyledAttributes.getInt(0, 0);
        obtainStyledAttributes.recycle();
    }
}
