package com.oculus.ocui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.common.ocui.R;
import com.oculus.common.ocui.databinding.OcemptyLayoutBinding;

public class OCEmptyLayout extends ConstraintLayout {
    public OcemptyLayoutBinding mBinding;

    public OCButton getOCButton() {
        return this.mBinding.cta;
    }

    public void setButtonText(String str) {
        this.mBinding.setButtonText(str);
    }

    public void setHeader(String str) {
        this.mBinding.setHeader(str);
    }

    public void setSplash(Drawable drawable) {
        this.mBinding.setSplash(drawable);
    }

    public OCEmptyLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mBinding = OcemptyLayoutBinding.inflate(LayoutInflater.from(context), this, true);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.OCEmptyLayout);
        try {
            this.mBinding.setHeader(obtainStyledAttributes.getString(1));
            this.mBinding.setSplash(obtainStyledAttributes.getDrawable(2));
            this.mBinding.setButtonText(obtainStyledAttributes.getString(0));
        } finally {
            obtainStyledAttributes.recycle();
        }
    }
}
