package com.google.android.material.textview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MaterialTextView extends N8 {
    public MaterialTextView(Context context, AttributeSet attributeSet) {
        super(AbstractC3918nd0.a(context, attributeSet, 16842884, 0), attributeSet, 16842884);
        Context context2 = getContext();
        boolean z = true;
        if (AbstractC0251Ec0.b(context2, R.attr.f8590_resource_name_obfuscated_RES_2130969305, true)) {
            Resources.Theme theme = context2.getTheme();
            int[] iArr = FJ0.i0;
            TypedArray obtainStyledAttributes = theme.obtainStyledAttributes(attributeSet, iArr, 16842884, 0);
            int g = g(context2, obtainStyledAttributes, 1, 2);
            obtainStyledAttributes.recycle();
            if (!(g == -1 ? false : z)) {
                TypedArray obtainStyledAttributes2 = theme.obtainStyledAttributes(attributeSet, iArr, 16842884, 0);
                int resourceId = obtainStyledAttributes2.getResourceId(0, -1);
                obtainStyledAttributes2.recycle();
                if (resourceId != -1) {
                    f(theme, resourceId);
                }
            }
        }
    }

    public static int g(Context context, TypedArray typedArray, int... iArr) {
        int i = -1;
        for (int i2 = 0; i2 < iArr.length && i < 0; i2++) {
            int i3 = iArr[i2];
            TypedValue typedValue = new TypedValue();
            if (!typedArray.getValue(i3, typedValue) || typedValue.type != 2) {
                i = typedArray.getDimensionPixelSize(i3, -1);
            } else {
                TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{typedValue.data});
                int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(0, -1);
                obtainStyledAttributes.recycle();
                i = dimensionPixelSize;
            }
        }
        return i;
    }

    public final void f(Resources.Theme theme, int i) {
        TypedArray obtainStyledAttributes = theme.obtainStyledAttributes(i, FJ0.h0);
        int g = g(getContext(), obtainStyledAttributes, 0, 1);
        obtainStyledAttributes.recycle();
        if (g >= 0) {
            setLineHeight(g);
        }
    }

    @Override // defpackage.N8
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        if (AbstractC0251Ec0.b(context, R.attr.f8590_resource_name_obfuscated_RES_2130969305, true)) {
            f(context.getTheme(), i);
        }
    }
}
