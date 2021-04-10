package com.oculus.panelapp.anytimeui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.R;

public abstract class AnytimeTabletLibraryMobileOculusGoBannerV2Binding extends ViewDataBinding {
    @NonNull
    public final OCTextView description;
    @NonNull
    public final OCButton dismissButton;
    @NonNull
    public final ImageView dismissIcon;
    @NonNull
    public final OCButton learnMoreButton;
    @NonNull
    public final ImageView mobileOculusGo;
    @NonNull
    public final OCTextView title;

    protected AnytimeTabletLibraryMobileOculusGoBannerV2Binding(Object obj, View view, int i, OCTextView oCTextView, OCButton oCButton, ImageView imageView, OCButton oCButton2, ImageView imageView2, OCTextView oCTextView2) {
        super(obj, view, i);
        this.description = oCTextView;
        this.dismissButton = oCButton;
        this.dismissIcon = imageView;
        this.learnMoreButton = oCButton2;
        this.mobileOculusGo = imageView2;
        this.title = oCTextView2;
    }

    @NonNull
    public static AnytimeTabletLibraryMobileOculusGoBannerV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletLibraryMobileOculusGoBannerV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeTabletLibraryMobileOculusGoBannerV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_library_mobile_oculus_go_banner_v2, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeTabletLibraryMobileOculusGoBannerV2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletLibraryMobileOculusGoBannerV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeTabletLibraryMobileOculusGoBannerV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_library_mobile_oculus_go_banner_v2, null, false, obj);
    }

    public static AnytimeTabletLibraryMobileOculusGoBannerV2Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeTabletLibraryMobileOculusGoBannerV2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletLibraryMobileOculusGoBannerV2Binding) bind(obj, view, R.layout.anytime_tablet_library_mobile_oculus_go_banner_v2);
    }
}
