package com.google.android.material.tabs;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TabItem extends View {
    public final CharSequence F;
    public final Drawable G;
    public final int H;

    public TabItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Drawable drawable;
        int resourceId;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, FJ0.G0);
        this.F = obtainStyledAttributes.getText(2);
        if (!obtainStyledAttributes.hasValue(0) || (resourceId = obtainStyledAttributes.getResourceId(0, 0)) == 0) {
            drawable = obtainStyledAttributes.getDrawable(0);
        } else {
            drawable = AbstractC5544x8.a(context, resourceId);
        }
        this.G = drawable;
        this.H = obtainStyledAttributes.getResourceId(1, 0);
        obtainStyledAttributes.recycle();
    }
}
