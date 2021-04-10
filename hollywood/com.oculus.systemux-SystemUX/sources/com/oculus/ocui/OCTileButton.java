package com.oculus.ocui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.common.ocui.R;
import com.oculus.common.ocui.databinding.OctileButtonBinding;

public class OCTileButton extends ConstraintLayout {
    private OctileButtonBinding mBinding;
    private OCEventHandler mEventHandler;
    private View.OnClickListener mOnClickListener;
    private View.OnHoverListener mOnHoverListener;

    public OCTileButton(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mBinding = OctileButtonBinding.inflate(LayoutInflater.from(context), this, true);
        super.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.ocui.$$Lambda$OCTileButton$ylrKGIF8ZHnEYSJv5MV11KBnWwU */

            public final void onClick(View view) {
                OCTileButton.this.lambda$new$16$OCTileButton(view);
            }
        });
        super.setOnHoverListener(new View.OnHoverListener() {
            /* class com.oculus.ocui.$$Lambda$OCTileButton$szVhWPlfF7fmlqfo6UBDoEuWJo4 */

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return OCTileButton.this.lambda$new$17$OCTileButton(view, motionEvent);
            }
        });
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.OCTileButton);
        try {
            this.mBinding.setTitle(obtainStyledAttributes.getString(R.styleable.OCTileButton_title));
            this.mBinding.setTitleIcon(obtainStyledAttributes.getDrawable(R.styleable.OCTileButton_titleIcon));
            this.mBinding.setSubtitle(obtainStyledAttributes.getString(R.styleable.OCTileButton_subtitle));
            this.mBinding.setActiveIndicator(obtainStyledAttributes.getDrawable(R.styleable.OCTileButton_activeIndicator));
            Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.OCTileButton_background);
            if (drawable != null) {
                this.mBinding.setBackground(drawable);
            } else {
                this.mBinding.setBackground(context.getDrawable(R.drawable.octile_button_background));
            }
            setCtaText(obtainStyledAttributes.getString(R.styleable.OCTileButton_ctaText));
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public /* synthetic */ void lambda$new$16$OCTileButton(View view) {
        OCEventHandler oCEventHandler = this.mEventHandler;
        if (oCEventHandler != null) {
            oCEventHandler.onButtonClick();
        }
        View.OnClickListener onClickListener = this.mOnClickListener;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    public /* synthetic */ boolean lambda$new$17$OCTileButton(View view, MotionEvent motionEvent) {
        if (this.mEventHandler != null && motionEvent.getAction() == 9) {
            this.mEventHandler.onButtonEnter();
        }
        View.OnHoverListener onHoverListener = this.mOnHoverListener;
        if (onHoverListener != null) {
            return onHoverListener.onHover(view, motionEvent);
        }
        return false;
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

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        setAlpha(OCConstants.getOpacity(getResources(), z));
    }

    public void setSelected(boolean z) {
        super.setSelected(z);
        this.mBinding.activeIndicatorView.setVisibility(z ? 0 : 8);
    }

    public void setCtaText(String str) {
        this.mBinding.setCtaText(str);
        this.mBinding.ctaIcon.setVisibility(TextUtils.isEmpty(str) ? 8 : 0);
    }

    public void setTitleIcon(Drawable drawable) {
        this.mBinding.setTitleIcon(drawable);
    }

    public void setSubtitle(String str) {
        this.mBinding.setSubtitle(str);
    }

    public void setTitle(String str) {
        this.mBinding.setTitle(str);
    }
}
