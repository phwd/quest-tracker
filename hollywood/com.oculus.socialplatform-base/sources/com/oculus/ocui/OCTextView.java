package com.oculus.ocui;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.oculus.common.ocui.R;
import com.oculus.vrshell.panels.AndroidPanelLayer;

public class OCTextView extends TextView {
    public void setLineHeightCompat(int i, boolean z) {
        float f;
        if (z) {
            f = OCTextUtils.getFontScaledSize(getResources(), (float) i);
        } else {
            f = (float) i;
        }
        if (Build.VERSION.SDK_INT < 28) {
            setLineSpacing(f, AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
        } else {
            super.setLineHeight((int) f);
        }
    }

    private void init(@Nullable AttributeSet attributeSet, int i, int i2) {
        Resources resources = getResources();
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.OCTextView, i, i2);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(0, resources.getDimensionPixelSize(com.oculus.socialplatform.R.dimen.abc_text_size_menu_header_material));
        boolean z = obtainStyledAttributes.getBoolean(2, true);
        float fontScaledSize = OCTextUtils.getFontScaledSize(resources, (float) dimensionPixelSize);
        if (z) {
            setTextSize(0, fontScaledSize);
        }
        int dimensionPixelSize2 = resources.getDimensionPixelSize(com.oculus.socialplatform.R.dimen.octypography_body1_line_height);
        int i3 = 1;
        if (!obtainStyledAttributes.hasValue(1)) {
            if (obtainStyledAttributes.hasValue(3)) {
                i3 = 3;
            }
            setLineHeightCompat(dimensionPixelSize2, z);
            obtainStyledAttributes.recycle();
        }
        dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(i3, dimensionPixelSize2);
        setLineHeightCompat(dimensionPixelSize2, z);
        obtainStyledAttributes.recycle();
    }

    public int getDefaultFontSizeRes() {
        return com.oculus.socialplatform.R.dimen.abc_text_size_menu_header_material;
    }

    public int getDefaultLineHeightRes() {
        return com.oculus.socialplatform.R.dimen.octypography_body1_line_height;
    }

    public OCTextView(Context context) {
        super(context);
        init(null, 0, 0);
    }

    public OCTextView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet, 0, 0);
    }

    public OCTextView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet, i, 0);
    }

    public OCTextView(Context context, @Nullable AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(attributeSet, i, i2);
    }
}
