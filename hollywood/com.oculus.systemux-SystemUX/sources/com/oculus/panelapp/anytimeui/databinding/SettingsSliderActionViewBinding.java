package com.oculus.panelapp.anytimeui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCSlider;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsSliderActionType;

public abstract class SettingsSliderActionViewBinding extends ViewDataBinding {
    @Bindable
    protected SettingsSliderActionType mSliderAction;
    @NonNull
    public final ImageView maxIcon;
    @NonNull
    public final OCTextView maxLabel;
    @NonNull
    public final ImageView minIcon;
    @NonNull
    public final OCTextView minLabel;
    @NonNull
    public final OCSlider slider;

    public abstract void setSliderAction(@Nullable SettingsSliderActionType settingsSliderActionType);

    protected SettingsSliderActionViewBinding(Object obj, View view, int i, ImageView imageView, OCTextView oCTextView, ImageView imageView2, OCTextView oCTextView2, OCSlider oCSlider) {
        super(obj, view, i);
        this.maxIcon = imageView;
        this.maxLabel = oCTextView;
        this.minIcon = imageView2;
        this.minLabel = oCTextView2;
        this.slider = oCSlider;
    }

    @Nullable
    public SettingsSliderActionType getSliderAction() {
        return this.mSliderAction;
    }

    @NonNull
    public static SettingsSliderActionViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SettingsSliderActionViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SettingsSliderActionViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.settings_slider_action_view, viewGroup, z, obj);
    }

    @NonNull
    public static SettingsSliderActionViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SettingsSliderActionViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SettingsSliderActionViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.settings_slider_action_view, null, false, obj);
    }

    public static SettingsSliderActionViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SettingsSliderActionViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SettingsSliderActionViewBinding) bind(obj, view, R.layout.settings_slider_action_view);
    }
}
