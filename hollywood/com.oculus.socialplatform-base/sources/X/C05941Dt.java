package X;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;
import androidx.annotation.NonNull;

/* renamed from: X.1Dt  reason: invalid class name and case insensitive filesystem */
public class C05941Dt extends ViewGroup.MarginLayoutParams {
    public int A00;

    public C05941Dt(C05941Dt r2) {
        super((ViewGroup.MarginLayoutParams) r2);
        this.A00 = 0;
        this.A00 = r2.A00;
    }

    public C05941Dt() {
        super(-2, -2);
        this.A00 = 0;
        this.A00 = 8388627;
    }

    public C05941Dt(ViewGroup.LayoutParams layoutParams) {
        super(layoutParams);
        this.A00 = 0;
    }

    public C05941Dt(@NonNull Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.A00 = 0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C11081qa.A01);
        this.A00 = obtainStyledAttributes.getInt(0, 0);
        obtainStyledAttributes.recycle();
    }
}
