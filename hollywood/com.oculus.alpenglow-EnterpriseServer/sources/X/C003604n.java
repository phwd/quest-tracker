package X;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;

/* renamed from: X.04n  reason: invalid class name and case insensitive filesystem */
public class C003604n extends ViewGroup.MarginLayoutParams {
    public float A00;
    public int A01;

    public C003604n(int i) {
        super(i, -2);
        this.A01 = -1;
        this.A00 = 0.0f;
    }

    public C003604n(ViewGroup.LayoutParams layoutParams) {
        super(layoutParams);
        this.A01 = -1;
    }

    public C003604n(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.A01 = -1;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AnonymousClass18N.A0D);
        this.A00 = obtainStyledAttributes.getFloat(3, 0.0f);
        this.A01 = obtainStyledAttributes.getInt(0, -1);
        obtainStyledAttributes.recycle();
    }
}
