package com.oculus.panelapp.anytimeui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.panelapp.anytimeui.R;

public abstract class AnytimeTabletEmptyViewV2Binding extends ViewDataBinding {
    protected AnytimeTabletEmptyViewV2Binding(Object obj, View view, int i) {
        super(obj, view, i);
    }

    @NonNull
    public static AnytimeTabletEmptyViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletEmptyViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeTabletEmptyViewV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_empty_view_v2, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeTabletEmptyViewV2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletEmptyViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeTabletEmptyViewV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_empty_view_v2, null, false, obj);
    }

    public static AnytimeTabletEmptyViewV2Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeTabletEmptyViewV2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletEmptyViewV2Binding) bind(obj, view, R.layout.anytime_tablet_empty_view_v2);
    }
}
