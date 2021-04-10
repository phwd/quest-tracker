package com.oculus.common.ocui.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCNotchedSlider;
import com.oculus.socialplatform.R;

public abstract class OcnotchedSliderBinding extends AnonymousClass1uW {
    @Bindable
    public Drawable mActiveDrawable;
    @Bindable
    public Drawable mInactiveDrawable;
    @Bindable
    public OCNotchedSlider.OCNotchedSliderViewModel mViewModel;
    @NonNull
    public final RatingBar slider;
    @NonNull
    public final OCButton toggle;

    public abstract void setActiveDrawable(@Nullable Drawable drawable);

    public abstract void setInactiveDrawable(@Nullable Drawable drawable);

    public abstract void setViewModel(@Nullable OCNotchedSlider.OCNotchedSliderViewModel oCNotchedSliderViewModel);

    @Nullable
    public Drawable getActiveDrawable() {
        return this.mActiveDrawable;
    }

    @Nullable
    public Drawable getInactiveDrawable() {
        return this.mInactiveDrawable;
    }

    @Nullable
    public OCNotchedSlider.OCNotchedSliderViewModel getViewModel() {
        return this.mViewModel;
    }

    public OcnotchedSliderBinding(Object obj, View view, int i, RatingBar ratingBar, OCButton oCButton) {
        super(obj, view, i);
        this.slider = ratingBar;
        this.toggle = oCButton;
    }

    public static OcnotchedSliderBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static OcnotchedSliderBinding bind(@NonNull View view, @Nullable Object obj) {
        return (OcnotchedSliderBinding) AnonymousClass1uW.bind(obj, view, R.layout.ocnotched_slider);
    }

    @NonNull
    public static OcnotchedSliderBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static OcnotchedSliderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static OcnotchedSliderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (OcnotchedSliderBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.ocnotched_slider, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static OcnotchedSliderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (OcnotchedSliderBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.ocnotched_slider, null, false);
    }
}
