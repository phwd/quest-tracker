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
import com.facebook.drawee.view.SimpleDraweeView;
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.tablet.destinationui.DestinationUIViewModel;

public abstract class AnytimeBarActiveAppButtonV2Binding extends ViewDataBinding {
    @NonNull
    public final SimpleDraweeView appImage;
    @NonNull
    public final ImageView appScreenshot;
    @NonNull
    public final OCButton button;
    @Bindable
    protected DestinationUIViewModel mDestinationUIViewModel;

    public abstract void setDestinationUIViewModel(@Nullable DestinationUIViewModel destinationUIViewModel);

    protected AnytimeBarActiveAppButtonV2Binding(Object obj, View view, int i, SimpleDraweeView simpleDraweeView, ImageView imageView, OCButton oCButton) {
        super(obj, view, i);
        this.appImage = simpleDraweeView;
        this.appScreenshot = imageView;
        this.button = oCButton;
    }

    @Nullable
    public DestinationUIViewModel getDestinationUIViewModel() {
        return this.mDestinationUIViewModel;
    }

    @NonNull
    public static AnytimeBarActiveAppButtonV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeBarActiveAppButtonV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeBarActiveAppButtonV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_bar_active_app_button_v2, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeBarActiveAppButtonV2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeBarActiveAppButtonV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeBarActiveAppButtonV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_bar_active_app_button_v2, null, false, obj);
    }

    public static AnytimeBarActiveAppButtonV2Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeBarActiveAppButtonV2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeBarActiveAppButtonV2Binding) bind(obj, view, R.layout.anytime_bar_active_app_button_v2);
    }
}
