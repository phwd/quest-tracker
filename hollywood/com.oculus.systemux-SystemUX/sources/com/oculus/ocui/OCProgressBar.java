package com.oculus.ocui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.common.ocui.R;
import com.oculus.common.ocui.databinding.OcprogressbarBinding;

public class OCProgressBar extends ConstraintLayout {
    private OcprogressbarBinding mBinding;

    public OCProgressBar(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mBinding = OcprogressbarBinding.inflate(LayoutInflater.from(context), this, true);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.OCProgressBar);
        try {
            this.mBinding.setProgress(obtainStyledAttributes.getInt(R.styleable.OCProgressBar_progress, 0));
            this.mBinding.setShowProgressPercentage(obtainStyledAttributes.getBoolean(R.styleable.OCProgressBar_showProgressPercentage, false));
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public void setProgress(int i) {
        this.mBinding.setProgress(i);
    }
}
