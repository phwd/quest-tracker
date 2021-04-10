package com.oculus.ocui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RatingBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.oculus.common.ocui.BR;
import com.oculus.common.ocui.R;
import com.oculus.common.ocui.databinding.OcnotchedSliderBinding;

public class OCNotchedSlider extends ConstraintLayout {
    private static final int DEFAULT_NUM_STEPS = 3;
    private static final int DEFAULT_VALUE = 1;
    private static final int DISCRETE_STEP_SIZE = 1;
    private final OcnotchedSliderBinding mBinding;
    private OCEventHandler mEventHandler = null;
    private int mLastProgress = 1;
    private OnValueChangedListener mOnValueChangedListener;
    private final OCNotchedSliderViewModel mViewModel;

    public interface OnValueChangedListener {
        void onValueChanged(int i);
    }

    public OCNotchedSlider(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mBinding = OcnotchedSliderBinding.inflate(LayoutInflater.from(context), this, true);
        this.mViewModel = new OCNotchedSliderViewModel();
        this.mBinding.setViewModel(this.mViewModel);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.OCNotchedSlider);
        this.mBinding.setActiveDrawable(obtainStyledAttributes.getDrawable(R.styleable.OCNotchedSlider_activeDrawable));
        this.mBinding.setInactiveDrawable(obtainStyledAttributes.getDrawable(R.styleable.OCNotchedSlider_inactiveDrawable));
        this.mLastProgress = obtainStyledAttributes.getInteger(R.styleable.OCNotchedSlider_value, 1);
        initializeSlider(obtainStyledAttributes.getInteger(R.styleable.OCNotchedSlider_steps, 3), this.mLastProgress);
        initializeToggleButton();
        obtainStyledAttributes.recycle();
    }

    public void setEventHandler(OCEventHandler oCEventHandler) {
        this.mEventHandler = oCEventHandler;
        this.mBinding.toggle.setEventHandler(oCEventHandler);
    }

    public void setNumSteps(int i) {
        this.mBinding.slider.setNumStars(i);
        this.mBinding.slider.setStepSize(1.0f);
    }

    public void setValue(int i) {
        this.mBinding.slider.setRating((float) i);
    }

    public void setEnabled(boolean z) {
        this.mBinding.slider.setEnabled(z);
        this.mBinding.toggle.setEnabled(z);
    }

    public void setOnValueChangedListener(OnValueChangedListener onValueChangedListener) {
        this.mOnValueChangedListener = onValueChangedListener;
    }

    private void initializeToggleButton() {
        this.mBinding.toggle.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.ocui.$$Lambda$OCNotchedSlider$R6_c4pfaB70UAMYJBDty5Nuib7g */

            public final void onClick(View view) {
                OCNotchedSlider.this.lambda$initializeToggleButton$9$OCNotchedSlider(view);
            }
        });
    }

    public /* synthetic */ void lambda$initializeToggleButton$9$OCNotchedSlider(View view) {
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

    private void updateToggleButton() {
        int progress = this.mBinding.slider.getProgress();
        boolean z = true;
        this.mBinding.toggle.setSelected(progress == 0);
        OCNotchedSliderViewModel oCNotchedSliderViewModel = this.mViewModel;
        if (progress == 0) {
            z = false;
        }
        oCNotchedSliderViewModel.setIsActive(z);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private void initializeSlider(int i, int i2) {
        setNumSteps(i);
        setValue(i2);
        this.mBinding.slider.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            /* class com.oculus.ocui.$$Lambda$OCNotchedSlider$3ys1kUfBiIWulqMR6lz5CeyLQfs */

            public final void onRatingChanged(RatingBar ratingBar, float f, boolean z) {
                OCNotchedSlider.this.lambda$initializeSlider$10$OCNotchedSlider(ratingBar, f, z);
            }
        });
        this.mBinding.slider.setOnTouchListener(new View.OnTouchListener() {
            /* class com.oculus.ocui.$$Lambda$OCNotchedSlider$FUby0LrdJxkqU797RhA8ZbLtk9g */

            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return OCNotchedSlider.this.lambda$initializeSlider$11$OCNotchedSlider(view, motionEvent);
            }
        });
    }

    public /* synthetic */ void lambda$initializeSlider$10$OCNotchedSlider(RatingBar ratingBar, float f, boolean z) {
        updateToggleButton();
    }

    public /* synthetic */ boolean lambda$initializeSlider$11$OCNotchedSlider(View view, MotionEvent motionEvent) {
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

    public static class OCNotchedSliderViewModel extends BaseObservable {
        private boolean mIsActive = true;

        public void setIsActive(boolean z) {
            this.mIsActive = z;
            notifyPropertyChanged(BR.isActive);
        }

        @Bindable
        public boolean getIsActive() {
            return this.mIsActive;
        }
    }
}
