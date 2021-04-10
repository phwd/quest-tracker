package org.chromium.components.browser_ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.LinearLayout;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BoundedLinearLayout extends LinearLayout {
    public TypedValue F = new TypedValue();
    public TypedValue G = new TypedValue();
    public final int H;

    public BoundedLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, FJ0.t);
        int i = -1;
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(0, -1);
        obtainStyledAttributes.getValue(1, this.F);
        obtainStyledAttributes.getValue(2, this.G);
        obtainStyledAttributes.recycle();
        this.H = dimensionPixelSize > 0 ? dimensionPixelSize : i;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0046  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r8, int r9) {
        /*
        // Method dump skipped, instructions count: 101
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.browser_ui.widget.BoundedLinearLayout.onMeasure(int, int):void");
    }
}
