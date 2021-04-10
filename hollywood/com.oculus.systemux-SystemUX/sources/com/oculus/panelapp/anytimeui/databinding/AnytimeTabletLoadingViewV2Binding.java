package com.oculus.panelapp.anytimeui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.panelapp.anytimeui.R;

public abstract class AnytimeTabletLoadingViewV2Binding extends ViewDataBinding {
    @NonNull
    public final AnytimeTabletLoadingDotsV2Binding loadingDots;

    protected AnytimeTabletLoadingViewV2Binding(Object obj, View view, int i, AnytimeTabletLoadingDotsV2Binding anytimeTabletLoadingDotsV2Binding) {
        super(obj, view, i);
        this.loadingDots = anytimeTabletLoadingDotsV2Binding;
        setContainedBinding(this.loadingDots);
    }

    @NonNull
    public static AnytimeTabletLoadingViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletLoadingViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeTabletLoadingViewV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_loading_view_v2, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeTabletLoadingViewV2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletLoadingViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeTabletLoadingViewV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_loading_view_v2, null, false, obj);
    }

    public static AnytimeTabletLoadingViewV2Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeTabletLoadingViewV2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletLoadingViewV2Binding) bind(obj, view, R.layout.anytime_tablet_loading_view_v2);
    }
}
