package com.oculus.common.ocui.databinding;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.common.ocui.R;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCNotchedSlider;

public abstract class OcnotchedSliderBinding extends ViewDataBinding {
    @Bindable
    protected Drawable mActiveDrawable;
    @Bindable
    protected Drawable mInactiveDrawable;
    @Bindable
    protected OCNotchedSlider.OCNotchedSliderViewModel mViewModel;
    @NonNull
    public final RatingBar slider;
    @NonNull
    public final OCButton toggle;

    public abstract void setActiveDrawable(@Nullable Drawable drawable);

    public abstract void setInactiveDrawable(@Nullable Drawable drawable);

    public abstract void setViewModel(@Nullable OCNotchedSlider.OCNotchedSliderViewModel oCNotchedSliderViewModel);

    protected OcnotchedSliderBinding(Object obj, View view, int i, RatingBar ratingBar, OCButton oCButton) {
        super(obj, view, i);
        this.slider = ratingBar;
        this.toggle = oCButton;
    }

    @Nullable
    public OCNotchedSlider.OCNotchedSliderViewModel getViewModel() {
        return this.mViewModel;
    }

    @Nullable
    public Drawable getActiveDrawable() {
        return this.mActiveDrawable;
    }

    @Nullable
    public Drawable getInactiveDrawable() {
        return this.mInactiveDrawable;
    }

    @NonNull
    public static OcnotchedSliderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static OcnotchedSliderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (OcnotchedSliderBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.ocnotched_slider, viewGroup, z, obj);
    }

    @NonNull
    public static OcnotchedSliderBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static OcnotchedSliderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (OcnotchedSliderBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.ocnotched_slider, null, false, obj);
    }

    public static OcnotchedSliderBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static OcnotchedSliderBinding bind(@NonNull View view, @Nullable Object obj) {
        return (OcnotchedSliderBinding) bind(obj, view, R.layout.ocnotched_slider);
    }
}
