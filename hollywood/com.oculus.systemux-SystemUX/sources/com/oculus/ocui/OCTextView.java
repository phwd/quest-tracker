package com.oculus.ocui;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.oculus.common.ocui.R;

public class OCTextView extends TextView {
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

    private void init(@Nullable AttributeSet attributeSet, int i, int i2) {
        Resources resources = getResources();
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.OCTextView, i, i2);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.OCTextView_android_textSize, resources.getDimensionPixelSize(getDefaultFontSizeRes()));
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.OCTextView_allowFontScaling, true);
        float fontScaledSize = OCTextUtils.getFontScaledSize(resources, (float) dimensionPixelSize);
        if (z) {
            setTextSize(0, fontScaledSize);
        }
        int dimensionPixelSize2 = resources.getDimensionPixelSize(getDefaultLineHeightRes());
        if (obtainStyledAttributes.hasValue(R.styleable.OCTextView_android_lineHeight)) {
            dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.OCTextView_android_lineHeight, dimensionPixelSize2);
        } else if (obtainStyledAttributes.hasValue(R.styleable.OCTextView_lineHeight)) {
            dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.OCTextView_lineHeight, dimensionPixelSize2);
        }
        setLineHeightCompat(dimensionPixelSize2, z);
        obtainStyledAttributes.recycle();
    }

    public void setLineHeightCompat(int i, boolean z) {
        float fontScaledSize = z ? OCTextUtils.getFontScaledSize(getResources(), (float) i) : (float) i;
        if (Build.VERSION.SDK_INT < 28) {
            setLineSpacing(fontScaledSize, 0.0f);
        } else {
            super.setLineHeight((int) fontScaledSize);
        }
    }

    /* access modifiers changed from: protected */
    public int getDefaultFontSizeRes() {
        return R.dimen.octypography_body1_font_size;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLineHeightRes() {
        return R.dimen.octypography_body1_line_height;
    }
}
