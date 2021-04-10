package com.oculus.panelapp.anytimeui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.social.SocialViewModel;

public abstract class AnytimeBarActiveCallBarFullV2Binding extends ViewDataBinding {
    @NonNull
    public final OCButton activeCallButton;
    @NonNull
    public final Guideline buttonsGuideline;
    @NonNull
    public final OCButton callVolumeButton;
    @NonNull
    public final ImageView callVolumeIcon;
    @NonNull
    public final OCButton endCallButton;
    @NonNull
    public final ImageView endCallIcon;
    @Bindable
    protected SocialViewModel mViewModel;
    @NonNull
    public final OCButton microphoneInputButton;
    @NonNull
    public final ImageView microphoneOutputIcon;
    @NonNull
    public final OCButton muteMicrophoneButton;
    @NonNull
    public final ImageView muteMicrophoneIcon;

    public abstract void setViewModel(@Nullable SocialViewModel socialViewModel);

    protected AnytimeBarActiveCallBarFullV2Binding(Object obj, View view, int i, OCButton oCButton, Guideline guideline, OCButton oCButton2, ImageView imageView, OCButton oCButton3, ImageView imageView2, OCButton oCButton4, ImageView imageView3, OCButton oCButton5, ImageView imageView4) {
        super(obj, view, i);
        this.activeCallButton = oCButton;
        this.buttonsGuideline = guideline;
        this.callVolumeButton = oCButton2;
        this.callVolumeIcon = imageView;
        this.endCallButton = oCButton3;
        this.endCallIcon = imageView2;
        this.microphoneInputButton = oCButton4;
        this.microphoneOutputIcon = imageView3;
        this.muteMicrophoneButton = oCButton5;
        this.muteMicrophoneIcon = imageView4;
    }

    @Nullable
    public SocialViewModel getViewModel() {
        return this.mViewModel;
    }

    @NonNull
    public static AnytimeBarActiveCallBarFullV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeBarActiveCallBarFullV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeBarActiveCallBarFullV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_bar_active_call_bar_full_v2, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeBarActiveCallBarFullV2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeBarActiveCallBarFullV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeBarActiveCallBarFullV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_bar_active_call_bar_full_v2, null, false, obj);
    }

    public static AnytimeBarActiveCallBarFullV2Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeBarActiveCallBarFullV2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeBarActiveCallBarFullV2Binding) bind(obj, view, R.layout.anytime_bar_active_call_bar_full_v2);
    }
}
