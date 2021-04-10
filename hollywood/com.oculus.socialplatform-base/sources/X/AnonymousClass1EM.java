package X;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;
import com.oculus.vrshell.panels.AndroidPanelLayer;

/* renamed from: X.1EM  reason: invalid class name */
public class AnonymousClass1EM extends ViewGroup.MarginLayoutParams {
    public float A00;
    public int A01;

    public AnonymousClass1EM(int i) {
        super(i, -2);
        this.A01 = -1;
        this.A00 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
    }

    public AnonymousClass1EM(ViewGroup.LayoutParams layoutParams) {
        super(layoutParams);
        this.A01 = -1;
    }

    public AnonymousClass1EM(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.A01 = -1;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C11081qa.A0D);
        this.A00 = obtainStyledAttributes.getFloat(3, AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
        this.A01 = obtainStyledAttributes.getInt(0, -1);
        obtainStyledAttributes.recycle();
    }
}
