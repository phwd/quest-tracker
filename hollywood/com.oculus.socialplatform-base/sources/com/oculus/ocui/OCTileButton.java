package com.oculus.ocui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.common.ocui.R;
import com.oculus.common.ocui.databinding.OctileButtonBinding;

public class OCTileButton extends ConstraintLayout {
    public OctileButtonBinding mBinding;
    public OCEventHandler mEventHandler;
    public View.OnClickListener mOnClickListener;
    public View.OnHoverListener mOnHoverListener;

    public /* synthetic */ void lambda$new$0$OCTileButton(View view) {
        OCEventHandler oCEventHandler = this.mEventHandler;
        if (oCEventHandler != null) {
            oCEventHandler.onButtonClick();
        }
        View.OnClickListener onClickListener = this.mOnClickListener;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    public /* synthetic */ boolean lambda$new$1$OCTileButton(View view, MotionEvent motionEvent) {
        if (this.mEventHandler != null && motionEvent.getAction() == 9) {
            this.mEventHandler.onButtonEnter();
        }
        View.OnHoverListener onHoverListener = this.mOnHoverListener;
        if (onHoverListener != null) {
            return onHoverListener.onHover(view, motionEvent);
        }
        return false;
    }

    public void setCtaText(String str) {
        this.mBinding.setCtaText(str);
        ImageView imageView = this.mBinding.ctaIcon;
        int i = 0;
        if (TextUtils.isEmpty(str)) {
            i = 8;
        }
        imageView.setVisibility(i);
    }

    public void setLabel(String str) {
        this.mBinding.setLabel(str);
    }

    public void setSubtitle(String str) {
        this.mBinding.setSubtitle(str);
        OCTextView oCTextView = this.mBinding.subtitle;
        int i = 0;
        if (TextUtils.isEmpty(str)) {
            i = 8;
        }
        oCTextView.setVisibility(i);
    }

    public void setTitle(String str) {
        this.mBinding.setTitle(str);
    }

    public void setTitleIcon(Drawable drawable) {
        this.mBinding.setTitleIcon(drawable);
    }

    public OCTileButton(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mBinding = OctileButtonBinding.inflate(LayoutInflater.from(context), this, true);
        super.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.ocui.$$Lambda$OCTileButton$m2DE9bOUQLzhtB7GMJnoDrsrTpg2 */

            public final void onClick(View view) {
                OCTileButton.this.lambda$new$0$OCTileButton(view);
            }
        });
        super.setOnHoverListener(new View.OnHoverListener() {
            /* class com.oculus.ocui.$$Lambda$OCTileButton$STzgCpBSKsEtw3e6cI_1bLq1uvo2 */

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return OCTileButton.this.lambda$new$1$OCTileButton(view, motionEvent);
            }
        });
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.OCTileButton);
        try {
            setCtaText(obtainStyledAttributes.getString(2));
            setLabel(obtainStyledAttributes.getString(3));
            setSubtitle(obtainStyledAttributes.getString(4));
            setTitle(obtainStyledAttributes.getString(6));
            setTitleIcon(obtainStyledAttributes.getDrawable(7));
            this.mBinding.setActiveIndicator(obtainStyledAttributes.getDrawable(0));
            Drawable drawable = obtainStyledAttributes.getDrawable(1);
            if (drawable != null) {
                this.mBinding.setBackground(drawable);
            } else {
                this.mBinding.setBackground(context.getDrawable(com.oculus.socialplatform.R.drawable.octile_button_background));
            }
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        setAlpha(OCConstants.getOpacity(getResources(), z));
    }

    public void setSelected(boolean z) {
        super.setSelected(z);
        OCTextView oCTextView = this.mBinding.activeIndicatorView;
        int i = 8;
        if (z) {
            i = 0;
        }
        oCTextView.setVisibility(i);
    }

    public void setEventHandler(OCEventHandler oCEventHandler) {
        this.mEventHandler = oCEventHandler;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }

    public void setOnHoverListener(View.OnHoverListener onHoverListener) {
        this.mOnHoverListener = onHoverListener;
    }
}
