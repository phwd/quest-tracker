package com.oculus.panelapp.anytimeui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.tablet.loading.LoadingDots;

public abstract class AnytimeTabletLoadingDotsV2Binding extends ViewDataBinding {
    @NonNull
    public final LoadingDots container;
    @NonNull
    public final View loadingDot1;
    @NonNull
    public final View loadingDot2;
    @NonNull
    public final View loadingDot3;

    protected AnytimeTabletLoadingDotsV2Binding(Object obj, View view, int i, LoadingDots loadingDots, View view2, View view3, View view4) {
        super(obj, view, i);
        this.container = loadingDots;
        this.loadingDot1 = view2;
        this.loadingDot2 = view3;
        this.loadingDot3 = view4;
    }

    @NonNull
    public static AnytimeTabletLoadingDotsV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletLoadingDotsV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeTabletLoadingDotsV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_loading_dots_v2, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeTabletLoadingDotsV2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletLoadingDotsV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeTabletLoadingDotsV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_loading_dots_v2, null, false, obj);
    }

    public static AnytimeTabletLoadingDotsV2Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeTabletLoadingDotsV2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletLoadingDotsV2Binding) bind(obj, view, R.layout.anytime_tablet_loading_dots_v2);
    }
}
