package com.oculus.ocui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.util.TypedValue;
import androidx.annotation.Nullable;
import com.oculus.common.ocui.R;

public class OCInfo extends OCTextView {
    public OCInfo(Context context) {
        super(context);
        init(null, 0, R.style.Body2);
    }

    public OCInfo(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet, 0, R.style.Body2);
    }

    public OCInfo(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet, i, R.style.Body2);
    }

    public OCInfo(Context context, @Nullable AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(attributeSet, i, i2);
    }

    private void init(@Nullable AttributeSet attributeSet, int i, int i2) {
        TypedValue typedValue = new TypedValue();
        getContext().getTheme().resolveAttribute(R.attr.ocPrimaryIcon, typedValue, true);
        setBackgroundResource(R.drawable.ocinfo_border);
        setCompoundDrawablesWithIntrinsicBounds(R.drawable.oc_icon_info_filled_24_d2d2d2, 0, 0, 0);
        setCompoundDrawableTintList(ColorStateList.valueOf(typedValue.data));
        setCompoundDrawablePadding(getContext().getResources().getDimensionPixelSize(R.dimen.ocinfo_image_margin));
    }
}
