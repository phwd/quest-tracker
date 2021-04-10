package X;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;
import androidx.viewpager.widget.ViewPager;

/* renamed from: X.0Cm  reason: invalid class name and case insensitive filesystem */
public class C00710Cm extends ViewGroup.LayoutParams {
    public boolean A00;
    public int A01;
    public boolean A02;

    public C00710Cm() {
        super(-1, -1);
    }

    public C00710Cm(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ViewPager.LAYOUT_ATTRS);
        this.A01 = obtainStyledAttributes.getInteger(0, 48);
        obtainStyledAttributes.recycle();
    }
}
