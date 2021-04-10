package com.oculus.ocui;

import X.AnonymousClass1uc;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RatingBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import com.oculus.common.ocui.R;
import com.oculus.common.ocui.databinding.OcnotchedSliderBinding;

public class OCNotchedSlider extends ConstraintLayout {
    public static final int DEFAULT_NUM_STEPS = 3;
    public static final int DEFAULT_VALUE = 1;
    public static final int DISCRETE_STEP_SIZE = 1;
    public final OcnotchedSliderBinding mBinding;
    public OCEventHandler mEventHandler = null;
    public int mLastProgress = 1;
    public OnValueChangedListener mOnValueChangedListener;
    public final OCNotchedSliderViewModel mViewModel;

    public static class OCNotchedSliderViewModel extends AnonymousClass1uc {
        public boolean mIsActive = true;

        @Bindable
        public boolean getIsActive() {
            return this.mIsActive;
        }

        public void setIsActive(boolean z) {
            this.mIsActive = z;
            notifyPropertyChanged(173);
        }
    }

    public interface OnValueChangedListener {
        void onValueChanged(int i);
    }

    private void initializeToggleButton() {
        this.mBinding.toggle.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.ocui.$$Lambda$OCNotchedSlider$Isd3nG8mpKGNTzIPmVVewpE9XI2 */

            public final void onClick(View view) {
                OCNotchedSlider.this.lambda$initializeToggleButton$0$OCNotchedSlider(view);
            }
        });
    }

    private void updateToggleButton() {
        int progress = this.mBinding.slider.getProgress();
        OCButton oCButton = this.mBinding.toggle;
        boolean z = true;
        boolean z2 = false;
        if (progress == 0) {
            z2 = true;
        }
        oCButton.setSelected(z2);
        OCNotchedSliderViewModel oCNotchedSliderViewModel = this.mViewModel;
        if (progress == 0) {
            z = false;
        }
        oCNotchedSliderViewModel.setIsActive(z);
    }

    public /* synthetic */ boolean lambda$initializeSlider$2$OCNotchedSlider(View view, MotionEvent motionEvent) {
        int progress = this.mBinding.slider.getProgress();
        int i = this.mLastProgress;
        if (i != progress) {
            if (i == 0 || progress == 0) {
                updateToggleButton();
            }
            this.mLastProgress = progress;
            if (this.mEventHandler != null && !this.mBinding.toggle.isHovered()) {
                this.mEventHandler.onButtonEnter();
            }
            OnValueChangedListener onValueChangedListener = this.mOnValueChangedListener;
            if (onValueChangedListener != null) {
                onValueChangedListener.onValueChanged(progress);
            }
        }
        return view.onTouchEvent(motionEvent);
    }

    public void setEnabled(boolean z) {
        this.mBinding.slider.setEnabled(z);
        this.mBinding.toggle.setEnabled(z);
    }

    public void setEventHandler(OCEventHandler oCEventHandler) {
        this.mEventHandler = oCEventHandler;
        this.mBinding.toggle.mEventHandler = oCEventHandler;
    }

    public void setNumSteps(int i) {
        this.mBinding.slider.setNumStars(i);
        this.mBinding.slider.setStepSize(1.0f);
    }

    public void setValue(int i) {
        this.mBinding.slider.setRating((float) i);
    }

    public OCNotchedSlider(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        OcnotchedSliderBinding inflate = OcnotchedSliderBinding.inflate(LayoutInflater.from(context), this, true);
        this.mBinding = inflate;
        OCNotchedSliderViewModel oCNotchedSliderViewModel = new OCNotchedSliderViewModel();
        this.mViewModel = oCNotchedSliderViewModel;
        inflate.setViewModel(oCNotchedSliderViewModel);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.OCNotchedSlider);
        this.mBinding.setActiveDrawable(obtainStyledAttributes.getDrawable(0));
        this.mBinding.setInactiveDrawable(obtainStyledAttributes.getDrawable(1));
        this.mLastProgress = obtainStyledAttributes.getInteger(3, 1);
        initializeSlider(obtainStyledAttributes.getInteger(2, 3), this.mLastProgress);
        initializeToggleButton();
        obtainStyledAttributes.recycle();
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private void initializeSlider(int i, int i2) {
        setNumSteps(i);
        setValue(i2);
        this.mBinding.slider.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            /* class com.oculus.ocui.$$Lambda$OCNotchedSlider$vd7mw6VV5NmGpDvvJ5rsgyKxzvs2 */

            public final void onRatingChanged(RatingBar ratingBar, float f, boolean z) {
                OCNotchedSlider.this.lambda$initializeSlider$1$OCNotchedSlider(ratingBar, f, z);
            }
        });
        this.mBinding.slider.setOnTouchListener(new View.OnTouchListener() {
            /* class com.oculus.ocui.$$Lambda$OCNotchedSlider$bXmCRV4kdCniZXst8W6wj40GQ2 */

            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return OCNotchedSlider.this.lambda$initializeSlider$2$OCNotchedSlider(view, motionEvent);
            }
        });
    }

    public /* synthetic */ void lambda$initializeToggleButton$0$OCNotchedSlider(View view) {
        view.setSelected(!view.isSelected());
        if (view.isSelected()) {
            this.mBinding.slider.setProgress(0);
            this.mViewModel.setIsActive(false);
        } else {
            this.mBinding.slider.setProgress(Math.max(this.mLastProgress, 1));
            this.mViewModel.setIsActive(true);
        }
        if (this.mOnValueChangedListener != null) {
            this.mOnValueChangedListener.onValueChanged(this.mBinding.slider.getProgress());
        }
    }

    public void setOnValueChangedListener(OnValueChangedListener onValueChangedListener) {
        this.mOnValueChangedListener = onValueChangedListener;
    }

    public /* synthetic */ void lambda$initializeSlider$1$OCNotchedSlider(RatingBar ratingBar, float f, boolean z) {
        updateToggleButton();
    }
}
